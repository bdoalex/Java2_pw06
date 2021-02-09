package isen.quiz.controller;

import isen.quiz.model.Question;
import javafx.fxml.FXML;
import org.w3c.dom.Text;

import java.awt.*;
import java.util.ArrayList;

public class QuizOverview {
    @FXML
    public Text questionText;

    @FXML
    public Button answerButton1;

    @FXML
    public Button answerButton2;

    @FXML
    public Button answerButton3;

    private List questions = new List();

    private int currentQuestionIndex;

    private void showCurrentQuestion(){
        String currentQuestion = questions.getItem(currentQuestionIndex);
        questionText.setTextContent(currentQuestion);
    }

    @FXML
    public void initialize (){
        
    }

}
