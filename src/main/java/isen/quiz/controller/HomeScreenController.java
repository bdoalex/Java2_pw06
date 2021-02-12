package isen.quiz.controller;

import isen.quiz.QuizApp;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeScreenController {

    @FXML
    private Button launchButton;


    /**
     * Appelle la prochaine scène (QuiOverviewController) à l'appui du bouton lancer
     */
    @FXML
    public void handleLaunchButton() throws IOException {
        QuizApp.setRoot("/isen/quiz/view/QuizOverview");



    }
}
