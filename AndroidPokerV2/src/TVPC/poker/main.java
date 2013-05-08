package TVPC.poker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class main extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button Register = (Button) findViewById(R.id.register);
		Button Login = (Button) findViewById(R.id.login);

		Register.setOnClickListener(botonPulsadoRegister);
		Login.setOnClickListener(botonPulsadoLogin);
	}

	private OnClickListener botonPulsadoLogin = new OnClickListener() {

		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(main.this, Login.class);
			startActivity(intent);
		}

	};

	private OnClickListener botonPulsadoRegister = new OnClickListener() {

		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(main.this, Register.class);
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
			intent.setClass(main.this, main.class);
			startActivity(intent);
			return true;
		case R.id.help:
			Context context = getApplicationContext();
			CharSequence text = "Enter your NickName and Pin if you are registered otherwise go to register mode";
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
