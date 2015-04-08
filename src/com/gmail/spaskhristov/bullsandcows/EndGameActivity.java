package com.gmail.spaskhristov.bullsandcows;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class EndGameActivity extends Activity implements OnClickListener {
	private String msg = "End Game: ";
	private TextView textViewWin;
	private Button btnPlayA;
	private Button btnPlayAgABC;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.end_game);

		textViewWin = (TextView) findViewById(R.id.textViewWin);
		Bundle extras = getIntent().getExtras();
		String myParam = "";
		if (extras != null) {
			myParam = extras.getString("USER_WIN");
		} else {
			Log.e("extras", "Extra NULL");
		}
		textViewWin.setText(myParam);

		btnPlayA = (Button) findViewById(R.id.btnPlayA);
		btnPlayA.setOnClickListener(this);
		btnPlayAgABC = (Button) findViewById(R.id.btnPlayAgABC);
		btnPlayAgABC.setOnClickListener(this);

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
		case R.id.btnPlayA:
			intent = new Intent(this, GameActivity.class);
			startActivity(intent);
			break;
		case R.id.btnPlayAgABC:
			intent = new Intent(this, GameActivityABC.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
