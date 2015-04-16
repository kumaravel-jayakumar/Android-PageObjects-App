package com.paypal.selion.pageobjectsdemoapp;

import android.content.Intent;
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

public class TapDemoActivity extends ActionBarActivity {

	private GestureDetector gestureDetector;

	private Button singleTapButton;

	private Button doubleTapButton;

	private TextView singleTapTextView;

	private TextView doubleTapTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tap_demo);
		gestureDetector = new GestureDetector(this, new GestureListener());
		singleTapButton = (Button) findViewById(R.id.single_tap_button);
		doubleTapButton = (Button) findViewById(R.id.double_tap_button);
		singleTapTextView = (TextView) findViewById(R.id.single_tap_button_output);
		doubleTapTextView = (TextView) findViewById(R.id.double_tap_button_output);
		setupSingleTapButton();
		setupDoubleTapButton();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		singleTapTextView.setText("");
		doubleTapTextView.setText("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.page_objects_home, menu);
		invalidateOptionsMenu();
		menu.findItem(R.id.action_button).setTitle("Touch");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_button) {
			Intent intent = new Intent(this, TouchDemoActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void setupSingleTapButton() {
		singleTapButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleSingleTap(v);
			}
		});
		
	}

	private void setupDoubleTapButton() {
		doubleTapButton.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.i("TapDemoActivity", "on touch called");
				boolean doubleTapped = gestureDetector.onTouchEvent(event);
				if (doubleTapped) {
					handleDoubleTap(v);
				}
				return doubleTapped;
			}
		});
	}

	private void handleSingleTap(View v) {
		singleTapTextView.setText("Tap Count: 1");
	}
	
	private void handleDoubleTap(View v) {
		doubleTapTextView.setText("Tap Count: 2");
	}

	public static class GestureListener extends
			GestureDetector.SimpleOnGestureListener {

		@Override
		public boolean onDoubleTap(MotionEvent e) {
			Log.i("TapDemoActivity", "Double tap fired");
			return true;
		}
		
	}
}
