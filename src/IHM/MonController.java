package IHM;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MonController {
    @FXML
    Button monLabel;
    @FXML
    ListView<String> listeEleves;
    @FXML
    ListView<String> listeTuteurs;
    @FXML
    TextField rechercheTuteur;
    @FXML
    TextArea contenueEleve;
    @FXML
    TextArea contenuTuteur;
    
    public void initialize() {
        System.out.println("Initialisation...");
        listeEleves.getItems().addAll("Toto", "Tutu");
        listeTuteurs.getItems().addAll("Toto", "Tutu");
        rechercheTuteur.getAccessibleText();
        
        
        listeEleves.getSelectionModel().getSelectedItems().addListener(new MonListChangeListener());
        
        listeTuteurs.getSelectionModel().getSelectedItems().addListener(new MonListChangeListener2());
        
    }
    
    class MonListChangeListener implements ListChangeListener<String> {
		public void onChanged(Change<? extends String> report) {
			contenueEleve.setText("Selection de "	 + report.getList());
		}
	}
    
    class MonListChangeListener2 implements ListChangeListener<String> {
  		public void onChanged(Change<? extends String> report) {
  			contenuTuteur.setText("Selection de "	 + report.getList());
  		}
  	}
    
    public void pressedButtonPlus(ActionEvent event) {
    	 ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}
