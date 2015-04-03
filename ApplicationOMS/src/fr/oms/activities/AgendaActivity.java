package fr.oms.activities;

import fr.oms.fragments.FragmentActus;
import fr.oms.fragments.FragmentEvents;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class AgendaActivity extends FragmentActivity  {
	
	private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.agenda);
        setContentView(R.layout.agenda);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(
                mTabHost.newTabSpec("Actualités").setIndicator("Actualités", null),
                FragmentActus.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("Evènements").setIndicator("Evènements", null),
                FragmentEvents.class, null);
    }
	
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.agenda_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_Agenda:
	        	Toast.makeText(this, getResources().getString(R.string.agenda), Toast.LENGTH_SHORT).show();
	            return true;
	        case android.R.id.home:
	            NavUtils.navigateUpFromSameTask(this);
	            return true;	 
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	  
}
