package sample;

import TrieSearch.*;
import Fx.Dictionary;
import Fx.Word;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main extends Application {

    @FXML
    public TextField textWord;

    @FXML
    ListView<String> recommendedWordList;

    ObservableList<String> observableList = FXCollections.observableArrayList();

    List<String> wordList = new ArrayList<>();

    private Trie trieSearch = new Trie();

    private Dictionary dictionary = new Dictionary();

    @Override
    public void start(Stage primaryStage) throws Exception{

        try {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("My Dictionary");
            primaryStage.setScene(new Scene(root, 800, 700));
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //Search a word
    @FXML
    public void buttonFind(MouseEvent event) {

        String insertWord = textWord.getText().toLowerCase(Locale.ROOT);

        Stage findStage = new Stage();
        findStage.setTitle("MEANING");
        findStage.initModality(Modality.APPLICATION_MODAL);

        Label meaningLabel = new Label();
        Label meaningTitle = new Label();
        meaningTitle.setText("Meaning Field");
        meaningTitle.setAlignment(Pos.CENTER);
        VBox findPane = new VBox();

        int i = dictionary.dictionaryLookUp(insertWord);
        if (i != -1) {
              meaningLabel.setText(dictionary.getWordList().get(i).getDefinition());
          //  meaningRoll.setContent(meaningLabel);
            findPane.getChildren().addAll(meaningTitle, meaningLabel);
        }
        else {
            meaningLabel.setText("Not found,maybe you wrote it wrong");
            //  meaningRoll.setContent(meaningLabel);
            findPane.getChildren().addAll(meaningTitle, meaningLabel);
        }


        Scene scene = new Scene(findPane, 300, 645);
        findStage.setScene(scene);
        findStage.setMinWidth(300);
        findStage.show();
    }

    //Remove a word
    @FXML
    public void buttonRemove(MouseEvent event) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Remove a word");

        Label inputWordLabel = new Label();
        inputWordLabel.setText("Enter the word");

        TextField inputWord = new TextField();
        inputWord.setPromptText("Enter a word you want to remove");

        Button removeConfirm = new Button();
        removeConfirm.setText("Remove");

        VBox removePane = new VBox();
        removePane.getChildren().addAll(inputWordLabel, inputWord, removeConfirm);

        removeConfirm.setOnAction(event1 -> {
            String s = inputWord.getText().toLowerCase(Locale.ROOT);

            int i = dictionary.removeWord(s);
            if (i != -1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("MESSAGE");
                alert.setContentText("Word remove successfully");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("MESSAGE");
                alert.setContentText("Word not found in dictionary");
                alert.show();
            }
        });
        Scene scene = new Scene(removePane, 300, 500);
        stage.setScene(scene);
        stage.setMinWidth(300);
        stage.show();
    }

    // Add a word
    @FXML
    public void buttonAdd(MouseEvent event) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add a word");

        Label inputWordLabel = new Label();
        inputWordLabel.setText("Enter a word");

        TextField inputWord = new TextField();
        inputWord.setPromptText("Enter a word you want to add");

        Label phonetic = new Label();
        phonetic.setText("Phonetics");

        TextField phoneticInput = new TextField();
        phoneticInput.setPromptText("Enter the word's phonetic");

        Label detailLabel = new Label();
        detailLabel.setText("Detail");

        TextArea wordDetail = new TextArea();

        Button addConfirm = new Button();
        addConfirm.setText("Add");

        VBox addPane = new VBox();
        addPane.getChildren().addAll(inputWordLabel, inputWord, phonetic, phoneticInput, detailLabel, wordDetail, addConfirm);

        addConfirm.setOnAction(event1 -> {
            String s = inputWord.getText().toLowerCase(Locale.ROOT);
            //   StringBuilder s2 = new StringBuilder(wordDetail.getText());
            String s2 = phoneticInput.getText();
            String s3 = wordDetail.getText();

            int i = dictionary.addWord(new Word(s, s2, s3));
            if (i != -1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("MESSAGE");
                alert.setContentText("Word add successfully");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("MESSAGE");
                alert.setContentText("Word already exist");
                alert.show();
            }
        });
        Scene scene = new Scene(addPane, 300, 500);
        stage.setScene(scene);
        stage.setMinWidth(300);
        stage.show();
    }

    //Change a word definition
    @FXML
    public void buttonChangeDefinition(MouseEvent event) {

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Change a word's definition");

        Label inputWordLabel = new Label();
        inputWordLabel.setText("Enter the word");

        TextField changeDefinitionWord = new TextField();
        changeDefinitionWord.setPromptText("Enter the word you want to change definition");

        Label definitionLabel = new Label();
        definitionLabel.setText("New definition");

        TextArea definitionDetail = new TextArea();

        Button changeConfirm = new Button();
        changeConfirm.setText("Change");

        VBox addPane = new VBox();
        addPane.getChildren().addAll(inputWordLabel, changeDefinitionWord,definitionLabel, definitionDetail, changeConfirm);

        changeConfirm.setOnAction(event1 -> {
            String s = changeDefinitionWord.getText().toLowerCase(Locale.ROOT);
            //   StringBuilder s2 = new StringBuilder(wordDetail.getText());
            String s2 = definitionDetail.getText();

            int i = dictionary.changeDefinition(s, s2);
            if (i != -1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("MESSAGE");
                alert.setContentText("WORD DEFINITION CHANGE SUCCESSFULLY");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("MESSAGE");
                alert.setContentText("WORD NOT EXIST IN DICTIONARY");
                alert.show();
            }
        });
        Scene scene = new Scene(addPane, 300, 500);
        stage.setScene(scene);
        stage.setMinWidth(300);
        stage.show();
    }

    @FXML
    public void keyRelease(KeyEvent event) {
            recommendedWordList.setItems(observableList);
            String s = textWord.getText();
            List<String> a = trieSearch.autocomplete(s);


            observableList.clear();
            observableList.addAll(a);
            // recommendedWordList.setVisible(true);
      /*  String str = recommendedWordList.getSelectionModel().getSelectedItems().toString();
        str = str.substring(1, str.length() - 1);
        textWord.setText(str);*/
    }

    public static void main(String[] args) {
        launch(args);
    }
}
