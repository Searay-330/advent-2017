package codingChallenger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public abstract class Day7 {

	public static String day7(String inputFile) {
		ArrayList<String> nodes = new ArrayList<String>();
		ArrayList<String> bads = new ArrayList<String>();
		try {
			FileReader inFile = new FileReader(Paths.get("data/" + inputFile).toAbsolutePath().normalize().toString());
			BufferedReader inStream = new BufferedReader(inFile);
			String inString;

			while ((inString = inStream.readLine()) != null) {
				String[] line = inString.replaceAll("[^a-z->,]", "").split("[->]");
				nodes.add(line[0]);
				if (line.length != 1) {
					bads.addAll(Arrays.asList(line[2].split(",")));
				}
			}
			inStream.close();

		} catch (IOException e) {
			e.printStackTrace();

		}
		for (String string : bads) {
			nodes.remove(string);
		}
		System.out.println(nodes.get(0));
		return nodes.get(0);
	}

	public static void day7Part2(String inputFile) {
		HashMap<String, Integer> nodes = new HashMap<String, Integer>();
		HashMap<String, ArrayList<String>> graph = new HashMap<String, ArrayList<String>>();
		try {
			FileReader inFile = new FileReader(Paths.get("data/" + inputFile).toAbsolutePath().normalize().toString());
			BufferedReader inStream = new BufferedReader(inFile);
			String inString;

			while ((inString = inStream.readLine()) != null) {
				String[] line = inString.replaceAll("[^a-z0-9->, ]", "").split("[->]");
				String[] temp = line[0].split(" ");
				nodes.put(temp[0], Integer.parseInt(temp[1]));
				if (line.length > 1) {
					ArrayList<String> temp2 = new ArrayList<String>();
					temp2.addAll(Arrays.asList(line[2].replaceAll(" ", "").split(",")));
					graph.put(temp[0], temp2);
				}
			}
			inStream.close();

		} catch (IOException e) {
			e.printStackTrace();

		}
	
		System.out.println(solve(day7(inputFile), nodes, graph));
	}

	public static int calcweight(String node, HashMap<String, Integer> nodes, HashMap<String, ArrayList<String>> graph) {
		if (!graph.containsKey(node)) {
			return nodes.get(node);
		}
		int sum = 0;
		for (String s : graph.get(node)) {
			sum += calcweight(s, nodes, graph);
		}
		return sum + nodes.get(node);
	}
	
	public static Integer solve(String node, HashMap<String, Integer> nodes, HashMap<String, ArrayList<String>> graph){
		ArrayList<Integer> totals = new ArrayList<>();
		for (String s : graph.get(node)) {
			totals.add(calcweight(s, nodes, graph));
		}
		for (int i = 0; i < totals.size(); ++i) {
			if(Collections.frequency(totals, totals.get(i)) == 1){
				Integer ans = solve(graph.get(node).get(i),nodes,graph);
				if(ans != null){
					return ans;
				}
				if(i-1 >= 0){
					return nodes.get(graph.get(node).get(i)) - Math.abs(totals.get(i-1) - totals.get(i));
				}else{
					return nodes.get(graph.get(node).get(i)) - Math.abs(totals.get(i+1) - totals.get(i));
				}
			}
		}
		return null;
		
	}
}
