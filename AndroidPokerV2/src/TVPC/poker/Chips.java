package TVPC.poker;

import control.ControlPokerMobile;
import DAO.DAOEngine;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Chips extends Activity implements SeekBar.OnSeekBarChangeListener {

	private SeekBar mSeekBar;
	private TextView mProgressText;
	private TextView mTrackingText;
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chips);
		
		Button EnterChips = (Button) findViewById(R.id.enterChips);
		mSeekBar = (SeekBar) findViewById(R.id.seekBar1);
		mSeekBar.setOnSeekBarChangeListener(this);
		mProgressText = (TextView) findViewById(R.id.textView2);
		mTrackingText = (TextView) findViewById(R.id.textView3);
		EnterChips.setOnClickListener(botonPulsadoEnterChips);
		
		init();
	}

	private void init() {
		//get chips data base
		final TextView txtChipsAvaliable = (TextView)findViewById(R.id.chipsAvailable);
		int nchipsAvaliable = DAOEngine.getChips(ControlPokerMobile.getInstance().getPlayerControl().getPlayer().getNickName());
		ControlPokerMobile.getInstance().getPlayerControl().getPlayer().setChips(nchipsAvaliable);
		txtChipsAvaliable.setText("" + nchipsAvaliable);
	}

	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
		mProgressText.setText(progress + getString(R.string.valorChips));
	}

	
	public void onStartTrackingTouch(SeekBar seekBar) {
		mTrackingText.setText(getString(R.string.seekbar_tracking_on));
	}

	public void onStopTrackingTouch(SeekBar seekBar) {
		mTrackingText.setText(getString(R.string.seekbar_tracking_off));
	}

	private OnClickListener botonPulsadoEnterChips = new OnClickListener() {
		public void onClick(View v) {
			//start server in background
						
			CharSequence enterChips = (mProgressText.getText());
			ControlPokerMobile.getInstance().getPlayerControl().getPlayer().setEnterChips(enterChips);
			
			Intent intent = new Intent();
			intent.setClass(Chips.this, Play.class);
			
			registerUser(); //start user
			startActivity(intent);
		}
	};
	
	
	//make register in table
	private void registerUser() {
		ControlPokerMobile.getInstance().getComunicationControl().getProtocolClient().register(); 
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Alternative
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_principal, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.logOut:
			Intent intent = new Intent();
			intent.setClass(Chips.this, main.class);
			startActivity(intent);
			return true;
		case R.id.help:
			Context context = getApplicationContext();
			CharSequence text = "Select the chips which you want to enter with ";
			int duration = Toast.LENGTH_LONG;
			Toast toast = Toast.makeText(context, text, duration);
			toast.setGravity(Gravity.CENTER | Gravity.LEFT, 0, 0);
			toast.show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	

}
