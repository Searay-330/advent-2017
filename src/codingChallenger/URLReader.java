package codingChallenger;

import java.net.*;
import java.io.*;

public class URLReader {
	public static void test(){
		try{
		URL oracle = new URL("http://www.oracle.com/");
		BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

		String inputLine;
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		in.close();
	}catch (Exception e) {
		// TODO: handle exception
	}
	}
}
