package Question2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Controller {


    @FXML
    public TextField defTextField;
    @FXML
    public TextArea descriptionTextArea;
    @FXML
    public Label errorLabel;
    @FXML
    public TextField dictExportName;

    private Dictionary<String, String> dictionary = new Dictionary<>();


    public void searchDictionary(ActionEvent actionEvent) {
        String definition = defTextField.getText();
        if (dictionary.containsKey(definition)) {
            descriptionTextArea.setText(dictionary.get(definition));
            errorLabel.setText("");
        } else {
            errorLabel.setText("WORD DOESN'T EXIST IN DICTIONARY");
        }

    }

    public void removeFromDictionary(ActionEvent actionEvent) {
        String definition = defTextField.getText();
        if (dictionary.containsKey(definition)) {
            dictionary.remove(definition);
            errorLabel.setText("WORD REMOVED FROM DICTIONARY");
        } else {
            errorLabel.setText("WORD DOESN'T EXIST IN DICTIONARY");
        }

        descriptionTextArea.setText("");
        defTextField.setText("");

    }

    public void addToDictionary(ActionEvent actionEvent) {
        String definition = defTextField.getText();
        String description = descriptionTextArea.getText();
        dictionary.put(definition, description);
        errorLabel.setText("WORD ADDED/ UPDATED TO DICTIONARY");

        descriptionTextArea.setText("");
        defTextField.setText("");
    }

    public void exportDictionaryToFile(ActionEvent actionEvent) {
        try {
            String name = dictExportName.getText().isEmpty() ? "test" : getNameOfFileFRomGivenName(dictExportName.getText());
            FileWriter myWriter = new FileWriter(name);
            String dictJson = getJsonStringFromDictionary(dictionary);
            myWriter.write(dictJson);
            myWriter.close();
            errorLabel.setText("Successfully exported to:" + name);
        } catch (IOException e) {
            errorLabel.setText("Error Occurred.");
            System.out.println(e.getMessage());
        }
    }

    private String getNameOfFileFRomGivenName(String name) {
        return name.endsWith(".json") ? name : name + ".json";
    }

    private String getJsonStringFromDictionary(Dictionary<String, String> dict) {
        Set<String> set = dict.keys();
        Iterator<String> it = set.iterator();
        StringBuilder json = new StringBuilder("[");
        while (it.hasNext()) {
            String key = it.next();
            String value = dict.get(key);
            String valueToAddToJson = key + ":" + value;
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
            dictionary = new Dictionary<>();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.equals("[") || data.equals("]")) {
                    continue;
                }
                String[] keyValue = data.split(":");
                dictionary.put(keyValue[0], keyValue[1]);
            }
            myReader.close();
            errorLabel.setText("Successfully imported from: " + name);
        } catch (FileNotFoundException e) {
            errorLabel.setText("Error Occurred");
            System.out.println(e.getMessage());
        }
    }


}
