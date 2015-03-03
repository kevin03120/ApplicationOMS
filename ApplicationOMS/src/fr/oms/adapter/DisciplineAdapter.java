package fr.oms.adapter;

import java.util.ArrayList;
import java.util.List;

import fr.oms.activities.R;
import fr.oms.metier.Discipline;
import fr.oms.metier.Sport;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DisciplineAdapter extends ArrayAdapter<Discipline> {

	public Context mContext;
	
	public DisciplineAdapter(Context context, int resource, List<Discipline> objects) {
		super(context, resource, objects);
		mContext = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_discipline, parent, false);
		final Discipline discipline = getItem(position);
		TextView nomDiscipline = (TextView) convertView.findViewById(R.id.nom_element);
		nomDiscipline.setText(discipline.getNom());
		return convertView;
	}

}
