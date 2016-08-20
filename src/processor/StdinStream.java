package processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StdinStream  implements DataStream {

	public BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	@Override
	public String read() {
		try {
			if(br.ready()) {
				return br.readLine();
			}
			else {
				return null;
			}
		}
		catch(IOException e) {
			return null;
		}
	}

}
