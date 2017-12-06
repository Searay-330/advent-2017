package codingChallenger;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Day2 {
	public static void day2(ArrayList<ArrayList<Integer>> input){
		int ans = 0;
		for (int i = 0; i < input.size(); i++) {
			ans += Collections.max(input.get(i)) - Collections.min(input.get(i));
		}
		System.out.println(ans);
	}
	
	public static void day2Part2(ArrayList<ArrayList<Integer>> input){
		int ans = 0;
		for (int i = 0; i < input.size(); i++) {
			ans += findDivs(input.get(i));
		}
		System.out.println(ans);
	}
	
	public static int findDivs(ArrayList<Integer> input){
		for (int i = 0; i < input.size(); i++) {
			for (int j = 0; j < input.size(); j++) {
				if(j == i){
					continue;
				}
				if(input.get(i)%input.get(j) == 0 || input.get(j)%input.get(i) == 0){
					return Integer.max(input.get(i), input.get(j))/ Integer.min(input.get(i), input.get(j));
				}
			}
		}
		return 0;
	}
	
	
	public static ArrayList<ArrayList<Integer>> makeMatrix(String input){
		ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
		String[] level1 = input.split(";");
		
		for(int i = 0; i < level1.length; ++i){
			String[] level2 = level1[i].split(",");
			ArrayList<Integer> f = new ArrayList<Integer>();
			for (int j = 0; j < level2.length; j++) {
				f.add(new Integer(level2[j]));
			}
			ans.add(f);
		}
		
		return ans;
	}
}
