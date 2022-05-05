package sample;

import Fx.Dictionary;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller {

  /*  @FXML
    public TextField textWord;
    @FXML
    public Label meaningLabel;

 //   private Dictionary dictionary;

    public void buttonFind(MouseEvent event) {
     *//*   dictionary= new Dictionary();
        dictionary.importFromFile();
        dictionary.sortWords();*//*
        String insertWord = textWord.getText();
        int i = dictionary.dictionaryLookUp(insertWord);
        if (i != -1) {
            meaningLabel.setText(dictionary.getWordList().get(i).getDefinition());
        }
        else {
            meaningLabel.setText("Not found,maybe you wrote it wrong");
        }
    }*/
}
