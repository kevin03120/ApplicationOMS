package fr.oms.adapter;

import java.util.ArrayList;
import java.util.List;

import fr.oms.activities.R;
import fr.oms.metier.Association;
import fr.oms.modele.Manager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AssociationAdapter extends ArrayAdapter<Association> {
	
	public AssociationAdapter(Context context, int resource, List<Association> objects) {
		super(context, resource, objects);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_association_equipement, parent,false);
		Association association = getItem(position);
		TextView nomAssociation = (TextView)convertView.findViewById(R.id.nom_association);
		nomAssociation.setText(association.getNom());
		return convertView;
	}
}
