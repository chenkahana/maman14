package Question2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class Controller {

    @FXML
    public Button searchButton;
    @FXML
    public TextField textField;
    @FXML
    public TextArea descriptionTextArea;
    @FXML
    public Label descriptionLabel;
    @FXML
    public Button addButton;
    @FXML
    public Button exportDictionary;
    @FXML
    public Button importDictionary;
    @FXML
    public Label errorLabel;

    private Hashtable<String, String> dictionary;


    public void initialize() {
        dictionary = new Hashtable<>();
    }

    public void searchDictionary(ActionEvent actionEvent) {
        String definition = textField.getText();
        if (dictionary.containsKey(definition)) {
            descriptionTextArea.setText(dictionary.get(definition));
            errorLabel.setText("");
        }else{
            errorLabel.setText("WORD ADDED TO DICTIONARY");
        }

    }

    public void addToDictionary(ActionEvent actionEvent) {
        String definition = textField.getText();
        if (!dictionary.containsKey(definition)) {
            String description = descriptionTextArea.getText();
            dictionary.put(definition, description);
            sortDictionary();
            errorLabel.setText("WORD ADDED TO DICTIONARY");

        }else{
            errorLabel.setText("WORD ALREADY EXIST IN\nDICTIONARY");
        }
        descriptionTextArea.setText("");
        textField.setText("");
    }

    private void sortDictionary() {
        List<String> tmp = Collections.list(dictionary.keys());
        Collections.sort(tmp);
        Iterator<String> it = tmp.iterator();
        Hashtable<String, String> newDict = new Hashtable<>();
        while (it.hasNext()) {
            String element = it.next();
            newDict.put(element, dictionary.get(element));
        }
        dictionary = newDict;
    }

    public void exportDictionaryToFile(ActionEvent actionEvent) {
        
    }

    public void importDictionaryFromFile(ActionEvent actionEvent) {

    }
}
