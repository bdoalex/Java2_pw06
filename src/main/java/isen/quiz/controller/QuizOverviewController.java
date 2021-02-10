package isen.quiz.controller;

import isen.quiz.model.Question;
import isen.quiz.service.QuestionService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;

import java.util.List;

public class QuizOverviewController {
    @FXML
    private Text questionText;

    @FXML
    private Button answerButton1;

    @FXML
    private Button answerButton2;

    @FXML
    private Button answerButton3;

    @FXML
    private Text scoreText;

    @FXML
    private Text questionIndexText;

    private List<Question> questions;

    private int currentQuestionIndex;

    private int score;

    private void showCurrentQuestion(){
        String currentQuestion = questions.get(currentQuestionIndex).getQuestion();
        questionText.setText(currentQuestion);
    }

    private void gotoNextQuestion(){
        if (currentQuestionIndex>=questions.size()-1){
            gameOver();
        }

        else{
            currentQuestionIndex++;
            showCurrentQuestion();
        }

    }

    private void printScore(){
        scoreText.setText("Votre score est de : " + score + "/" + questions.size());
    }

    private void printQuestionIndex(){
        questionIndexText.setText("Question #" + currentQuestionIndex +"/"+questions.size());
    }

    private void handleAnswer(int answerIndex){
        if (questions.get(currentQuestionIndex).getAnswers().get(answerIndex).isGoodAnswer()){
            incrementScore();

        }
        printScore();
        gotoNextQuestion();
        printQuestionIndex();
        answerButton1.setText(questions.get(currentQuestionIndex).getAnswers().get(0).getText());
        answerButton2.setText(questions.get(currentQuestionIndex).getAnswers().get(1).getText());
        answerButton3.setText(questions.get(currentQuestionIndex).getAnswers().get(2).getText());
    }

    @FXML
    private void handleAnswerButton1(){
        handleAnswer(0);
    }

    @FXML
    private void handleAnswerButton2(){
        handleAnswer(1);

    }

    @FXML
    private void handleAnswerButton3(){
        handleAnswer(2);

    }


    private void incrementScore(){
        score++;
    }

    private void gameOver() {
        questionText.setText("");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game over");
        alert.setHeaderText("Game over");
        alert.setContentText("Votre score est de : " + score + "/" + questions.size());
        alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> Platform.exit());
    }

    @FXML
    public void initialize (){
        currentQuestionIndex = 1;
        score = 0;
        questions = QuestionService.getInstance().getQuestions();
        showCurrentQuestion();
        printScore();
        printQuestionIndex();

        answerButton1.setText(questions.get(currentQuestionIndex).getAnswers().get(0).getText());
        answerButton2.setText(questions.get(currentQuestionIndex).getAnswers().get(1).getText());
        answerButton3.setText(questions.get(currentQuestionIndex).getAnswers().get(2).getText());


    }

}
