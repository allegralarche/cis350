package com.example.danceforhealth;

import java.util.List;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HeartRateTwo extends Activity {

	TextView tv;
	CountDownTimer timer;
	private Workout w;
	private int heartrate;
	EditText et;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_heart_rate_two);
		Typeface font = Typeface.createFromAsset(getAssets(), "Komika_display.ttf");
		
		tv = (TextView)findViewById(R.id.timerText);
		tv.setTypeface(font);
        timer = new CountDownTimer(18000, 1000) {

            public void onTick(long millisUntilFinished) {
            	if (millisUntilFinished > 17000) {
            		tv.setText("Ready?");
            	} else if (millisUntilFinished > 16000) {
            		tv.setText("Set?");
            	} else if (millisUntilFinished > 15000) {
            		tv.setText("Start Counting!");
            	} else {
            		tv.setText("Time left: " + millisUntilFinished / 1000);
            	}
            }

            public void onFinish() {
                tv.setText("Stop Counting!");
            }
         };
 		
 		Bundle extras = getIntent().getExtras();
 		if (extras != null) {
 		    w = (Workout) extras.get("workout");
 		}
 		
		et = (EditText) findViewById(R.id.hr_input);
		
		TextView txt1 = (TextView) findViewById(R.id.textView1);
		Button b = (Button) findViewById(R.id.timer);
		TextView txt2 = (TextView) findViewById(R.id.textView2);
		Button b1 = (Button) findViewById(R.id.hr_input_button);
		TextView txt3 = (TextView) findViewById(R.id.hrText);
		Button b2 = (Button) findViewById(R.id.button1);
		txt1.setTypeface(font);
		txt2.setTypeface(font);
		txt3.setTypeface(font);
		et.setTypeface(font);
		b.setTypeface(font);
		b1.setTypeface(font);
		b2.setTypeface(font);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.heart_rate_two, menu);
		return true;
	}
	
	public void startTimer(View view) {
		timer.start();
	}
	
	public void onNextButtonClick(View view) {
		

		PrevWorkout pw = PrevWorkout.getInstance();
		List<Workout> all = pw.getPrevious();
		
		all.add(w);
		
		// create an Intent using the current Activity 
		// and the Class to be created
		Intent i = new Intent(this, WorkoutSummary.class).putExtra("workout", w);
		
		
		

		// pass the Intent to the Activity, 
		// using the specified request code
		startActivity(i);
	}
	
	public void onHRButtonClick(View view) {
		TextView t = (TextView)findViewById(R.id.hrText);
		String input = et.getText().toString();
		int numIn = Integer.parseInt(input) * 4;
		t.setText("\nYour heart rate is: " + numIn + "\n");
		heartrate = numIn;
		w.setHeartrate(numIn);
	}
	public void onBackButtonClick(View view) {
		// create an Intent using the current Activity 
		// and the Class to be created
		Intent i = new Intent(this, HeartRateActivity.class).putExtra("workout", w);

		// pass the Intent to the Activity, 
		// using the specified request code
		startActivity(i);
	}

}
