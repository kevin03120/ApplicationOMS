package fr.oms.activities;

import java.util.List;

import fr.oms.activities.R;
import fr.oms.fragments.FragmentAssociation;
import fr.oms.metier.Association;
import fr.oms.modele.Manager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class FragmentAssociationActivity extends FragmentActivity {

	private Association association;
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	        setContentView(R.layout.association_pager);     

	        int pos = getIntent().getExtras().getInt("position");
	        int position = 0;
	        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
	        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
	        List<Association> associations = Manager.getInstance().getListeAssociation();
	        for(Association a : associations){
	        	if(pos == a.getUid()){
	        		association = a;
	        		position = associations.indexOf(a);
	        	}
	        }
	        pager.setCurrentItem(position);
	    }
	 
	 private class MyPagerAdapter extends FragmentPagerAdapter {

	        public MyPagerAdapter(FragmentManager fragmentManager) {
	            super(fragmentManager);
	        }

	        @Override
	        public Fragment getItem(int pos) {
	        	association = Manager.getInstance().getListeAssociation().get(pos);
	            return FragmentAssociation.newInstance(association);
	        }

	        @Override
	        public int getCount() {
	            return Manager.getInstance().getListeAssociation().size();
	        }       
	    }
	 

		public void onGoSite(){
			String nomAssoc = association.getNom();
			nomAssoc = nomAssoc.replace("Œ", "OE");
			nomAssoc = nomAssoc.replace("AS ", "");
			nomAssoc = nomAssoc.replace(" A ", "-");
			nomAssoc = nomAssoc.replace(" ", "-");
			nomAssoc = nomAssoc.replace(".", "");
			nomAssoc = nomAssoc.replace("(", "");
			nomAssoc = nomAssoc.replace(")", "");
			nomAssoc = nomAssoc.replace("&", "");
			nomAssoc = nomAssoc.replace("/", "");
			nomAssoc = nomAssoc.replace("\"", "");
			nomAssoc = nomAssoc.replace("'", "");
			nomAssoc = nomAssoc.replace("--", "-");
			String url = getResources().getString(R.string.lienSite);
			url = url + "club/" + nomAssoc;
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(url));
			startActivity(i);
		}
		
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
		    MenuInflater inflater = getMenuInflater();
		    inflater.inflate(R.menu.association_actions, menu);
		    return super.onCreateOptionsMenu(menu);
		}
		
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
		    switch (item.getItemId()) {
		        case R.id.lien_site:
		        	onGoSite();
		            return true;
		        default:
		            return super.onOptionsItemSelected(item);
		    }
		}
}
