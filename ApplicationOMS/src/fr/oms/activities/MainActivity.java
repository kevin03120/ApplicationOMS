package fr.oms.activities;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {

	Timer timer;
	SeekBar sk;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		sk = (SeekBar)findViewById(R.id.pgrBarDemarrage);
		timer = new Timer();
		timer.scheduleAtFixedRate(new DemarrageTimer(sk),0, 1000);
		actionChangeProgressSeekBarTpsMusique();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private static void actualiserProgressBar(ProgressBar sk){
		sk.setProgress(sk.getProgress() + 1);
	}
	
	public void actionChangeProgressSeekBarTpsMusique(){
		 final SeekBar sk=(SeekBar) findViewById(R.id.pgrBarDemarrage);
		 sk.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {       
		    	
			    @Override       
			    public void onStopTrackingTouch(SeekBar seekBar) {   
			    }       
			    
			    @Override       
			    public void onStartTrackingTouch(SeekBar seekBar) {   
			    }       

			    @Override       
			    public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) { 
			    	if(progress == 3){
						timer.cancel();
						timer.purge();

						Intent intent = new Intent(MainActivity.this, AccueilActivity.class);
						startActivity(intent);
						overridePendingTransition(R.anim.right_to_left, R.anim.left_to_outleft); 
					}
			    }
			});   
	}
	private class DemarrageTimer extends TimerTask{

		SeekBar sk;
		
		  public DemarrageTimer(SeekBar sk) {
			  this.sk = sk;
		}
		
		  @Override
		  public void run() {
			  MainActivity.actualiserProgressBar(sk);
		  }
		
	}
}
