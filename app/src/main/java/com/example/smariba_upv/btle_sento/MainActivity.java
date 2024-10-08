package com.example.smariba_upv.btle_sento;
/********************************************
 * @file MainActivity.java
 * @brief Clase principal de la aplicación desde la que se inicia el escaneo de dispositivos BTLE.
 * @version 1.0
 * @date 2024
 *******************************************/
import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.example.smariba_upv.btle_sento.API.RetrofitClient;
import com.example.smariba_upv.btle_sento.LOGIC.Utilidades;
import com.example.smariba_upv.btle_sento.POJO.Medicion;
import com.example.smariba_upv.btle_sento.POJO.TramaIBeacon;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/********************************************
 * @class MainActivity
 * @brief La clase principal que gestiona la interfaz y las funciones BTLE.
 */
public class MainActivity extends AppCompatActivity {

    /********************************************
     * @file MainActivity.java
     * @brief Clase principal de la aplicación desde la que se inicia el escaneo de dispositivos BTLE.
     * @version 1.0
     * @date 2024
     *******************************************/
    private static final String ETIQUETA_LOG = ">>>>"; ///< Etiqueta utilizada para los mensajes de log.
    private static final int CODIGO_PETICION_PERMISOS = 11223344; ///< Código para la solicitud de permisos.

    private BluetoothLeScanner elEscanner; ///< Escáner de dispositivos BTLE.
    private ScanCallback callbackDelEscaneo = null; ///< Callback para el proceso de escaneo.
    private TextView txt_datos; ///< Componente de texto para mostrar datos en la UI.


    /********************************************
     * @fn void buscarTodosLosDispositivosBTLE()
     * @brief Método que inicia el escaneo de dispositivos BTLE.
     * @details Instala el callback de escaneo y solicita permisos si es necesario.
     *******************************************/
    private void buscarTodosLosDispositivosBTLE() {
        Log.d(ETIQUETA_LOG, " buscarTodosLosDispositivosBTL(): empieza ");

        Log.d(ETIQUETA_LOG, " buscarTodosLosDispositivosBTL(): instalamos scan callback ");

        this.callbackDelEscaneo = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult resultado) {
                super.onScanResult(callbackType, resultado);
                Log.d(ETIQUETA_LOG, " buscarTodosLosDispositivosBTL(): onScanResult() ");

                mostrarInformacionDispositivoBTLE(resultado);
            }

            @Override
            public void onBatchScanResults(List<ScanResult> results) {
                super.onBatchScanResults(results);
                Log.d(ETIQUETA_LOG, " buscarTodosLosDispositivosBTL(): onBatchScanResults() ");

            }

            @Override
            public void onScanFailed(int errorCode) {
                super.onScanFailed(errorCode);
                Log.d(ETIQUETA_LOG, " buscarTodosLosDispositivosBTL(): onScanFailed() ");

            }
        };

        Log.d(ETIQUETA_LOG, " buscarTodosLosDispositivosBTL(): empezamos a escanear ");

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            Log.d(ETIQUETA_LOG, " buscarTodosLosDispositivosBTL(): NO tengo permisos para escanear ");
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    new String[]{Manifest.permission.BLUETOOTH_SCAN},
                    CODIGO_PETICION_PERMISOS);
            return;
        }
        this.elEscanner.startScan(this.callbackDelEscaneo);

    } // ()

    /**************************************************
     * @fn void mostrarInformacionDispositivoBTLE(ScanResult resultado)
     * @brief Método que muestra por consola la información de un dispositivo BTLE detectado.
     * @param[in] resultado Objeto ScanResult con la información del dispositivo detectado.
     **************************************************/
    private void mostrarInformacionDispositivoBTLE(ScanResult resultado) {

        BluetoothDevice bluetoothDevice = resultado.getDevice();
        byte[] bytes = resultado.getScanRecord().getBytes();
        int rssi = resultado.getRssi();

        Log.d(ETIQUETA_LOG, " ****************************************************");
        Log.d(ETIQUETA_LOG, " ****** DISPOSITIVO DETECTADO BTLE ****************** ");
        Log.d(ETIQUETA_LOG, " ****************************************************");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            Log.d(ETIQUETA_LOG, "  mostrarInformacionDispositivoBTLE(): NO tengo permisos para conectar ");
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    new String[]{Manifest.permission.BLUETOOTH_CONNECT},
                    CODIGO_PETICION_PERMISOS);
            return;
        }
        Log.d(ETIQUETA_LOG, " nombre = " + bluetoothDevice.getName());
        Log.d(ETIQUETA_LOG, " toString = " + bluetoothDevice.toString());

        /*
        ParcelUuid[] puuids = bluetoothDevice.getUuids();
        if ( puuids.length >= 1 ) {
            //Log.d(ETIQUETA_LOG, " uuid = " + puuids[0].getUuid());
           // Log.d(ETIQUETA_LOG, " uuid = " + puuids[0].toString());
        }*/

        Log.d(ETIQUETA_LOG, " dirección = " + bluetoothDevice.getAddress());
        Log.d(ETIQUETA_LOG, " rssi = " + rssi);

        Log.d(ETIQUETA_LOG, " bytes = " + new String(bytes));
        Log.d(ETIQUETA_LOG, " bytes (" + bytes.length + ") = " + Utilidades.bytesToHexString(bytes));

        TramaIBeacon tib = new TramaIBeacon(bytes);

        Log.d(ETIQUETA_LOG, " ----------------------------------------------------");
        Log.d(ETIQUETA_LOG, " prefijo  = " + Utilidades.bytesToHexString(tib.getPrefijo()));
        Log.d(ETIQUETA_LOG, "          advFlags = " + Utilidades.bytesToHexString(tib.getAdvFlags()));
        Log.d(ETIQUETA_LOG, "          advHeader = " + Utilidades.bytesToHexString(tib.getAdvHeader()));
        Log.d(ETIQUETA_LOG, "          companyID = " + Utilidades.bytesToHexString(tib.getCompanyID()));
        Log.d(ETIQUETA_LOG, "          iBeacon type = " + Integer.toHexString(tib.getiBeaconType()));
        Log.d(ETIQUETA_LOG, "          iBeacon length 0x = " + Integer.toHexString(tib.getiBeaconLength()) + " ( "
                + tib.getiBeaconLength() + " ) ");
        Log.d(ETIQUETA_LOG, " uuid  = " + Utilidades.bytesToHexString(tib.getUUID()));
        Log.d(ETIQUETA_LOG, " uuid  = " + Utilidades.bytesToString(tib.getUUID()));
        Log.d(ETIQUETA_LOG, " major  = " + Utilidades.bytesToHexString(tib.getMajor()) + "( "
                + Utilidades.bytesToInt(tib.getMajor()) + " ) ");
        Log.d(ETIQUETA_LOG, " minor  = " + Utilidades.bytesToHexString(tib.getMinor()) + "( "
                + Utilidades.bytesToInt(tib.getMinor()) + " ) ");
        Log.d(ETIQUETA_LOG, " txPower  = " + Integer.toHexString(tib.getTxPower()) + " ( " + tib.getTxPower() + " )");
        Log.d(ETIQUETA_LOG, " ****************************************************");

    } // ()

    /**********************************************************
     * @fn void buscarEsteDispositivoBTLE(String dispositivoBuscado)
     * @brief Método que inicia el escaneo de un dispositivo BTLE específico.
     * @param[in] dispositivoBuscado UUID del dispositivo BTLE que se busca.
     ***********************************************************/
    private void buscarEsteDispositivoBTLE(final String dispositivoBuscado) {
        //Log.d(ETIQUETA_LOG, " buscarEsteDispositivoBTLE(): empieza ");

        //Log.d(ETIQUETA_LOG, "  buscarEsteDispositivoBTLE(): instalamos scan callback ");


        // super.onScanResult(ScanSettings.SCAN_MODE_LOW_LATENCY, result); para ahorro de energía

        this.callbackDelEscaneo = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult resultado) {
                super.onScanResult(callbackType, resultado);
                //Log.d(ETIQUETA_LOG, "  buscarEsteDispositivoBTLE(): onScanResult() ");

                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }

                byte[] bytes = resultado.getScanRecord().getBytes();
                TramaIBeacon tib = new TramaIBeacon(bytes);
                if (Utilidades.bytesToString(tib.getUUID()).equals(dispositivoBuscado)) {
                    mostrarInformacionDispositivoBTLE(resultado);
                    txt_datos.setText("Major: " + (int)getMedicionsBeacon(resultado));
                    insertarMedicion((int) getMedicionsBeacon(resultado));
                }


                else {
                    //Losg.d(ETIQUETA_LOG, "  buscarEsteDispositivoBTLE(): onScanResult(): no es el dispositivo buscado ");
                }
            }

            @Override
            public void onBatchScanResults(List<ScanResult> results) {
                super.onBatchScanResults(results);
                //Log.d(ETIQUETA_LOG, "  buscarEsteDispositivoBTLE(): onBatchScanResults() ");

            }

            @Override
            public void onScanFailed(int errorCode) {
                super.onScanFailed(errorCode);
                //Log.d(ETIQUETA_LOG, "  buscarEsteDispositivoBTLE(): onScanFailed() ");

            }
        };

        ScanFilter sf = new ScanFilter.Builder().setDeviceName(dispositivoBuscado).build();

        //Log.d(ETIQUETA_LOG, "  buscarEsteDispositivoBTLE(): empezamos a escanear buscando: " + dispositivoBuscado);
        //Log.d(ETIQUETA_LOG, "  buscarEsteDispositivoBTLE(): empezamos a escanear buscando: " + dispositivoBuscado
        //      + " -> " + Utilidades.stringToUUID( dispositivoBuscado ) );

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            //Log.d(ETIQUETA_LOG, "  buscarEsteDispositivoBTLE(): NO tengo permisos para escanear ");
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    new String[]{Manifest.permission.BLUETOOTH_SCAN},
                    CODIGO_PETICION_PERMISOS);
            return;
        }
        this.elEscanner.startScan(this.callbackDelEscaneo);
    } // ()

    /******************************************************************
     * @fn void detenerBusquedaDispositivosBTLE()
     * @brief Método que detiene el escaneo de dispositivos BTLE.
     ******************************************************************/
    private void detenerBusquedaDispositivosBTLE() {

        if (this.callbackDelEscaneo == null) {
            return;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            Log.d(ETIQUETA_LOG, "  detenerBusquedaDispositivosBTLE(): NO tengo permisos para escanear ");
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    new String[]{Manifest.permission.BLUETOOTH_SCAN},
                    CODIGO_PETICION_PERMISOS);
            return;
        }
        this.elEscanner.stopScan(this.callbackDelEscaneo);
        this.callbackDelEscaneo = null;

    } // ()

    /******************************************************************
     * @param v the v
     * @fn void botonBuscarDispositivosBTLEPulsado(View v)
     * @brief Método que se ejecuta al pulsar el botón para buscar dispositivos BTLE.
     * @param[in] v View asociada al botón.
     */
    public void botonBuscarDispositivosBTLEPulsado(View v) {
        Log.d(ETIQUETA_LOG, " boton buscar dispositivos BTLE Pulsado");
        this.buscarTodosLosDispositivosBTLE();
    } // ()

    /******************************************************************
     * @param v the v
     * @fn void botonBuscarNuestroDispositivoBTLEPulsado(View v)
     * @brief Método que se ejecuta al pulsar el botón para buscar un dispositivo BTLE específico.
     * @param[in] v View asociada al botón.
     */
    public void botonBuscarNuestroDispositivoBTLEPulsado(View v) {
        Log.d(ETIQUETA_LOG, " boton nuestro dispositivo BTLE Pulsado");
        //this.buscarEsteDispositivoBTLE( Utilidades.stringToUUID( "EPSG-GTI-PROY-3A" ) );

        this.buscarEsteDispositivoBTLE( "EPSG-GTI-PROY-3D" );
        // this.buscarEsteDispositivoBTLE("fistro");

    } // ()

    /******************************************************************
     * @param v the v
     * @fn void botonDetenerBusquedaDispositivosBTLEPulsado(View v)
     * @brief Método que se ejecuta al pulsar el botón para detener la búsqueda de dispositivos BTLE.
     * @param[in] v View asociada al botón.
     */
    public void botonDetenerBusquedaDispositivosBTLEPulsado(View v) {
        Log.d(ETIQUETA_LOG, " boton detener busqueda dispositivos BTLE Pulsado");
        this.detenerBusquedaDispositivosBTLE();
    } // ()

    /************************************************************
     * @fn void inicializarBlueTooth()
     * @brief Método que inicializa el adaptador Bluetooth y solicita los permisos necesarios.
     ************************************************************/
    private void inicializarBlueTooth() {
        Log.d(ETIQUETA_LOG, " inicializarBlueTooth(): obtenemos adaptador BT ");

        BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();

        Log.d(ETIQUETA_LOG, " inicializarBlueTooth(): habilitamos adaptador BT ");

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            Log.d(ETIQUETA_LOG, "  inicializarBlueTooth(): NO tengo permisos para conectar ");
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    new String[]{Manifest.permission.BLUETOOTH_CONNECT},
                    CODIGO_PETICION_PERMISOS);
            return;
        }
        bta.enable();

        Log.d(ETIQUETA_LOG, " inicializarBlueTooth(): habilitado =  " + bta.isEnabled() );

        Log.d(ETIQUETA_LOG, " inicializarBlueTooth(): estado =  " + bta.getState() );

        Log.d(ETIQUETA_LOG, " inicializarBlueTooth(): obtenemos escaner btle ");

        this.elEscanner = bta.getBluetoothLeScanner();

        if ( this.elEscanner == null ) {
            Log.d(ETIQUETA_LOG, " inicializarBlueTooth(): Socorro: NO hemos obtenido escaner btle  !!!!");

        }

        Log.d(ETIQUETA_LOG, " inicializarBlueTooth(): voy a perdir permisos (si no los tuviera) !!!!");

        if (
                ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        )
        {
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    new String[]{Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.ACCESS_FINE_LOCATION},
                    CODIGO_PETICION_PERMISOS);
        }
        else {
            Log.d(ETIQUETA_LOG, " inicializarBlueTooth(): parece que YA tengo los permisos necesarios !!!!");

        }
    } // ()


    /************************************************************
     * @fn void verificarPermisosBluetooth()
     * @brief Método que verifica si se tienen los permisos necesarios para Bluetooth.
     ************************************************************/
    private void verificarPermisosBluetooth() {
        Log.d(ETIQUETA_LOG, " verificarPermisosBluetooth(): Verificando permisos");

        // Verificamos si los permisos necesarios ya están concedidos
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {

            // Si no están concedidos, los solicitamos
            Log.d(ETIQUETA_LOG, " verificarPermisosBluetooth(): Solicitando permisos necesarios");
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    new String[]{
                            Manifest.permission.BLUETOOTH,
                            Manifest.permission.BLUETOOTH_ADMIN,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.BLUETOOTH_SCAN,
                            Manifest.permission.BLUETOOTH_CONNECT
                    },
                    CODIGO_PETICION_PERMISOS
            );
        } else {
            Log.d(ETIQUETA_LOG, " verificarPermisosBluetooth(): Permisos ya concedidos");
            inicializarBlueTooth();  // Llamamos a la inicialización de Bluetooth si los permisos están concedidos
        }
    } // ()

    /************************************************************
     * @fn void onCreate(Bundle savedInstanceState)
     * @brief Método que se ejecuta al crear la actividad.
     * @param[in] savedInstanceState Estado de la instancia guardada.
     ************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(ETIQUETA_LOG, " onCreate(): empieza ");

        // Verificamos los permisos antes de cualquier otra operación
        verificarPermisosBluetooth();
        Button enviarMedicionButton = findViewById(R.id.btn_insertar);
        txt_datos = findViewById(R.id.Txt_datos);

    } // onCreate()

    /************************************************************
     * @fn void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
     * @brief Método que se ejecuta al solicitar permisos.
     * @param[in] requestCode Código de solicitud.
     * @param[in] permissions Permisos solicitados.
     * @param[in] grantResults Resultados de los permisos.
     ************************************************************/
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CODIGO_PETICION_PERMISOS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(ETIQUETA_LOG, " onRequestPermissionResult(): Permisos concedidos");
                    inicializarBlueTooth();  // Llamamos a la inicialización si se conceden los permisos
                } else {
                    Log.d(ETIQUETA_LOG, " onRequestPermissionResult(): Permisos NO concedidos");
                }
                return;
        }
    } // ()

    /************************************************************
     * @fn void insertarMedicion(int major)
     * @brief Método que inserta una medición en la base de datos.
     * @param[in] major Valor de la medición.
     ************************************************************/
    private void insertarMedicion(int major) {
        RetrofitClient.getApiService().insertarMedicion(new Medicion("Living Room", "CO2",  major)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("API Response", "Medición insertada correctamente");
                } else {
                    Log.d("API Error", "Error en la respuesta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("API Failure", "Error al conectar con el servidor", t);
            }
        });

    }
    /************************************************************
     * @fn double getMedicionsBeacon(ScanResult resultado)
     * @brief Método que obtiene el valor de la medición de un beacon.
     * @param[in] resultado Objeto ScanResult con la información del dispositivo detectado.
     * @return Valor de la medición.
     ************************************************************/
    public double getMedicionsBeacon(ScanResult resultado) {
        byte[] bytes = resultado.getScanRecord().getBytes();
        TramaIBeacon tib = new TramaIBeacon(bytes);
        return Utilidades.bytesToInt(tib.getMinor());
    }

} // class
