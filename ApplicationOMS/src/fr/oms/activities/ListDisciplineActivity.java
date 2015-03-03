package fr.oms.activities;

import java.util.ArrayList;
import java.util.List;

import fr.oms.DataLoader.GestionDonnees;
import fr.oms.adapter.DisciplineAdapter;
import fr.oms.metier.Discipline;
import fr.oms.metier.Sport;
import fr.oms.modele.Manager;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
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
	
	public void donneSport(){
		listeDiscipline.setOnItemClickListener(new ListView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
			}
		});
	}
}
