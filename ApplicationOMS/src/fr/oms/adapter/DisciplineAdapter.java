package fr.oms.adapter;

import java.util.ArrayList;
import java.util.List;

import fr.oms.activities.R;
import fr.oms.metier.Discipline;
import fr.oms.metier.Sport;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DisciplineAdapter extends ArrayAdapter<Discipline> {

	public Context mContext;
	private int cpt;
	
	public DisciplineAdapter(Context context, int resource, List<Discipline> objects) {
		super(context, resource, objects);
		mContext = context;
		cpt = 0;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_discipline, parent, false);
		final Discipline discipline = getItem(position);
		TextView nomDiscipline = (TextView) convertView.findViewById(R.id.nom_element);
		nomDiscipline.setText(discipline.getNom());
		Spinner spinnerSport = (Spinner)convertView.findViewById(R.id.spinner);
		List<Sport> sports = discipline.getListeSport();
		List<String> mesNomsSports = new ArrayList<String>();
		for(Sport sport : sports){
			mesNomsSports.add(sport.getNom());
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, mesNomsSports);
		spinnerSport.setAdapter(adapter);
		spinnerSport.setSelected(false);
		spinnerSport.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				//Fonctionne mais c'est moche
				if(cpt > discipline.getListeSport().size()){
					Sport sport = discipline.getListeSport().get(position);
					Toast.makeText(mContext, sport.getNom(), Toast.LENGTH_SHORT).show();
				}
				cpt++;
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		return convertView;
	}

}
