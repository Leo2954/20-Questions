import java.io.FileNotFoundException;

public class QuestionRunner {
    public static void main(String[] args) throws FileNotFoundException {
        QuestionsGame test = new QuestionsGame();
        test.read("big-questions.txt");
        System.out.println("Welcome to 20 Questions");
        test.game();
        test.write("jeff.txt");


    }
}
