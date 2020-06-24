package Lessons.Lesson4;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ChatApplication extends Application {
    double glowVal = 0.0;
    TextArea chatText;
    TextField fieldOfText;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    Glow glow = new Glow(0.0);


    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Rumyancev Vladimir");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setResizable(false);

        FlowPane rootNode = new FlowPane();
        rootNode.setAlignment(Pos.CENTER);
        //Glow effect.
        Glow glow = new Glow(0.0);
        Scene myScene = new Scene(rootNode, 300, 275);

        primaryStage.setScene(myScene);

        chatText = new TextArea();
        chatText.setMaxSize(300, 275);
        chatText.setEditable(false);

        fieldOfText = new TextField();

        Button sendText = new Button("Send.");
        sendText.setEffect(glow);

        rootNode.getChildren().add(chatText);
        rootNode.getChildren().add(fieldOfText);
        rootNode.getChildren().add(sendText);

        primaryStage.show();
        fieldOfText.requestFocus();
        fieldOfText.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                sendTextToChat();
                fieldOfText.clear();

            }
        });
        sendText.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                sendTextToChat();
                // Glow effect for button sendText.
                glowVal += 0.1;
                if(glowVal > 1.0) glowVal = 0.0;
                glow.setLevel(glowVal);
                //end of effect

                //clear field of text to send
                fieldOfText.clear();
                fieldOfText.requestFocus();
            }
        });
    }
// Method called by handler from fieldText  and sendButton
    private void sendTextToChat() {
        Calendar time = Calendar.getInstance();
        chatText.appendText(sdf.format(time.getTime()) + " " + fieldOfText.getText() + "\n");
        fieldOfText.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
