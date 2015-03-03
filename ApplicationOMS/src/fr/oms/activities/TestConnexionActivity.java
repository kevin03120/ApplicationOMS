package fr.oms.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import fr.oms.DataLoader.ClientHttp;

public class TestConnexionActivity extends Activity 	{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.synchro);
	}

	public void onSynClick(View v){


		//ClientHttp client=new ClientHttp(this);		
		try {
			new ClientHttp(TestConnexionActivity.this).execute(openFileOutput("testDonnees.txt",MODE_PRIVATE));
		}
		catch (Exception e) 
		{
			String error="";
			error=e.getMessage();
		}

	}

}
