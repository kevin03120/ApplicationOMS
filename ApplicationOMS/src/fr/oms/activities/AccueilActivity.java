package fr.oms.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class AccueilActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.accueil);
	}
	
	public void onInfoActivity(View v){
		Intent intent = new Intent(this, InfoActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.right_to_left, R.anim.left_to_outleft); 
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
		Intent intent = new Intent(this, TestConnexionActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.right_to_left, R.anim.left_to_outleft); 
	}
	
	public void onQuartierActivity(View v){
		Intent intent = new Intent(this, ListQuartierActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.right_to_left, R.anim.left_to_outleft); 
	}
	
	@Override
	public void onBackPressed() {
		moveTaskToBack(true);
	}
	
}
