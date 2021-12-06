import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Class to get the message of the day from the given website URL by decoding the result of a GET request to the website
 * and returning the result.
 *
 * @author Patel
 * @version 1.0
 */
public class Message {

	/**
	 * Method to get the message of the day.
	 *
	 * @return The message of the day.
	 * @throws IOException Throws an IOException if the HTTP request fails.
	 */
	public String MessageOfTheDay() throws IOException {
		
		String code = GetHTTP("http://cswebcat.swansea.ac.uk/puzzle");
		
		String passcode = decode(code);
		
		String message = GetHTTP("http://cswebcat.swansea.ac.uk/message?solution=" + passcode);
		
		message += " - MaRATma Ghandi";
	
		return message;
	}

	/**
	 * Method to issue a GET request to a given url.
	 *
	 * @param link Website URL
	 * @return returns the result of the GET request
	 * @throws IOException Throws an IOException if the HTTP request fails.
	 */
	public String GetHTTP(String link) throws IOException {
		
		URL url = new URL(link);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
		InputStreamReader instream  = new InputStreamReader(con.getInputStream());
		Scanner scan = new Scanner(instream);

		return scan.nextLine();
	}

	/**
	 * Method to decode a given String with a shift value of 1.
	 *
	 * @param code Cipher text to be decoded.
	 * @return returns the result of decoding the string.
	 */
	public static String decode(String code) {
		
		String passcode = "";
	
		for(int i = 0; i < code.length(); i ++) {
			passcode += cipher(code.charAt(i), i + 1);
		}
	
		passcode = passcode + "CS-230";
		passcode = passcode.length() + passcode;
		
		return passcode;
	}

	/**
	 * Method to decipher a given character by shifting it by a given value.
	 *
	 * @param c Character to be shifted.
	 * @param shift Amount to shift the character by.
	 * @return Deciphered character.
	 */
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
