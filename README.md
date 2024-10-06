# **Biometría y Medio Ambiente - Android**

Este proyecto es una aplicación Android desarrollada en **Java** y **Kotlin**, con **Gradle** como sistema de construcción. El propósito de la aplicación es recopilar datos biométricos y ambientales a través de **sensores conectados a beacons Bluetooth**, proporcionando funcionalidades útiles para el monitoreo, análisis y visualización de esos datos en tiempo real.

## **Tecnologías utilizadas**
- **Lenguajes:** Java, Kotlin
- **Sistema de construcción:** Gradle
- **Plataforma:** Android
- **Tecnologías de conexión:** Beacons Bluetooth

## **Instalación**
Para ejecutar este proyecto en tu entorno local, sigue estos pasos:

1. Clona este repositorio:
   ```bash
   git clone https://github.com/SentoMarcos/Biometr-a-y-Medio-Ambiente-Android.git
   ```

2. Abre el proyecto en Android Studio.

3. Sincroniza Gradle y compila el proyecto.

4. Ejecuta la aplicación en un emulador o dispositivo físico con Bluetooth habilitado.

## **Uso**
### Recolección de Datos
La aplicación se comunica con **beacons Bluetooth** que están conectados a diversos sensores ambientales. Estos sensores pueden incluir:

- **Sensores ambientales**: para medir temperatura y calidad del aire, entre otros.

### Funcionalidades principales:
1. **Detección de Beacons Bluetooth**: La aplicación escanea los dispositivos Bluetooth cercanos y se conecta a los beacons que transmiten datos de los sensores.

2. **Recopilación de Datos en Tiempo Real**: Los datos son recibidos y procesados en la aplicación a medida que se transmiten desde los sensores a través de Bluetooth.

3. **Almacenamiento en la nube**: Los datos recogidos se envian a un servidor web para su posterior visualizacion.

### Flujo de trabajo típico:
1. Abre la aplicación y habilita Bluetooth en tu dispositivo Android.
2. Escanea y empareja los beacons Bluetooth que están conectados a los sensores.
3. Los datos comienzan a ser recolectados automáticamente por la aplicación y enviados al servidor web.

## **Autores**
- [SentoMarcos](https://github.com/SentoMarcos)

## **Proyectos Relacionados**
- [Biometría y Medio Ambiente - Arduino](https://github.com/SentoMarcos/Biometr-a-y-Medio-Ambiente-Arduino)
- [Biometría y Medio Ambiente - Web](https://github.com/SentoMarcos/Biometr-a-y-Medio-Ambiente-Docker-Web-DB)
