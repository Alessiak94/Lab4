package it.polito.tdp.anagrammi;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.anagrammiModel.Modello;
import it.polito.tdp.anagrammidb.AnagrammaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class AnagrammiController {

	private Modello model;
	private AnagrammaDAO db;
	
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtParola;

    @FXML
    private TextFlow txtFlow;

    @FXML
    void doAnagramma(ActionEvent event) {
    	txtFlow.getChildren().clear();
    	Text t;

    	if(txtParola.getText().length()==0)
    		txtFlow.getChildren().add(new Text("Inserire una parola"));
    	else{
    		 txtFlow.getChildren().addAll(trovaAnagrammi(txtParola.getText()));
    	}
    			
    }

    private List<Text> trovaAnagrammi(String t){
    	List<Text> parola= new LinkedList<Text>();
    	
    	List <String> l=model.trovaAnagrammi(""+t);
    	
    	for(int i=0;i<l.size();i++){
    		parola.add(new Text(""+l.get(i)+" "));
        	if(db.cercaParola(l.get(i))==true)
        		parola.get(i).setFill(Color.BLUE);
        	else parola.get(i).setFill(Color.RED);
    	}
    	return parola;
    }
    @FXML
    void doReset(ActionEvent event) {
    	txtParola.clear();
    	txtFlow.getChildren().clear();

    }

    public void setController(Modello model){
    	this.model=model;
    	db= new AnagrammaDAO();
    }
    
    @FXML
    void initialize() {
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Anagrammi.fxml'.";
        assert txtFlow != null : "fx:id=\"txtFlow\" was not injected: check your FXML file 'Anagrammi.fxml'.";

    }
}
