package fr.oms.activities;

import fr.oms.metier.Association;
import fr.oms.metier.Personne;
import fr.oms.modele.Manager;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class AssociationActivity extends Activity {

	private TextView nomAssoc;
	private TextView nomContact;
	private TextView telFixContact;
	private TextView telPortContact;
	private TextView mailContact;
	private TextView horaire;
	private TextView equipement1;
	private TextView equipement2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.association);
		Bundle extras = getIntent().getExtras();
		int position = extras.getInt("position");
		Association association = Manager.getInstance().getListeAssociation().get(position);
		recupererToutesViews();
		placeDonneeDansView(association);
	}
	
	private void recupererToutesViews(){
		nomAssoc = (TextView)findViewById(R.id.titreAssociation);
		nomContact = (TextView)findViewById(R.id.nomContact);
		telFixContact = (TextView)findViewById(R.id.telFixContact);
		telPortContact = (TextView)findViewById(R.id.telPortContact);
		mailContact = (TextView)findViewById(R.id.mailContact);
		horaire = (TextView)findViewById(R.id.horaire);
		equipement1 = (TextView)findViewById(R.id.lieu_equipement1);
		equipement2 = (TextView)findViewById(R.id.lieu_equipement2);
	}
	
	private void placeDonneeDansView(Association association){
		Personne pers = association.getContact();
		nomAssoc.setText(association.getNom());
		nomContact.setText(pers.getTitre() + " " + pers.getNom() + " " + pers.getPrenom());
		telFixContact.setText("Tel Fix : " + pers.getTelFixe());
		telPortContact.setText("Tel Port : " + pers.getTelPortable());
		mailContact.setText(pers.getEmail());
		horaire.setText(association.getHoraire());
		if(association.getListeEquipement().size() >=2){
			equipement1.setText(association.getListeEquipement().get(0).getNom());
			equipement2.setText(association.getListeEquipement().get(1).getNom());
		}
		else if(association.getListeEquipement().size() == 1){
			equipement1.setText(association.getListeEquipement().get(0).getNom());
			equipement2.setVisibility(4);
		}
		else{
			equipement1.setText("Aucun equipement connu");
		}
	}
}
