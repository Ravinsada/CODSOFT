import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String question;
    private List<String> options;
    private int correctAnswerIndex;

    public Question(String question, List<String> options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public boolean isCorrect(int userAnswerIndex) {
        return userAnswerIndex == correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }
}

public class QuizApplication {
    private static int score = 0;
    private static int questionIndex = 0;
    private static List<Question> questions;
    private static Timer timer;

    public static void main(String[] args) {
        initializeQuestions();
        startQuiz();
    }

    private static void initializeQuestions() {
        // Define your quiz questions here.
        questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?",
                List.of("Berlin", "London", "Paris", "Madrid"), 2));
        questions.add(new Question("What is 2 + 2?", List.of("3", "4", "5", "6"), 1));
        // Add more questions as needed.
    }

    private static void startQuiz() {
        timer = new Timer();
        if (questionIndex < questions.size()) {
            Question currentQuestion = questions.get(questionIndex);
            System.out.println("Question " + (questionIndex + 1) + ": " + currentQuestion.getQuestion());
            List<String> options = currentQuestion.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            // Set a timer for the question
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up! Moving to the next question.");
                    questionIndex++;
                    startQuiz();
                }
            }, 15000); // 15 seconds

            // Get user input for the answer
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your choice (1-" + options.size() + "): ");
            int userChoice = scanner.nextInt() - 1;

            if (currentQuestion.isCorrect(userChoice)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect.");
            }

            questionIndex++;
            timer.cancel(); // Cancel the timer
            startQuiz();
        } else {
            endQuiz();
        }
    }

    private static void endQuiz() {
        System.out.println("\nQuiz completed!");
        System.out.println("Your Score: " + score + "/" + questions.size());
    }
}
