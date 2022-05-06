package sample;

import GoogleAPITranslate.translator;
import com.sun.java.accessibility.util.Translator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class API implements Initializable {

    @FXML
    Button translate;

    @FXML
    Button speakText;

    @FXML
    TextArea enArea;

    @FXML
    TextArea viArea;

    @FXML
    void handleAPI(MouseEvent event) throws IOException {
        String s = translator.translate("en", "vi", enArea.getText());
        viArea.setText(s);

    }

    @FXML
    void handleSpeak(MouseEvent event) throws IOException {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
