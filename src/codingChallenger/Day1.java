package codingChallenger;

public abstract class Day1 {
	public static void day1(String input){
		int ans = 0;
		for(int i = 0; i <input.length(); i++){
			if(i+1 == input.length()){
				if(input.charAt(0) == input.charAt(i)){
					ans += Integer.parseInt(input.charAt(0) + "");
					break;
				}
			}
			if(input.charAt(i) == input.charAt(i + 1)){
				ans += Integer.parseInt(input.charAt(i) + "");
			}
		}
		
		System.out.println(ans);
	}
	
	public static void day1Part2(String input){
		int ans = 0;
		for(int i = 0; i <input.length(); i++){
			if(input.charAt(i) == input.charAt(wrapAround(i,input.length()))){
				ans += Integer.parseInt(input.charAt(i) + "");
			}
		}
		
		System.out.println(ans);
	}
	
	public static int wrapAround(int i, int max){
		if(i+(max/2) >= max){
			return (i+(max/2)-max);
		}
		return i+max/2;
	}
}
