package fr.oms.activities;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import fr.oms.metier.Association;
import fr.oms.metier.Personne;
import fr.oms.modele.Manager;

public class AssociationActivity extends Activity {

	private TextView nomContact;
	private TextView telFixContact;
	private TextView telPortContact;
	private TextView mailContact;
	private TextView horaire;
	private TextView equipement1;
	private TextView equipement2;
	private Association association;
	private Personne pers;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);
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
		setTitle(association.getNom());
	}
	
	private void recupererToutesViews(){
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
	}
	
	public void onGoSite(){
		String nomAssoc = association.getNom();
		nomAssoc = nomAssoc.replace("�", "OE");
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
		/*Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog);
		dialog.setTitle("Appeler " + pers.getNom() + " " + pers.getPrenom());
		numeros = new ArrayList<String>();
		Log.i("testAppel", "Tel Fix : " + pers.getTelFixe() );
		Log.i("testAppel", "Tel Port : " + pers.getTelPortable());
		if((association.getContact().getTelFixe().length() != 0)&&(association.getContact().getTelPortable().length() != 0)){
			numeros.add(pers.getTelFixe());
			numeros.add(pers.getTelPortable());
		}
		else if((association.getContact().getTelFixe().length() == 0)&&(association.getContact().getTelPortable().length() != 0)){
			numeros.add(pers.getTelPortable());
		}
		else if(((association.getContact().getTelFixe().length() != 0)&&(association.getContact().getTelPortable().length() == 0))){
			numeros.add(pers.getTelFixe());
		}
		ArrayAdapter<String> lesNumeros = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, numeros);
		listeNumero = (ListView)dialog.findViewById(R.id.listeDialog);
		listeNumero.setAdapter(lesNumeros);
		touchNumero();
		dialog.show();*/
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.association_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.lien_site:
	        	onGoSite();
	            return true;
	        case android.R.id.home:
	            NavUtils.navigateUpFromSameTask(this);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
