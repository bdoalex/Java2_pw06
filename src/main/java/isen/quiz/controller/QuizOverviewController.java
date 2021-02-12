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

    /**
     * Affiche la question en cours
     */
    private void showCurrentQuestion(){
        String currentQuestion = questions.get(currentQuestionIndex).getQuestion();
        this.questionText.setText(currentQuestion);
    }

    /**
     * Va à la prochaine question
     */
    private void gotoNextQuestion(){
        if (currentQuestionIndex>=questions.size()-1){
            this.gameOver();
        }

        else{
            currentQuestionIndex++;
            this.showCurrentQuestion();
        }

    }

    /**
     * Met à jour le texte du score
     */
    private void printScore(){
        this.scoreText.setText("Votre score est de : " + score + "/" + this.questions.size());
    }

    /**
     * Met à jour le texte de l'indexe de la question
     */
    private void printQuestionIndex(){
        this.questionIndexText.setText("Question #" + (currentQuestionIndex+1) +"/"+questions.size());
    }

    /**
     * @param answerIndex indexe sur la reponse
     * détermine s'il faut incrémenter ou non le score en fonction de answerIndex et passe la question en mettant à jour les différents textes
     */
    private void handleAnswer(int answerIndex){
        if (questions.get(currentQuestionIndex).getAnswers().get(answerIndex).isGoodAnswer()){
            this.incrementScore();

        }
        this.printScore();
        this.gotoNextQuestion();
        this. printQuestionIndex();
        answerButton1.setText(questions.get(currentQuestionIndex).getAnswers().get(0).getText());
        answerButton2.setText(questions.get(currentQuestionIndex).getAnswers().get(1).getText());
        answerButton3.setText(questions.get(currentQuestionIndex).getAnswers().get(2).getText());
    }

    /**
     * Action du bouton 1
     */
    @FXML
    private void handleAnswerButton1(){
        handleAnswer(0);
    }

    /**
     * Action du bouton 2
     */
    @FXML
    private void handleAnswerButton2(){
        handleAnswer(1);

    }

    /**
     * Action du bouton 3
     */
    @FXML
    private void handleAnswerButton3(){
        handleAnswer(2);

    }


    /**
     * Incrémente le score
     */
    private void incrementScore(){
        this.score++;
        this.printScore();
    }

    /**
     * Met fin à la partie
     */
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
        this.currentQuestionIndex = 0;
        this.score = 0;
        questions = QuestionService.getInstance().getQuestions();
        this.showCurrentQuestion();
        this.printScore();
        this.printQuestionIndex();

        answerButton1.setText(questions.get(currentQuestionIndex).getAnswers().get(0).getText());
        answerButton2.setText(questions.get(currentQuestionIndex).getAnswers().get(1).getText());
        answerButton3.setText(questions.get(currentQuestionIndex).getAnswers().get(2).getText());


    }

}
