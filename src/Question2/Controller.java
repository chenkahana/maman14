package Question2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

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
    @FXML
    public TextField dictExportName;

    private Hashtable<String, String> dictionary;


    public void initialize() {
        dictionary = new Hashtable<>();
    }

    public void searchDictionary(ActionEvent actionEvent) {
        String definition = textField.getText();
        if (dictionary.containsKey(definition)) {
            descriptionTextArea.setText(dictionary.get(definition));
            errorLabel.setText("");
        } else {
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

        } else {
            errorLabel.setText("WORD ALREADY EXIST IN\nDICTIONARY");
        }
        descriptionTextArea.setText("");
        textField.setText("");
    }

    private void sortDictionary() {
        List<String> keys = Collections.list(dictionary.keys());
        Collections.sort(keys);
        Iterator<String> it = keys.iterator();
        Hashtable<String, String> newDict = new Hashtable<>();
        while (it.hasNext()) {
            String element = it.next();
            newDict.put(element, dictionary.get(element));
        }
        dictionary = newDict;
    }

    public void exportDictionaryToFile(ActionEvent actionEvent) {
        try {
            String name = dictExportName.getText().isEmpty() ? "test" : getNameOfFileFRomGivenName(dictExportName.getText());
            FileWriter myWriter = new FileWriter(name);
            String dictJson = getJsonStringFromDictionary(dictionary);
            myWriter.write(dictJson);
            myWriter.close();
            errorLabel.setText("Successfully exported to:\n" + name);
        } catch (IOException e) {
            errorLabel.setText("Error Occurred.");
            System.out.println(e.getMessage());
        }
    }

    private String getNameOfFileFRomGivenName(String name){
        return name.endsWith(".json") ? name : name + ".json";
    }

    private String getJsonStringFromDictionary(Hashtable<String, String> dict) {
        List<String> keys = Collections.list(dict.keys());
        Iterator<String> it = keys.iterator();
        StringBuilder json = new StringBuilder("[\n");
        while (it.hasNext()) {
            String key = it.next();
            String valueToAddToJson = key + ":" + dict.get(key) + "\n";
            json.append(valueToAddToJson);
        }
        json.append("]");
        return json.toString();
    }


    public void importDictionaryFromFile(ActionEvent actionEvent) {
        if (dictExportName.getText().isEmpty()) {
            errorLabel.setText("NO NAME WAS ENTERED");
            return;
        }
        try {
            String name = getNameOfFileFRomGivenName(dictExportName.getText());
            File dictFile = new File(name);
            Scanner myReader = new Scanner(dictFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.equals("[") || data.equals("]")) {
                    continue;
                }
                String[] keyValue= data.split(":");
                dictionary= new Hashtable<>();
                dictionary.put(keyValue[0],keyValue[1]);
                System.out.println(data);
            }
            myReader.close();
            errorLabel.setText("Successfully imported to:\n" + name);
        } catch (FileNotFoundException e) {
            errorLabel.setText("Error Occurred");
            System.out.println(e.getMessage());
        }
    }
}
