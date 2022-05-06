package sample;

import GoogleAPITranslate.translator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import voice.demo;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import Fx.Dictionary;
import Fx.Word;
import TrieSearch.Trie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Controller {

    Button speakButton;

    @FXML
    public TextField textWord;

    @FXML
    ListView<String> recommendedWordList;

    ObservableList<String> observableList = FXCollections.observableArrayList();

    List<String> wordList = new ArrayList<>();

    private Trie trieSearch = new Trie();

    private Dictionary dictionary = new Dictionary();


    //Search a word
    @FXML
    public void buttonFind(MouseEvent event) {

        String insertWord = textWord.getText().toLowerCase(Locale.ROOT);

        Stage findStage = new Stage();
        findStage.setTitle("MEANING");
        findStage.initModality(Modality.APPLICATION_MODAL);

        Label phoneticLabel = new Label();
        Label meaningLabel = new Label();
        Label meaningTitle = new Label();
        meaningTitle.setText("Meaning Field");
        meaningTitle.setAlignment(Pos.CENTER);

        //Speak that word :3
        speakButton = new Button();
        speakButton.setText("Speak");
        speakButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                demo demo = new demo(insertWord);
            }
        });

        VBox findPane = new VBox();

        int i = dictionary.dictionaryLookUp(insertWord);
        if (i != -1) {
            phoneticLabel.setText(dictionary.getWordList().get(i).getPronunciation());
            meaningLabel.setText(dictionary.getWordList().get(i).getDefinition());
            //    meaningLabel.setFont(new Font(12, 30));
            findPane.getChildren().addAll(meaningTitle, phoneticLabel, meaningLabel, speakButton);
        }
        else {
            meaningLabel.setText("Not found,maybe you wrote it wrong");
            findPane.getChildren().addAll(meaningTitle, meaningLabel);
        }


        Scene scene = new Scene(findPane, 500, 645);
        findStage.setScene(scene);
        findStage.setMinWidth(500);
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

    //Goi y tu sau moi lan keyRelease duoc tha ra :3
    @FXML
    public void keyRelease(KeyEvent event) {

        for (Word word : dictionary.getWordList()) {
            wordList.add(word.getTargetWord());
            trieSearch.insert(word.getTargetWord());
        }

        String s = textWord.getText();
        List<String> a = trieSearch.autocomplete(s);

        observableList = FXCollections.observableList(wordList);
        System.out.println(observableList.size());
        recommendedWordList.setItems(observableList);
        recommendedWordList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String str = recommendedWordList.getSelectionModel().getSelectedItems().toString();
                str = str.substring(1, str.length() - 1);
                textWord.setText(str);
            }
        });

        observableList.clear();
        observableList.addAll(a);
    }

    @FXML
    public void buttonAPI(MouseEvent event) throws IOException {
        try {
            Stage st = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("API.fxml"));
            st.setTitle("My Dictionary");
            st.setScene(new Scene(root, 750, 600));
            st.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
