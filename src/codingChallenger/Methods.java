package codingChallenger;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Methods {
	
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
			if(input.charAt(i) == input.charAt(Methods.wrapAround(i,input.length()))){
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
	
	
	public static void day3(int stopping,HashMap<Point, Integer> spiral, Point lastPlaced){
		int num = spiral.get(lastPlaced) + 1;
		Point currpoint = new Point(lastPlaced);
		currpoint.x++;
		int layer = (int)currpoint.getX();
		while (!currpoint.equals(new Point(layer,layer))) {
			spiral.put(new Point((int)currpoint.getX(),(int)currpoint.getY()), num);
			num++;
			currpoint.y++;
		}
		while (!currpoint.equals(new Point(0-layer,layer))) {
			spiral.put(new Point((int)currpoint.getX(),(int)currpoint.getY()), num);
			num++;
			currpoint.x--;
		}
		while (!currpoint.equals(new Point(0-layer,0-layer))) {
			spiral.put(new Point((int)currpoint.getX(),(int)currpoint.getY()), num);
			num++;
			currpoint.y--;
		}
		while (!currpoint.equals(new Point(layer,0-layer))) {
			spiral.put(new Point((int)currpoint.getX(),(int)currpoint.getY()), num);
			num++;
			currpoint.x++;
		}
		spiral.put(new Point((int)currpoint.getX(),(int)currpoint.getY()), num);
		if(num >= stopping ){
			return;
		}
		
		day3(stopping,spiral,currpoint);
		
	}
	
	
	public static int day3Part2(int stopping,HashMap<Point, Integer> spiral, Point lastPlaced){
		int num;
		Point currpoint = new Point(lastPlaced);
		currpoint.x++;
		int layer = (int)currpoint.getX();
		while (!currpoint.equals(new Point(layer,layer))) {
			num = addUpSurround(spiral,currpoint);
			if(num > stopping ){
				return num;
			}
			spiral.put(new Point((int)currpoint.getX(),(int)currpoint.getY()), num);
			currpoint.y++;
		}
		while (!currpoint.equals(new Point(0-layer,layer))) {
			num = addUpSurround(spiral,currpoint);
			if(num > stopping ){
				return num;
			}
			spiral.put(new Point((int)currpoint.getX(),(int)currpoint.getY()), num);
			currpoint.x--;
		}
		while (!currpoint.equals(new Point(0-layer,0-layer))) {
			num = addUpSurround(spiral,currpoint);
			if(num > stopping ){
				return num;
			}
			spiral.put(new Point((int)currpoint.getX(),(int)currpoint.getY()), num);
			currpoint.y--;
		}
		while (!currpoint.equals(new Point(layer,0-layer))) {
			num = addUpSurround(spiral,currpoint);
			if(num > stopping ){
				return num;
			}
			spiral.put(new Point((int)currpoint.getX(),(int)currpoint.getY()), num);
			currpoint.x++;
		}
		num = addUpSurround(spiral,currpoint);
		
		if(num > stopping ){
			return num;
		}
		spiral.put(new Point((int)currpoint.getX(),(int)currpoint.getY()), num);

		
		return day3Part2(stopping,spiral,currpoint);
		
	}
	
	public static int addUpSurround(HashMap<Point, Integer> spiral, Point location){
		int ans = 0;
		if(spiral.get(new Point((int)location.getX() + 1,(int)location.getY())) != null){
			ans += spiral.get(new Point((int)location.getX() + 1,(int)location.getY()));
		}
		if(spiral.get(new Point((int)location.getX() - 1,(int)location.getY())) != null){
			ans += spiral.get(new Point((int)location.getX() - 1,(int)location.getY()));
		}
		if(spiral.get(new Point((int)location.getX(),(int)location.getY()+1)) != null){
			ans += spiral.get(new Point((int)location.getX(),(int)location.getY()+1));
		}
		if(spiral.get(new Point((int)location.getX(),(int)location.getY()-1)) != null){
			ans += spiral.get(new Point((int)location.getX(),(int)location.getY()-1));
		}
		
		if(spiral.get(new Point((int)location.getX()-1,(int)location.getY()-1)) != null){
			ans += spiral.get(new Point((int)location.getX()-1,(int)location.getY()-1));
		}
		
		if(spiral.get(new Point((int)location.getX()+1,(int)location.getY()-1)) != null){
			ans += spiral.get(new Point((int)location.getX()+1,(int)location.getY()-1));
		}
		
		if(spiral.get(new Point((int)location.getX()-1,(int)location.getY()+1)) != null){
			ans += spiral.get(new Point((int)location.getX()-1,(int)location.getY()+1));
		}
		
		if(spiral.get(new Point((int)location.getX()+1,(int)location.getY()+1)) != null){
			ans += spiral.get(new Point((int)location.getX()+1,(int)location.getY()+1));
		}

		return ans;
	}

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
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String s = "61697637962276641366442297247367117738114719863473648131982449728688116728695866572989524473392982963976411147683588415878214189996163533584547175794158118148724298832798898333399786561459152644144669959887341481968319172987357989785791366732849932788343772112176614723858474959919713855398876956427631354172668133549845585632211935573662181331613137869866693259374322169811683635325321597242889358147123358117774914653787371368574784376721652181792371635288376729784967526824915192526744935187989571347746222113625577963476141923187534658445615596987614385911513939292257263723518774888174635963254624769684533531443745729344341973746469326838186248448483587477563285867499956446218775232374383433921835993136463383628861115573142854358943291148766299653633195582135934544964657663198387794442443531964615169655243652696782443394639169687847463721585527947839992182415393199964893658322757634675274422993237955354185194868638454891442893935694454324235968155913963282642649968153284626154111478389914316765783434365458352785868895582488312334931317935669453447478936938533669921165437373741448378477391812779971528975478298688754939216421429251727555596481943322266289527996672856387648674166997731342558986575258793261986817177487197512282162964167151259485744835854547513341322647732662443512251886771887651614177679229984271191292374755915457372775856178539965131319568278252326242615151412772254257847413799811417287481321745372879513766235745347872632946776538173667371228977212143996391617974367923439923774388523845589769341351167311398787797583543434725374343611724379399566197432154146881344528319826434554239373666962546271299717743591225567564655511353255197516515213963862383762258959957474789718564758843367325794589886852413314713698911855183778978722558742329429867239261464773646389484318446574375323674136638452173815176732385468675215264736786242866295648997365412637499692817747937982628518926381939279935993712418938567488289246779458432179335139731952167527521377546376518126276";
		String matrix = "4168,3925,858,2203,440,185,2886,160,1811,4272,4333,2180,174,157,361,1555;150,111,188,130,98,673,408,632,771,585,191,92,622,158,537,142;5785,5174,1304,3369,3891,131,141,5781,5543,4919,478,6585,116,520,673,112;5900,173,5711,236,2920,177,3585,4735,2135,2122,5209,265,5889,233,4639,5572;861,511,907,138,981,168,889,986,980,471,107,130,596,744,251,123;2196,188,1245,145,1669,2444,656,234,1852,610,503,2180,551,2241,643,175;2051,1518,1744,233,2155,139,658,159,1178,821,167,546,126,974,136,1946;161,1438,3317,4996,4336,2170,130,4987,3323,178,174,4830,3737,4611,2655,2743;3990,190,192,1630,1623,203,1139,2207,3994,1693,1468,1829,164,4391,3867,3036;116,1668,1778,69,99,761,201,2013,837,1225,419,120,1920,1950,121,1831;107,1006,92,807,1880,1420,36,1819,1039,1987,114,2028,1771,25,85,430;5295,1204,242,479,273,2868,3453,6095,5324,6047,5143,293,3288,3037,184,987;295,1988,197,2120,199,1856,181,232,564,1914,1691,210,1527,1731,1575,31;191,53,714,745,89,899,854,679,45,81,726,801,72,338,95,417;219,3933,6626,2137,3222,1637,5312,238,5895,222,154,6649,169,6438,3435,4183;37,1069,166,1037,172,258,1071,90,497,1219,145,1206,143,153,1067,510";
		HashMap<Point, Integer> spiral = new HashMap<>();
		spiral.put(new Point(0,0), 1);
		Methods.day4Part2();
	}

}
