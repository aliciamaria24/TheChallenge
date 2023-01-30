package com.example.RPrototype;

import com.fazecast.jSerialComm.SerialPort;

public class COMPortReader {
    private SerialPort comPort;
    private int baudRate;

    public COMPortReader(String portName, int baudRate) {
        this.comPort = SerialPort.getCommPort(portName);
        this.baudRate = baudRate;
    }

    public void openPort() {
        if (!comPort.openPort()) {
            System.out.println("Failed to open port " + comPort.getSystemPortName());
            return;
        }

        comPort.setBaudRate(baudRate);
    }

    public void closePort() {
        comPort.closePort();
    }

    public int readData() {
        byte[] buffer = new byte[1000000];
        int bytesRead = 0;
        while (bytesRead == 0) {
            bytesRead = comPort.readBytes(buffer, buffer.length);

        }

        String data = new String(buffer, 0, bytesRead);
        String[] lines = data.split("\n");
        String latestLine = lines[lines.length - 1];
        String number = latestLine.split(" ")[0];
        return Integer.parseInt(number);
    }
}
