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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		final EditText txtNickName = (EditText) findViewById(R.id.nickNameId);
		final EditText txtPin = (EditText) findViewById(R.id.pinId);
		final Button btnPin = (Button) findViewById(R.id.btEnterPinId);

		btnPin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// create the information to navigate trough the interfaces

				// receive the nickname
				String nickName = txtNickName.getText().toString();
				// receive the pin
				String pin = txtPin.getText().toString();

				if (DAOEngine.login(nickName, pin)) {
					Intent intent = new Intent(Login.this, Lobby.class);
					// Bundle b = new Bundle();
					ControlPokerMobile.getInstance().getPlayerControl()
							.getPlayer().setNickName(nickName);
					startActivity(intent);
				} else {
					Context context = getApplicationContext();
					CharSequence text = "NickName or Pin wrong, Please try again";
					int duration = Toast.LENGTH_LONG;

					Toast toast = Toast.makeText(context, text, duration);
					toast.setGravity(Gravity.CENTER | Gravity.LEFT, 0, 0);
					toast.show();
				}

			}
		});
	}

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
			intent.setClass(Login.this, main.class);
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