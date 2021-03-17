package it.polito.tdp.alien;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import it.polito.tdp.alien.model.Dizionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	Dizionario alien;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtTesto;

    @FXML
    private TextArea txtRisultato;

    @FXML
    void handleClearText(ActionEvent event) {

    	alien.cancellaTutto();
    }

    @FXML
    void handleTranslate(ActionEvent event) {
    	
    	String testo = this.txtTesto.getText();
    	String nome;
    	String traduzione;

    	if (testo.contains(" ") ) {
    		String[]parola = testo.split(" ");
    		
    		if (parola.length!=2) {
    			this.txtRisultato.setText("puoi inserire due parole: una parola l'altra traduzione");
    			this.txtTesto.setText(null);
    		}
    		else {
    			if ((Pattern.matches("[a-zA-Z]+", parola[0]) == true) && (Pattern.matches("[a-zA-Z]+", parola[0]) == true) ) {
    			    nome = parola[0].toLowerCase();
    			    traduzione = parola[1].toLowerCase();
    			    alien.addParole(nome, traduzione);
    			    this.txtRisultato.setText("parola inserita correttamente");
    			    this.txtTesto.setText(null);
    		      }
    			else {
    					this.txtRisultato.setText("la parola non può contenere caratteri speciali");
    					this.txtTesto.setText(null);
    				
    			}
    	   }
    	}
    	else {
    		if (testo.contains("?")) {
    			String[] parti = testo.split("\\?");
    			List <String> traduzioni = alien.cercaChiave(parti[0].toLowerCase(), parti[1].toLowerCase());
    			String s= "";
    			for (int i=0; i<traduzioni.size(); i++)
    				s= s+traduzioni.get(i);
    			this.txtRisultato.setText(s);
    			this.txtTesto.setText(null);
    			
    		}
    		else {
    		if ((Pattern.matches("[a-zA-Z]+", testo) == true) && alien.cercaTraduzione(testo).toLowerCase()!=null) {
    			this.txtRisultato.setText(alien.cercaTraduzione(testo).toLowerCase());
    			this.txtTesto.setText(null);
    		}
    		else {
    			this.txtRisultato.setText("la parola non è nel dizionario");
    			this.txtTesto.setText(null);
    		}
    	 }
    	}
    }
    		

    @FXML
    void initialize() {
        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

        alien = new Dizionario();
    }
}
