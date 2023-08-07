


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class QuestionsGame {
    // Your code here
    private QuestionNode root;

    private Scanner chicken;

    ;
    public QuestionsGame()
    {
         root = new QuestionNode("computer");
         chicken = new Scanner(System.in);
    }

    public void read(String file) throws FileNotFoundException {
        Scanner s = new Scanner(new File(file));
        while(s.hasNextLine())
        {
            root=read(s);
        }

    }

    public QuestionNode read(Scanner temp)
    {
          String type = temp.nextLine();
          String data = temp.nextLine();
          QuestionNode node = new QuestionNode(data);
          if(type.contains("Q"))
          {
              node.yes=read(temp);
              node.no=read(temp);
          }
          return node;

    }

    public void write(String file) throws FileNotFoundException {
        PrintStream stream = new PrintStream(file);
        write(root, stream);
    }

    public void write(QuestionNode node, PrintStream temp)
    {
        if(isAnswer(node))
        {
            temp.println("A: ");
            temp.println(node.data);
        }
      else {
            temp.println("Q: ");
            temp.println(node.data);
            write(node.yes, temp);
            write(node.no, temp);
        }

    }

    public boolean yesTo(String prompt)
    {
        System.out.println(prompt);
        String response = chicken.nextLine().trim().toLowerCase();
        while(!response.equals("y") && !response.equals("n"))
        {
            System.out.print("Your answer is not y or n. Please type a valid answer");
            System.out.println();
            response=chicken.nextLine().trim().toLowerCase();
        }
        return(response.equals("y"));

    }

    public boolean isAnswer(QuestionNode node)
    {
        return(node.yes==null && node.no==null);
    }


    public void askQuestions()
    {
        if(root==null)
        {
            return;
        }
        root=askQuestions(root);
    }

    private QuestionNode askQuestions(QuestionNode n)
    {

        if(isAnswer(n))
        {
            if(yesTo("Is your object a " + n.data + "?"))
            {

                System.out.println("Yay, I guessed your object");
            }
            else
            {
                System.out.println("What object were you thinking of?");
                QuestionNode answer = new QuestionNode(chicken.nextLine());
                System.out.println("What question should I use to guess it?");
                String question = chicken.nextLine();
                if(yesTo("Is the answer to your question yes or no?"))
                {
                   n= new QuestionNode(question, answer, n);
                   return n;

                }
                else {
                    n= new QuestionNode(question, n, answer);
                    return n;
                }

            }
        }
        if(yesTo(n.data))
        {
            n.yes=askQuestions(n.yes);
        }
        else {
            n.no=askQuestions(n.no);
        }
        return n;
    }

    public void game()
    {
       boolean check=true;
        while(check)
        {
            askQuestions(root);
            boolean test=yesTo("Do you want to play again?");
            if(!test)
            {
                check=false;
            }
        }
    }

    public void preOrder()
    {
        preOrder(root);
    }

    public void preOrder(QuestionNode t)
    {
        if(t==null)
        {
            return;
        }
        System.out.print(t.data + " ");
        System.out.println();
        preOrder(t.yes);
        preOrder(t.no);

    }






    private static class QuestionNode {

        // Your code here
        public String data;
        public QuestionNode yes;
       public  QuestionNode no;

        public QuestionNode(String s)
        {
            data=s;
            yes=null;
            no=null;
        }

        public QuestionNode(String s, QuestionNode y, QuestionNode n)
        {
            data=s;
            yes=y;
            no=n;

        }
    }
}
