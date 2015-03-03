package fr.oms.adapter;

import java.util.List;

import fr.oms.activities.R;
import fr.oms.metier.Equipement;
import fr.oms.modele.Manager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EquipementAdapter extends ArrayAdapter<Equipement> {

	public EquipementAdapter(Context context, int resource, List<Equipement> objects) {
		super(context, resource, objects);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_association_equipement, parent, false);
		Equipement equipement = getItem(position);
		TextView nomEquipement = (TextView) convertView.findViewById(R.id.nom_element);
		nomEquipement.setText(equipement.getNom());
		return convertView;
	}

}
