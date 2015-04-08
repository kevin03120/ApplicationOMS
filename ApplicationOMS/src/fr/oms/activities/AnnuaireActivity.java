package fr.oms.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import fr.oms.DataLoader.CSVParser;
import fr.oms.DataLoader.ClientHttp;
import fr.oms.fragments.FragmentListeAssociations;
import fr.oms.fragments.FragmentListeDisciplines;
import fr.oms.fragments.FragmentListeEquipements;
import fr.oms.fragments.FragmentListeQuartiers;
import fr.oms.modele.Manager;

public class AnnuaireActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Manager.getInstance().clearDonnees();
		CSVParser parser=new CSVParser(this);
		parser.readCSV();
		setTitle(getResources().getString(R.string.annuaire));
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.annuaire);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowCustomEnabled(true);
		
		FragmentTabHost tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
		tabHost.addTab(
				tabHost.newTabSpec("Associations").setIndicator("Associations", null),
                FragmentListeAssociations.class, null);
		tabHost.addTab(
				tabHost.newTabSpec("Equipements").setIndicator("Equipements", null),
                FragmentListeEquipements.class, null);
		tabHost.addTab(
				tabHost.newTabSpec("Disciplines").setIndicator("Disciplines", null),
                FragmentListeDisciplines.class, null);
		tabHost.addTab(
				tabHost.newTabSpec("Quartiers").setIndicator("Quartiers", null),
                FragmentListeQuartiers.class, null);
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            NavUtils.navigateUpFromSameTask(this);
	            return true;	        
	        default:
	            return super.onOptionsItemSelected(item);
	    }
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
	
	public void onConnexionTest(){
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
