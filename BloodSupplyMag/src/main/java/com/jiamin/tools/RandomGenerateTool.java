package com.jiamin.tools;

import java.util.Random;

public class RandomGenerateTool {
	// use for generate name
	public RandomGenerateTool(){}
	
	public String generateString(int length) {
		Random rng = new Random();
		String characters = "qwertyuiopasdfghjklzxcvbnm";
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rng.nextInt(characters.length()));
		}
		text[0] = (char) (text[0] - 32);
		return new String(text);
	}

	public int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	public String randBloodType() {
		Random rand = new Random();
		int j = rand.nextInt(4);
		switch (j) {
		case 0:
			return "A";
		case 1:
			return "B";
		case 2:
			return "AB";
		default:
			return "O";
		}
	}

	public String randHemo() {
		Random rand = new Random();
		int i = rand.nextInt(10000);
		if (i < 9000) {
			return "Normal";
		} else {
			return "Low";
		}
	}

	public String randill() {
		Random rand = new Random();
		int i = rand.nextInt(100);
		if (i < 90) {
			return "No";
		} else {
			return "Yes";
		}
	}

	public double randDouble(double min, double max) {
		Random rand = new Random();
		double randomDoubleRate = rand.nextDouble();
		double randomDouble = randomDoubleRate * max + (1 - randomDoubleRate) * min;
		return randomDouble;
	}

	public boolean randBool() {
		Random rand = new Random();
		boolean randBool = rand.nextBoolean();
		return randBool;
	}

	public boolean randSmoker() {
		Random rand = new Random();
		int i = rand.nextInt(10);
		if (i < 7)
			return false;
		else
			return true;
	}

	public String randDiabetes() {
		Random rand = new Random();
		int i = rand.nextInt(100);
		if (i < 90)
			return "No";
		else
			return "Yes";
	}
}
