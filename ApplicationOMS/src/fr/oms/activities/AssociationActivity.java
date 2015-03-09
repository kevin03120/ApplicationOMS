package fr.oms.activities;

import java.util.ArrayList;
import java.util.List;

import fr.oms.metier.Association;
import fr.oms.metier.Personne;
import fr.oms.modele.Manager;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
	private Association association;
	private Personne pers;
	private List<String> numeros;
	private ListView listeNumero;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.association);
		Bundle extras = getIntent().getExtras();
		int position = extras.getInt("position");
		for(Association a : Manager.getInstance().getListeAssociation()){
			if(a.getUid() == position){
				association = a;
			}
		}
		recupererToutesViews();
		placeDonneeDansView();
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
	
	private void placeDonneeDansView(){
		pers = association.getContact();
		nomAssoc.setText(association.getNom());
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
		//equipement1.setText(getResources().getString(R.string.equipementNonDispo));
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
		
	}
	public void onGoSite(View v){
		String nomAssoc = association.getNom();
		nomAssoc = nomAssoc.replace("Œ", "OE");
		nomAssoc = nomAssoc.replace("AS ", "");
		nomAssoc = nomAssoc.replace(" A ", "-");
		nomAssoc = nomAssoc.replace(" ", "-");
		nomAssoc = nomAssoc.replace(".", "");
		nomAssoc = nomAssoc.replace("(", "");
		nomAssoc = nomAssoc.replace(")", "");
		nomAssoc = nomAssoc.replace("&", "");
		nomAssoc = nomAssoc.replace("/", "");
		nomAssoc = nomAssoc.replace("\"", "");
		nomAssoc = nomAssoc.replace("'", "");
		nomAssoc = nomAssoc.replace("--", "-");
		String url = getResources().getString(R.string.lienSite);
		url = url + "club/" + nomAssoc;
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
		Log.i("testString", nomAssoc);
	}
	
	public void onCall(View v){
		Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog);
		dialog.setTitle("Appeler " + pers.getNom() + " " + pers.getPrenom());
		numeros = new ArrayList<String>();
		Log.i("testAppel", "Tel Fix : " + pers.getTelFixe() );
		Log.i("testAppel", "Tel Port : " + pers.getTelPortable());
		if((association.getContact().getTelFixe() != "")&&(association.getContact().getTelPortable() != "")){
			numeros.add(pers.getTelFixe());
			numeros.add(pers.getTelPortable());
		}
		else if((association.getContact().getTelFixe() == "")&&(association.getContact().getTelPortable() != "")){
			numeros.add(pers.getTelPortable());
		}
		else if(((association.getContact().getTelFixe() != "")&&(association.getContact().getTelPortable() == ""))){
			numeros.add(pers.getTelFixe());
		}
		ArrayAdapter<String> lesNumeros = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, numeros);
		listeNumero = (ListView)dialog.findViewById(R.id.listeDialog);
		listeNumero.setAdapter(lesNumeros);
		touchNumero();
		dialog.show();
	}
	
	private void touchNumero(){
		listeNumero.setOnItemClickListener(new ListView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				/*String numero = numeros.get(position);
				numero = numero.replace(" ", "");
				numero = numero.replace(".", "");
				Log.i("testAppel", "Tel Fix cool : " + numero );
				Log.i("testAppel", "Tel Port cool : " + numero);
				Intent intent = new Intent( Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel:" + numero));
				startActivity( intent );*/
			}
		
		});
	}
}
