package codingChallenger;

import java.util.ArrayList;

public abstract class Day5 {
	
	public static void day5(ArrayList<String> steps){
		int ans = 0;
		int indexCheck = 0;
		try{
			while(true){
				
				steps.set(indexCheck, (Integer.parseInt(steps.get(indexCheck)) + 1) + "");
				indexCheck += Integer.parseInt(steps.get(indexCheck)) - 1;
				ans++;
			}
		}catch (IndexOutOfBoundsException e) {
			System.out.println(ans);
		}

		
	}
	
}
