package codingChallenger;

import java.awt.Point;
import java.util.HashMap;

public abstract class Day3 {
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
	
	public static void day3(int stoppingPoint){
		HashMap<Point, Integer> spiral = new HashMap<Point, Integer>();
		spiral.put(new Point(0,0), 1);
		day3(stoppingPoint,spiral,new Point(0,0));
		for (Point p : spiral.keySet()) {
			if(spiral.get(p) == stoppingPoint){
				System.out.println((int)(Math.abs(p.getX()) + Math.abs(p.getY())));
				return;
			}
		}
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
	
	public static void day3Part2(int stoppingPoint){
		HashMap<Point, Integer> spiral = new HashMap<Point, Integer>();
		spiral.put(new Point(0,0), 1);
		System.out.println(day3Part2(stoppingPoint,spiral,new Point(0,0)));
		
	}

}
