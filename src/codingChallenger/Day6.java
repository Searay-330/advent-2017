package codingChallenger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public abstract class Day6 {
	static HashMap<String, Integer> seen = new HashMap<String,Integer>();

	public static void day6(ArrayList<Integer> input) {
		seen.clear();
		boolean seenIt = true;
		int times = 0;
		while(seenIt){
			rotate(input.indexOf(Collections.max(input)), input);
			seenIt = checkSeen(input, 0);
			times++;
		}

		System.out.println(times);
	}
	
	public static void day6Part2(ArrayList<Integer> input) {
		seen.clear();
		boolean seenIt = true;
		int times = 0;
		while(seenIt){
			rotate(input.indexOf(Collections.max(input)), input);
			seenIt = checkSeen(input, times);
			times++;
		}
		System.out.println(--times - seen.get(ArrayToString(input)));
	}
	
	public static void rotate(int index, ArrayList<Integer> input){
		int value = input.get(index);
		input.set(index,0);
		index++;
		for(int i = value; i > 0; i--){
			if(index >= input.size()){
				index = 0;
			}
			input.set(index, input.get(index) + 1);
			index++;
		}
	}
	
	public static boolean checkSeen(ArrayList<Integer> input, int val){
		return (seen.putIfAbsent(ArrayToString(input), val) == null) ? true : false;
	}
	
	
	

	public static String ArrayToString(ArrayList<Integer> input){
		String temp = "";
		for (int i = 0; i < input.size(); i++) {
			temp += input.get(i);
		}
		return temp;
	}
	

	
	
	
	
	public static void day6(String inputFile) {
		ArrayList<Integer> input = new ArrayList<>();
		try {
			FileReader inFile = new FileReader(Paths.get("data/" + inputFile).toAbsolutePath().normalize().toString());
			BufferedReader inStream = new BufferedReader(inFile);
			String inString;

			while ((inString = inStream.readLine()) != null) {
				input.add(Integer.parseInt(inString));

			}
			inStream.close();

		} catch (IOException e) {
			e.printStackTrace();

		}
		day6(input);

	}
	
	public static void day6Part2(String inputFile) {
		ArrayList<Integer> input = new ArrayList<>();
		try {
			FileReader inFile = new FileReader(Paths.get("data/" + inputFile).toAbsolutePath().normalize().toString());
			BufferedReader inStream = new BufferedReader(inFile);
			String inString;

			while ((inString = inStream.readLine()) != null) {
				input.add(Integer.parseInt(inString));

			}
			inStream.close();

		} catch (IOException e) {
			e.printStackTrace();

		}
		day6Part2(input);

	}
	

}
