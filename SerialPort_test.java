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
        PortModule ard = new PortModule();
        System.out.println(ard.printNTimes(10));
}
