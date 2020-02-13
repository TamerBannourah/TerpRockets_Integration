/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialport;

import com.fazecast.jSerialComm.*;
import java.io.InputStream;
/**
 *
 * @author eatoc
 */
public class SerialPort_test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SerialPort comPort = SerialPort.getCommPorts()[0];
        comPort.openPort();
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        InputStream in = comPort.getInputStream();
        try
        {
           for (int j = 0; j < 1000; ++j)
              System.out.print((char)in.read());
           in.close();
        } catch (Exception e) { e.printStackTrace(); }
            comPort.closePort();
        }  
}
