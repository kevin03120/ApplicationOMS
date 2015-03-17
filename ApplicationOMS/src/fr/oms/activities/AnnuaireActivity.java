package fr.oms.activities;

import fr.oms.DataLoader.CSVParser;
import fr.oms.DataLoader.ClientHttp;
import fr.oms.modele.Manager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;

public class AnnuaireActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Manager.getInstance().clearDonnees();
		CSVParser parser=new CSVParser(this);
		parser.readCSV();
		setTitle(getResources().getString(R.string.annuaire));
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.annuaire);
	}
	
	
	
	public void onAssociationActivity(View v){
		Intent intent = new Intent(this, ListAssociationActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.right_to_left, R.anim.left_to_outleft);
	}
	
	public void onEquipementActivity(View v){
		Intent intent = new Intent(this, ListEquipementActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.right_to_left, R.anim.left_to_outleft); 
	}
	
	public void onDisciplineActivity(View v){
		Intent intent = new Intent(this, ListDisciplineActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.right_to_left, R.anim.left_to_outleft);
	}
	
	public void onConnexionTest(View v){
		if(isNetworkAvailable(this)){
			try {
				new ClientHttp(AnnuaireActivity.this).execute(openFileOutput("testDonnees.txt",MODE_PRIVATE));
			}
			catch (Exception e) 
			{
				e.getMessage();
			} 
		}
		else{
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AnnuaireActivity.this);
				alertDialogBuilder.setTitle(R.string.detailCo);
				alertDialogBuilder
					.setMessage(getResources().getString(R.string.detailCo))
					.setCancelable(false)
					.setPositiveButton("Annuler la synchronisation",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							dialog.dismiss();
						}
					  });
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
			}
	}
	public void onQuartierActivity(View v){
		Intent intent = new Intent(this, ListQuartierActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.right_to_left, R.anim.left_to_outleft); 
	}
	
	public boolean isNetworkAvailable( Activity mActivity ) 
	  { 
	          Context context = mActivity.getApplicationContext();
	          ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	          if (connectivity == null) 
	          {   
	        	  return false;
	          } 
	          else 
	          {  
	        
		        NetworkInfo[] info = connectivity.getAllNetworkInfo();   
		        if (info != null) 
		        {   
		                for (int i = 0; i <info.length; i++) 
		                { 
		                        if (info[i].getState() == NetworkInfo.State.CONNECTED)
		                        {
		                                return true; 
		                        }
		             }     
		         } 
		          return false;
	          }
	 }   
	
}
