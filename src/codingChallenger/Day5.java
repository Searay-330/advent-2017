package codingChallenger;

import java.util.ArrayList;

public abstract class Day5 {
	
	public static void day5(ArrayList<String> steps){
		int ans = 0;
		int indexCheck = 0;
		try{
			while(true){
				int number = Integer.parseInt(steps.get(indexCheck));
				steps.set(indexCheck,  (number + 1) + "");
				indexCheck += number;
				ans++;
			}
		}catch (IndexOutOfBoundsException e) {
			System.out.println(ans);
		}

		
	}
	
	public static void day5Part2(ArrayList<String> steps){
		int ans = 0;
		int indexCheck = 0;
		try{
			while(true){
				int number = Integer.parseInt(steps.get(indexCheck));
				steps.set(indexCheck,  ((number >= 3)? number - 1 : number + 1) + "");
				indexCheck += number;
				ans++;
			}
		}catch (IndexOutOfBoundsException e) {
			System.out.println(ans);
		}

		
	}
	
}
