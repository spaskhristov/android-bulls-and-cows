package com.gmail.spaskhristov.bullsandcows;

import java.util.ArrayList;
import java.util.List;

public class GameABC extends Game {

	public GameABC() {
		super();
	}

	@Override
	protected String secretNum() {
		List<String> StrArray = new ArrayList<String>();
		int index = 0;
		while (index < 4) {
			String ch = randomDigit();
			if (!StrArray.contains(ch)) {
				StrArray.add(ch);
				index++;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (String digit : StrArray) {
			sb.append(digit);
		}

		return sb.toString();
	}

	@Override
	protected String randomDigit() {
		char min = 'A';
		char max = 'Z';
		char randomChar = (char) ((int) min + Math.random()
				* ((int) max - (int) min + 1));
		return String.valueOf(randomChar);
	}

	@Override
	public boolean setGuessNum(String guessNum) {

		boolean uniqueDigits = true;
		if (!guessNum.matches("^(?!.*(.).*\\1)\\w{4}")) {
			uniqueDigits = false;
			return uniqueDigits;
		}
		this.guessNum = guessNum;
		setCountBulls();
		setCountCows();
		return uniqueDigits;
	}
}
