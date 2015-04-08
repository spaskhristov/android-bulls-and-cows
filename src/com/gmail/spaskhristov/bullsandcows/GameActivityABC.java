package com.gmail.spaskhristov.bullsandcows;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;

public class GameActivityABC extends Activity implements OnClickListener  {

	private String msg = "Android Game ABC: ";
	private Button btnOk;
	private NumberPicker[] guessNum = new NumberPicker[4];
	private TableLayout tableResult;
	private Game game;
	private int turn = 0;
	private SharedPreferences mPrefs;
	private final String KEY_SCORE = "scoreABC";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_abc);

		this.btnOk = (Button) findViewById(R.id.btnNewTurnABC);
		this.btnOk.setOnClickListener(this);
		this.tableResult = (TableLayout) findViewById(R.id.tableResultABC);
		this.guessNum[0] = (NumberPicker) findViewById(R.id.charPicker1);
		this.guessNum[1] = (NumberPicker) findViewById(R.id.charPicker2);
		this.guessNum[2] = (NumberPicker) findViewById(R.id.charPicker3);
		this.guessNum[3] = (NumberPicker) findViewById(R.id.charPicker4);

		this.mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

		this.game = new GameABC();

		this.setNumPicker();

		Log.d(msg, "The onCreate() event");

	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(this.msg, "The onStart() event");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(this.msg, "The onResume() event");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(this.msg, "The onPause() event");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(this.msg, "The onStop() event");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(this.msg, "The onDestroy() event");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnNewTurnABC:
			if (!game.getGameEnd()) {
				this.fillTableResult();
			} else {
				Intent intent = new Intent(this, GameActivityABC.class);
				startActivity(intent);
			}
			break;
		default:
			break;
		}
	}

	private void setNumPicker() {
		String strArray[] = new String[26];
		for (int j = 0; j < 26; j++) {
			strArray[j] = "" + (char) (j + 65);
		}
		for (int i = 0; i < this.guessNum.length; i++) {
			this.guessNum[i].setMinValue(0);
			this.guessNum[i].setMaxValue(25);
			this.guessNum[i].setWrapSelectorWheel(true);
			this.guessNum[i].setDisplayedValues(strArray);
		} 
	}

	private void fillTableResult() {
		String geuss = "";
		for (int i = 0; i < this.guessNum.length; i++) {
			geuss += (char) (this.guessNum[i].getValue() + 65);
		}
		this.game.setGuessNum(geuss);
		if (this.game.setGuessNum(geuss)) {
			this.btnOk.setText("OK");
			if (this.game.getGameEnd()) {
				this.gameEnd();
			} else {
				this.gameContinues(geuss);
			}
		} else {
			this.alertMessage();
		}
	}

	private void gameEnd() {
		SharedPreferences.Editor editor = this.mPrefs.edit();
		int currBestScore = Integer.MAX_VALUE;
		if (this.mPrefs.contains(this.KEY_SCORE)) {
			currBestScore = this.mPrefs.getInt(this.KEY_SCORE, 0);
		} else {
			editor.putInt(this.KEY_SCORE, (turn + 1));
			editor.commit();
		}

		Intent intent = new Intent(this, EndGameActivity.class);

		if ((turn + 1) <= currBestScore) {
			editor.putInt(this.KEY_SCORE, (turn + 1));
			editor.commit();
			intent.putExtra(
					"USER_WIN",
					"Best Score Ever!!! "
							+ this.mPrefs.getInt(this.KEY_SCORE, 0));
		} else {
			intent.putExtra("USER_WIN", "Your Score is " + (this.turn + 1)
					+ " Best Score is " + this.mPrefs.getInt(this.KEY_SCORE, 0));
		}

		startActivity(intent);

	}

	private void alertMessage() {
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("ERROR...");
		alertDialog.setMessage("You must enter 4 different letters!!!");
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			// here you can add functions
			}
		});
		alertDialog.show();
	}
	
	private void gameContinues(String geuss) {
		this.turn++;
		TableRow tr = new TableRow(this);
		TableLayout.LayoutParams tableRowParams = new TableLayout.LayoutParams(
				TableLayout.LayoutParams.WRAP_CONTENT,
				TableLayout.LayoutParams.WRAP_CONTENT);

		int leftMargin = 0;
		int topMargin = 0;
		int rightMargin = 0;
		int bottomMargin = 0;

		tableRowParams.setMargins(leftMargin, topMargin, rightMargin,
				bottomMargin);
		tr.setLayoutParams(tableRowParams);
		tr.setId(10);
		tr.setBackgroundColor(Color.WHITE);

		TextView label_turn = new TextView(this);
		label_turn.setWidth(tr.getMeasuredWidth() / 4);
		label_turn.setId(20);
		label_turn.setText(String.valueOf(turn));
		label_turn.setGravity(Gravity.CENTER);
		tr.addView(label_turn);

		TextView label_guess = new TextView(this);
		label_guess.setWidth(tr.getMeasuredWidth() / 4);
		label_guess.setId(21);
		label_guess.setText(geuss);
		label_guess.setGravity(Gravity.CENTER);
		tr.addView(label_guess);

		TextView label_bulls = new TextView(this);
		label_bulls.setWidth(tr.getMeasuredWidth() / 4);
		label_bulls.setId(22);
		label_bulls.setText(game.getCountBulls());
		label_bulls.setGravity(Gravity.CENTER);
		tr.addView(label_bulls);

		TextView label_cows = new TextView(this);
		label_cows.setWidth(tr.getMeasuredWidth() / 4);
		label_cows.setId(23);
		label_cows.setText(game.getCountCows());
		label_cows.setGravity(Gravity.CENTER);

		tr.addView(label_cows);// add the column to the table row here
		this.tableResult.addView(tr, 0, tableRowParams);
	}
}
