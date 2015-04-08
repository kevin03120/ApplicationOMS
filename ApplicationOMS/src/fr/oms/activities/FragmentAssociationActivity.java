package fr.oms.activities;

import java.util.List;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import fr.oms.fragments.FragmentAssociation;
import fr.oms.metier.Association;
import fr.oms.modele.Manager;

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
}
