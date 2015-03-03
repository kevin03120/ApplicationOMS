package fr.oms.activities;

import java.util.ArrayList;
import java.util.List;

import fr.oms.DataLoader.GestionDonnees;
import fr.oms.adapter.DisciplineAdapter;
import fr.oms.metier.Discipline;
import fr.oms.metier.Sport;
import fr.oms.modele.Manager;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListDisciplineActivity extends Activity {

	private ListView listeDiscipline;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.list_discipline);
		Manager.getInstance().setAccesDonnees(new GestionDonnees());
		Manager.getInstance().lireDonnees();
		
		DisciplineAdapter disciplineAdapter = new DisciplineAdapter(this, 0, Manager.getInstance().getListeDiscipline());
		listeDiscipline = (ListView) findViewById(R.id.listeDiscipline);
		listeDiscipline.setAdapter(disciplineAdapter);
		donneSport();
	}
	
	private void donneSport(){
		listeDiscipline.setOnItemClickListener(new ListView.OnItemClickListener(){
			private ListView listeSport;
			private Discipline discipline;
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Dialog dialog=new Dialog(ListDisciplineActivity.this);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.dialog_sport);
				TextView titreDialog = (TextView)dialog.findViewById(R.id.titreDialogSport);
				listeSport = (ListView)dialog.findViewById(R.id.listeSport);
			    discipline = Manager.getInstance().getListeDiscipline().get(position);
				titreDialog.setText(discipline.getNom() + " : ");
				List<String> mesNomsSports = new ArrayList<String>();
				for(Sport sport : discipline.getListeSport()){
					mesNomsSports.add(sport.getNom());
				}
				ArrayAdapter<String> sportAdapter = new ArrayAdapter<String>(ListDisciplineActivity.this, android.R.layout.simple_list_item_1, mesNomsSports);
				listeSport.setAdapter(sportAdapter);
				clicSurUnSport();
				dialog.show();
			}
			
			private void clicSurUnSport(){
				listeSport.setOnItemClickListener(new ListView.OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						Sport sport = discipline.getListeSport().get(position);
						Toast.makeText(ListDisciplineActivity.this, sport.getNom(), Toast.LENGTH_SHORT).show();
					}
					
				});
			}
		});
	}
}
