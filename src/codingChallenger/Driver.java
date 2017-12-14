package codingChallenger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Driver {
	public static String readFile(String input) {
		try {
			FileReader inFile = new FileReader(Paths.get("data/" + input).toAbsolutePath().normalize().toString());
			BufferedReader inStream = new BufferedReader(inFile);
			String out= inStream.readLine();

			inStream.close();
			return out;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String readMultiLineFile(String input) {
		try {
			FileReader inFile = new FileReader(Paths.get("data/" + input).toAbsolutePath().normalize().toString());
			BufferedReader inStream = new BufferedReader(inFile);
			String inString;
			String out= "";

			while ((inString = inStream.readLine()) != null) {
				
				out += inString + ";";
			}
			out.substring(0, out.length() - 1);
			inStream.close();
			return out;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Day1.day1(readFile("input1.txt"));
		Day1.day1Part2(readFile("input1.txt"));
		Day2.day2(Day2.makeMatrix(readFile("input2.txt")));
		Day2.day2Part2(Day2.makeMatrix(readFile("input2.txt")));
		Day3.day3(289326);
		Day3.day3Part2(289326);
		Day4.day4();
		Day4.day4Part2();
		Day5.day5(new ArrayList<String>(Arrays.asList(readMultiLineFile("input5.txt").split(";"))));
		Day5.day5Part2(new ArrayList<String>(Arrays.asList(readMultiLineFile("input5.txt").split(";"))));
		Day6.day6("input6.txt");
		Day6.day6Part2("input6.txt");
		Day7.day7("input7.txt");
		Day7.day7Part2("input7.txt");
	}

}
