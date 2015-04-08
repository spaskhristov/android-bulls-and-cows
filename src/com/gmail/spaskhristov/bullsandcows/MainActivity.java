package com.gmail.spaskhristov.bullsandcows;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	private Button btnNewGame;
	private Button btnNewGameABC;
	private Button btnRules;
	private String msg = "Android : ";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnNewGame = (Button) findViewById(R.id.btnNewGame);
		btnNewGame.setOnClickListener(this);
		btnNewGameABC = (Button) findViewById(R.id.btnNewGameABC);
		btnNewGameABC.setOnClickListener(this);
		btnRules = (Button) findViewById(R.id.btnRules);
		btnRules.setOnClickListener(this);
		Log.d(msg, "The onCreate() event");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(msg, "The onStart() event");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(msg, "The onResume() event");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(msg, "The onPause() event");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(msg, "The onStop() event");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(msg, "The onDestroy() event");
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.btnNewGame:
			intent = new Intent(this, GameActivity.class);
			startActivity(intent);
			break;
		case R.id.btnNewGameABC:
			intent = new Intent(this, GameActivityABC.class);
			startActivity(intent);
			break;
		case R.id.btnRules:
			intent = new Intent(this, RulesActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
