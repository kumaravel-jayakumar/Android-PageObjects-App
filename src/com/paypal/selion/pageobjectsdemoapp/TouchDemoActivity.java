package com.paypal.selion.pageobjectsdemoapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TouchDemoActivity extends ActionBarActivity {

	private GestureDetector gestureDetector;

	private Button doubleTouchButton;

	private TextView doubleTouchTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_touch_demo);
		gestureDetector = new GestureDetector(this, new GestureListener());
		doubleTouchButton = (Button) findViewById(R.id.double_touch_button);
		doubleTouchTextView = (TextView) findViewById(R.id.double_touch_button_output);
		setupDoubleTouchButton();
	}

	@Override
	protected void onResume() {
		super.onResume();
		doubleTouchTextView.setText("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.page_objects_home, menu);
		invalidateOptionsMenu();
		menu.findItem(R.id.action_button).setTitle("Slider");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_button) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void setupDoubleTouchButton() {
		doubleTouchButton.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.i("TouchDemoActivity", "on touch called");
				boolean doubleTapped = gestureDetector.onTouchEvent(event);
				if (doubleTapped) {
					handleDoubleTouch(v);
				}
				return doubleTapped;
			}
		});
	}

	private void handleDoubleTouch(View v) {
		doubleTouchTextView.setText("Touch Count: 2");
	}
	
	public static class GestureListener extends
			GestureDetector.SimpleOnGestureListener {

		@Override
		public boolean onDown(MotionEvent e) {
			if (e.getPointerCount() == 2) {
				Log.i("Touch Demo Activity", "Double touch press");
				return true;
			}
			return false;
		}

	}
}
