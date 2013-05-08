package TVPC.poker;

import android.app.Activity;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class Register extends Activity {

	private EditText nickName;
	private EditText email;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		Button RegisterDetails = (Button) findViewById(R.id.registerDetails);
		RegisterDetails.setOnClickListener(botonPulsado);
		
		nickName = (EditText) findViewById(R.id.editText1);
		email = (EditText) findViewById(R.id.editText2);
	}

	private OnClickListener botonPulsado = new OnClickListener() {

		public void onClick(View v) {
			if(!nickName.getText().equals("") && !email.getText().equals("")){
				Intent intent = new Intent();
				String strNick = nickName.getText().toString();
				String strEmail = email.getText().toString();
				intent.putExtra("nick", strNick);
				intent.putExtra("email", strEmail);
				intent.setClass(Register.this, RegisterPin.class);
				startActivity(intent);
			}
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
			intent.setClass(Register.this, main.class);
			startActivity(intent);
			return true;
		case R.id.help:
			Context context = getApplicationContext();
			CharSequence text = "Here you have to introduce your NickName and your personal email address";
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
