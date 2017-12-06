package codingChallenger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public abstract class Day4 {
	public static void day4() {
		try {
			FileReader inFile = new FileReader(Paths.get("data/input4.txt").toAbsolutePath().normalize().toString());
			BufferedReader inStream = new BufferedReader(inFile);
			String inString;
			int ans = 0;

			while ((inString = inStream.readLine()) != null) {
				List<String> data =  Arrays.asList(inString.split(" "));
				HashSet<String> test = new HashSet<String>();
				for (String string : data) {
					if (!test.add(string)) {
						ans--;
						break;
					}
				}
				ans++;
			}
			inStream.close();
			System.out.println(ans);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void day4Part2() {
		try {
			FileReader inFile = new FileReader(Paths.get("data/input4.txt").toAbsolutePath().normalize().toString());
			BufferedReader inStream = new BufferedReader(inFile);
			String inString;
			int ans = 0;

			while ((inString = inStream.readLine()) != null) {
				List<String> data =  Arrays.asList(inString.split(" "));
				HashSet<String> test = new HashSet<String>();
				for (String string : data) {
					if (!checkStrings(test, string) || !test.add(string)) {
						ans--;
						break;
					}
				}
				ans++;
			}
			inStream.close();
			System.out.println(ans);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static boolean checkStrings(HashSet<String> set, String test){
		for (String string : set) {
			if(XORString(string, test) == 0){
				return false;
			}
		}
		return true;
	}
	
	public static int XORString(String a,String b){
		int ans = 0;
		if(a.length() != b.length()){
			return Math.abs(a.length() - b.length());
		}
		byte[] temp = a.getBytes();
		Arrays.sort(temp);
		a = new String(temp);
		temp = b.getBytes();
		Arrays.sort(temp);
		b = new String(temp);
		for(int i = 0; i < a.length(); i++){
			ans += a.charAt(i) ^ b.charAt(i);
		}
		return ans;
	}
	
}
