package fr.oms.fragments;

import java.util.List;

import fr.oms.activities.R;
import fr.oms.metier.Association;
import fr.oms.metier.Personne;
import fr.oms.modele.Manager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentAssociation extends Fragment {

	private Association association;
	private TextView nomContact;
	private ImageView iconeAdherent;
	private TextView telFixContact;
	private TextView telPortContact;
	private TextView mailContact;
	private TextView horaire;
	private TextView equipement1;
	private TextView equipement2;
	private Personne pers;
	private List<String> numeros;
	private ListView listeNumero;
	private ViewPager viewPager;
	
	public static FragmentAssociation newInstance(Association a) {
		Bundle extras = new Bundle();
		extras.putInt("id", a.getUid());
		FragmentAssociation fragment = new FragmentAssociation();
		fragment.setArguments(extras);
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 View v = inflater.inflate(R.layout.association, container, false);
			for(Association a : Manager.getInstance().getListeAssociation()){
				if(a.getUid() == getArguments().getInt("id")){
					association = a;
				}
			}
			recupererToutesViews(v);
			placeDonneeDansView();
			getActivity().setTitle(association.getNom());
	     return v;
	}
	
	private void recupererToutesViews(View v){
		nomContact = (TextView)v.findViewById(R.id.nomContact);
		telFixContact = (TextView)v.findViewById(R.id.telFixContact);
		telPortContact = (TextView)v.findViewById(R.id.telPortContact);
		mailContact = (TextView)v.findViewById(R.id.mailContact);
		horaire = (TextView)v.findViewById(R.id.horaire);
		equipement1 = (TextView)v.findViewById(R.id.lieu_equipement1);
		equipement2 = (TextView)v.findViewById(R.id.lieu_equipement2);
		iconeAdherent = (ImageView)v.findViewById(R.id.iconeAdherentAssociationFiche);
	}
	
	private void placeDonneeDansView(){
		pers = association.getContact();
		if(pers != null){
			nomContact.setText(pers.getTitre() + " " + pers.getNom() + " " + pers.getPrenom());
			telFixContact.setText("Tel Fix : " + pers.getTelFixe());
			telPortContact.setText("Tel Port : " + pers.getTelPortable());
			mailContact.setText(pers.getEmail());
		}
		else{
			nomContact.setText(getResources().getString(R.string.contactNonDispo));
		}
		horaire.setText(association.getHorraire());
		if(association.getListeEquipement()!=null){
			if(association.getListeEquipement().size() >=2){	
				equipement1.setText(association.getListeEquipement().get(0).getNom());
				equipement2.setText(association.getListeEquipement().get(1).getNom());
			}
			else if(association.getListeEquipement().size() == 1){
				equipement1.setText(association.getListeEquipement().get(0).getNom());
				equipement2.setVisibility(4);
			}			
		}else{
			equipement1.setText("Aucun equipement connu");
			equipement2.setVisibility(4);
		}
		if(!association.isAdherent()){
			iconeAdherent.setVisibility(4);
		}
	}
}
