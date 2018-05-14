package codingChallenger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day8 {

	static HashMap<String, Integer> registers = new HashMap<>();
	static int allTimeMax = Integer.MIN_VALUE;
	

	public static void day8(String input) {
		List<String> Lines = Arrays.asList(input.split(";"));
		for (String instruction : Lines) {
			String[] parsed = instruction.split(" ");
			String reg = parsed[0];

			if (!registers.containsKey(reg)) {
				registers.put(reg, 0);
			}

			if (checkCond(parsed)) {
				if (checkOp(parsed)) {
					registers.put(reg, registers.get(reg) + new Integer(parsed[2]));
				} else {
					registers.put(reg, registers.get(reg) - new Integer(parsed[2]));
				}

			}
			allTimeMax = Math.max(allTimeMax, trackMax());
		}
		System.out.println("part1: " + trackMax());
		System.out.println("part2: " + allTimeMax);
	}

	public static boolean checkCond(String[] cond) {
		try {
			switch (cond[5]) {
			case "==":
				return registers.get(cond[4]) == Integer.parseInt(cond[6]);

			case "<":
				return registers.get(cond[4]) < Integer.parseInt(cond[6]);

			case ">":
				return registers.get(cond[4]) > Integer.parseInt(cond[6]);

			case "<=":
				return registers.get(cond[4]) <= Integer.parseInt(cond[6]);

			case ">=":
				return registers.get(cond[4]) >= Integer.parseInt(cond[6]);

			case "!=":
				return registers.get(cond[4]) != Integer.parseInt(cond[6]);

			default:
				System.out.println("unhandleable cond" + cond[5]);
				break;
			}
		} catch (NullPointerException E) {
			registers.put(cond[4], 0);
			return checkCond(cond);
		}
		return true;
	}
	

	// true for inc
	// false for dec
	public static boolean checkOp(String[] op) {
		if (op[1].equals("inc")) {
			return true;
		}
		return false;
	}

	public static int trackMax() {
		int max = Integer.MIN_VALUE;
		for (String key : registers.keySet()) {
			max  = Math.max(registers.get(key), max);
		}
		return max;
	}
	
	public static void dumpRegisters() {
		for (String key : registers.keySet()) {
			System.out.println(key + " : " + registers.get(key));
		}
	}
	

}
