package fr.oms.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import fr.oms.adapter.DisciplineAdapter;
import fr.oms.metier.Discipline;
import fr.oms.metier.Sport;
import fr.oms.modele.Manager;

public class ListDisciplineActivity extends Activity {

	private ListView listeDiscipline;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_discipline);
		setTitle(getResources().getString(R.string.discipline));
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
				dialog.setContentView(R.layout.dialog);
				listeSport = (ListView)dialog.findViewById(R.id.listeDialog);
			    discipline = Manager.getInstance().getListeDiscipline().get(position);
			    dialog.setTitle(discipline.getNom() + " : ");
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
						int idSport = sport.getId();
						Intent intent = new Intent(ListDisciplineActivity.this, ListAssociationActivity.class);
						intent.putExtra("idSport", idSport);
						startActivity(intent);
					}
					
				});
			}
		});
	}
}
