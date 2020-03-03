import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.fazecast.jSerialComm.SerialPort;

public class PortModule {
	
	private InputStream in;
	private SerialPort comPort;
	private boolean open;
	
	public PortModule() {
		comPort = SerialPort.getCommPorts()[0]; 
        comPort.openPort();
       	comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
       	in = comPort.getInputStream();
       	open = true;
	}
	
	/* Close the port when done with the module*/
	public void closePort() {
		open = false;
		comPort.closePort();
	}
	
	public InputStream getStream() {
		return in;
	}
	
	/* Reads n characters from the serial port stream and returns the results in an Array */
	public ArrayList<Character> readNTimes(int n) throws Exception{
		if(open == false) {//Check that the port is open for reading
			System.out.println("Port is closed!");
			return null;
		}
		ArrayList<Character> ret = new ArrayList<Character>();
		for(int i = 0; i < n; i++) {
			try {
				ret.add((char)in.read());
			} catch (IOException e) {
				comPort.closePort(); //If something goes wrong, close the port
				e.printStackTrace();
			}
		}
		return ret;
	}
	
	/* IDEAS FOR LATER MAYBE */
	// Read every n seconds???
	// Data remaining???
}
