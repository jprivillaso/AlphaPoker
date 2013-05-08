package TVPC.poker;

import control.ControlPokerMobile;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Lobby extends Activity {

	Spinner spinner_rooms;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lobby);
		Button EnterLobby = (Button) findViewById(R.id.btnEnterLobby);
		
		spinner_rooms = (Spinner) findViewById(R.id.roomsAvaliableId);
		ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource(this,
				R.array.roomsAvaliable, android.R.layout.simple_spinner_item);
		spinner_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_rooms.setAdapter(spinner_adapter);
		
		EnterLobby.setOnClickListener(botonPulsadoEnterLobby);
	}

	private OnClickListener botonPulsadoEnterLobby = new OnClickListener() {
		public void onClick(View v) {
			int value = spinner_rooms.getFirstVisiblePosition();
			if(value == 0){
				ControlPokerMobile.getInstance().getPlayerControl().getPlayer().setIp("192.168.1.70");
			}else{
				ControlPokerMobile.getInstance().getPlayerControl().getPlayer().setIp("192.168.1.110");
			}
			Intent intent = new Intent();
			intent.setClass(Lobby.this, Chips.class);
			startActivity(intent);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_principal, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.logOut:
			Intent intent = new Intent();
			intent.setClass(Lobby.this, main.class);
			startActivity(intent);
			return true;
		case R.id.help:
			Context context = getApplicationContext();
			CharSequence text = "Choose the Table where you want to play";
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