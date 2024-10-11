package com.example.smariba_upv.btle_sento;

import com.example.smariba_upv.btle_sento.POJO.TramaIBeacon;
import org.junit.Test;
import static org.junit.Assert.*;

public class TramaIBeaconTest {

    @Test
    public void testGetUUID() {
        // Create a mock iBeacon frame
        byte[] mockFrame = new byte[30];
        for (int i = 0; i < 30; i++) {
            mockFrame[i] = (byte) i;
        }

        // Create a TramaIBeacon object using the mock frame
        TramaIBeacon trama = new TramaIBeacon(mockFrame);

        // Call getUUID() and assert it returns the expected UUID
        byte[] expectedUUID = new byte[16];
        for (int i = 0; i < 16; i++) {
            expectedUUID[i] = (byte) (i + 9);
        }
        assertArrayEquals(expectedUUID, trama.getUUID());
    }

    @Test
    public void testGetPrefijo() {
        // Create a mock iBeacon frame
        byte[] mockFrame = new byte[30];
        for (int i = 0; i < 30; i++) {
            mockFrame[i] = (byte) i;
        }

        // Create a TramaIBeacon object using the mock frame
        TramaIBeacon trama = new TramaIBeacon(mockFrame);

        // Call getPrefijo() and assert it returns the expected prefijo
        byte[] expectedPrefijo = new byte[9];
        for (int i = 0; i < 9; i++) {
            expectedPrefijo[i] = (byte) i;
        }
        assertArrayEquals(expectedPrefijo, trama.getPrefijo());
    }

    @Test
    public void testGetMajor() {
        // Create a mock iBeacon frame
        byte[] mockFrame = new byte[30];
        for (int i = 0; i < 30; i++) {
            mockFrame[i] = (byte) i;
        }

        // Create a TramaIBeacon object using the mock frame
        TramaIBeacon trama = new TramaIBeacon(mockFrame);

        // Call getMajor() and assert it returns the expected major
        byte[] expectedMajor = new byte[2];
        expectedMajor[0] = 25;
        expectedMajor[1] = 26;
        assertArrayEquals(expectedMajor, trama.getMajor());
    }

    @Test
    public void testGetMinor() {
        // Create a mock iBeacon frame
        byte[] mockFrame = new byte[30];
        for (int i = 0; i < 30; i++) {
            mockFrame[i] = (byte) i;
        }

        // Create a TramaIBeacon object using the mock frame
        TramaIBeacon trama = new TramaIBeacon(mockFrame);

        // Call getMinor() and assert it returns the expected minor
        byte[] expectedMinor = new byte[2];
        expectedMinor[0] = 27;
        expectedMinor[1] = 28;
        assertArrayEquals(expectedMinor, trama.getMinor());
    }

    @Test
    public void testGetTxPower() {
        // Create a mock iBeacon frame
        byte[] mockFrame = new byte[30];
        for (int i = 0; i < 30; i++) {
            mockFrame[i] = (byte) i;
        }

        // Create a TramaIBeacon object using the mock frame
        TramaIBeacon trama = new TramaIBeacon(mockFrame);

        // Call getTxPower() and assert it returns the expected txPower
        byte expectedTxPower = 29;
        assertEquals(expectedTxPower, trama.getTxPower());
    }

    @Test
    public void testGetAdvFlags() {
        // Create a mock iBeacon frame
        byte[] mockFrame = new byte[30];
        for (int i = 0; i < 30; i++) {
            mockFrame[i] = (byte) i;
        }

        // Create a TramaIBeacon object using the mock frame
        TramaIBeacon trama = new TramaIBeacon(mockFrame);

        // Call getAdvFlags() and assert it returns the expected advFlags
        byte[] expectedAdvFlags = new byte[3];
        expectedAdvFlags[0] = 0;
        expectedAdvFlags[1] = 1;
        expectedAdvFlags[2] = 2;
        assertArrayEquals(expectedAdvFlags, trama.getAdvFlags());
    }

    @Test
    public void testGetAdvHeader() {
        // Create a mock iBeacon frame
        byte[] mockFrame = new byte[30];
        for (int i = 0; i < 30; i++) {
            mockFrame[i] = (byte) i;
        }

        // Create a TramaIBeacon object using the mock frame
        TramaIBeacon trama = new TramaIBeacon(mockFrame);

        // Call getAdvHeader() and assert it returns the expected advHeader
        byte[] expectedAdvHeader = new byte[2];
        expectedAdvHeader[0] = 3;
        expectedAdvHeader[1] = 4;
        assertArrayEquals(expectedAdvHeader, trama.getAdvHeader());
    }

    @Test
    public void testGetCompanyID() {
        // Create a mock iBeacon frame
        byte[] mockFrame = new byte[30];
        for (int i = 0; i < 30; i++) {
            mockFrame[i] = (byte) i;
        }

        // Create a TramaIBeacon object using the mock frame
        TramaIBeacon trama = new TramaIBeacon(mockFrame);

        // Call getCompanyID() and assert it returns the expected companyID
        byte[] expectedCompanyID = new byte[2];
        expectedCompanyID[0] = 5;
        expectedCompanyID[1] = 6;
        assertArrayEquals(expectedCompanyID, trama.getCompanyID());
    }

    @Test
    public void testGetIBeaconType() {
        // Create a mock iBeacon frame
        byte[] mockFrame = new byte[30];
        for (int i = 0; i < 30; i++) {
            mockFrame[i] = (byte) i;
        }

        // Create a TramaIBeacon object using the mock frame
        TramaIBeacon trama = new TramaIBeacon(mockFrame);

        // Call getIBeaconType() and assert it returns the expected iBeaconType
        byte expectedIBeaconType = 7;
        assertEquals(expectedIBeaconType, trama.getiBeaconType());
    }

    @Test
    public void testGetIBeaconLength() {
        // Create a mock iBeacon frame
        byte[] mockFrame = new byte[30];
        for (int i = 0; i < 30; i++) {
            mockFrame[i] = (byte) i;
        }

        // Create a TramaIBeacon object using the mock frame
        TramaIBeacon trama = new TramaIBeacon(mockFrame);

        // Call getIBeaconLength() and assert it returns the expected iBeaconLength
        byte expectedIBeaconLength = 8;
        assertEquals(expectedIBeaconLength, trama.getiBeaconLength());
    }

    @Test
    public void testGetLosBytes() {
        // Create a mock iBeacon frame
        byte[] mockFrame = new byte[30];
        for (int i = 0; i < 30; i++) {
            mockFrame[i] = (byte) i;
        }

        // Create a TramaIBeacon object using the mock frame
        TramaIBeacon trama = new TramaIBeacon(mockFrame);

        // Call getLosBytes() and assert it returns the expected losBytes
        byte[] expectedLosBytes = new byte[30];
        for (int i = 0; i < 30; i++) {
            expectedLosBytes[i] = (byte) i;
        }
        assertArrayEquals(expectedLosBytes, trama.getLosBytes());
    }

}