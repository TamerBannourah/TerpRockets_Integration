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
	
	public void closePort() {
		open = false;
		comPort.closePort();
	}
	
	public InputStream getStream() {
		return in;
	}
	
	public ArrayList<Character> readNTimes(int n) {
		if(open == false)
			throw new Exception("Port is closed!");
		ArrayList<Character> ret = new ArrayList<Character>();
		for(int i = 0; i < n; i++) {
			try {
				ret.add((char)in.read());
			} catch (IOException e) {
				comPort.closePort();
				e.printStackTrace();
			}
		}
		return ret;
	}
	
	// Read every n seconds???
	// Data remaining???
}
