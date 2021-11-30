import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Message {

	public String MessageOfTheDay() throws IOException {
		
		String code = GetHTTP("http://cswebcat.swansea.ac.uk/puzzle");
		
		String passcode = decode(code);
		
		String message = GetHTTP("http://cswebcat.swansea.ac.uk/message?solution=" + passcode);
		
		message = message.substring(0, message.indexOf('.') + 1);
	
		return message;
	}
	
	public String GetHTTP(String link) throws IOException {
		
		URL url = new URL(link);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
		InputStreamReader instream  = new InputStreamReader(con.getInputStream());
		Scanner scan = new Scanner(instream);
		
		String code = scan.nextLine();
		
		return code;
	}
	
	public static String decode(String code) {
		
		String passcode = "";
	
		for(int i = 0; i < code.length(); i ++) {
			passcode += cipher(code.charAt(i), i + 1);
		}
	
		passcode = passcode + "CS-230";
		passcode = passcode.length() + passcode;
		
		return passcode;
	}
	
	public static char cipher(char c, int shift) {
		
		if(shift % 2 != 0) {
			if(c - shift < 65) {
				c = (char) ((c - shift) + 26);
			} else {
				c -= shift;
			}
		} else {
			if(c + shift > 90) {
				c = (char) ((c + shift) - 26);
			} else {
				c += shift;
			}
		}
		return c;
	}
}
