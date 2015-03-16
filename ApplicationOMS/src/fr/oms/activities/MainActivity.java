package fr.oms.activities;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

						Intent intent = new Intent(MainActivity.this, AnnuaireActivity.class);
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
