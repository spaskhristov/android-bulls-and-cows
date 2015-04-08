package com.gmail.spaskhristov.bullsandcows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
	protected Random rn = new Random();
	protected String guessNum;
	protected String secretNum;
	protected String secretNumAfterBulls;
	protected int countBulls = 0;
	protected int countCows = 0;
	protected boolean gameEnd = false;

	public Game() {
		this.secretNum = secretNum();
	}

	protected void setCountBulls() {
		this.countBulls = 0;
		StringBuilder sb = new StringBuilder();
		this.secretNumAfterBulls = this.secretNum;
		for (int i = 0; i < this.secretNum.length(); i++) {
			if (this.guessNum.charAt(i) == this.secretNum.charAt(i)) {
				sb.append('*');
				this.countBulls++;
			} else {
				sb.append(this.secretNum.charAt(i));
			}
		}
		this.secretNumAfterBulls = sb.toString();
		if (this.countBulls == 4) {
			this.gameEnd = true;
		}
	}

	protected void setCountCows() {
		this.countCows = 0;
		for (int i = 0; i < this.secretNumAfterBulls.length(); i++) {
			for (int j = 0; j < this.guessNum.length(); j++) {
				if (j != i
						&& this.secretNumAfterBulls.charAt(i) == this.guessNum
								.charAt(j)) {
					this.countCows++;
				}
			}
		}
	}

	protected String secretNum() {
		List<Integer> number = new ArrayList<Integer>();
		int index = 0;
		while (index < 4) {
			int digit = Integer.parseInt(randomDigit());
			if (!number.contains(digit)) {
				number.add(digit);
				index++;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int digit : number) {
			sb.append(digit);
		}

		return sb.toString();
	}

	protected String randomDigit() {
		int min = 0;
		int max = 9;
		int randomDigit = rn.nextInt((max - min) + 1) + min;
		String randomDigitStr = randomDigit + "";
		return randomDigitStr;
	}

	public boolean setGuessNum(String guessNum) {
		boolean uniqueDigits = true;
		if (!guessNum.matches("^(?!.*(.).*\\1)\\d{4}")) {
			uniqueDigits = false;
			return uniqueDigits;
		}
		this.guessNum = guessNum;
		setCountBulls();
		setCountCows();
		return uniqueDigits;
	}

	public String getCountBulls() {
		return String.valueOf(this.countBulls);
	}

	public String getCountCows() {
		return String.valueOf(this.countCows);
	}

	public boolean getGameEnd() {
		return this.gameEnd;
	}
}
