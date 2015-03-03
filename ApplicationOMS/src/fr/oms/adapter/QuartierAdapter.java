package fr.oms.adapter;

import java.util.List;

import fr.oms.activities.R;
import fr.oms.metier.Quartier;
import fr.oms.modele.Manager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class QuartierAdapter extends ArrayAdapter<Quartier> {

	public QuartierAdapter(Context context, int resource, List<Quartier> objects) {
		super(context, resource, objects);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_association_equipement, parent, false);
		Quartier quartier = Manager.getInstance().getListeQuartier().get(position);
		TextView nomQuartier = (TextView)convertView.findViewById(R.id.nom_element);
		nomQuartier.setText(quartier.getNom());
		return convertView;
	}

}