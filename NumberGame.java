import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        int answer=1;
        int score=0 , lastgs=0 ;
        while(answer==1)
        {
            Random rand = new Random();
            int numb = 1 + rand.nextInt(100);
            System.out.println("--------->"+numb);
            int guess=0 , gsCnt=0 ;

            do {
                if(gsCnt==10)
                {
                    System.out.println("You've reached the maximum guesses!");
                    System.out.print("You want to try again? (1/0) ");
                    Scanner sc = new Scanner(System.in);
                    answer = sc.nextInt();
                    gsCnt++;
                }
                else
                {
                    System.out.print("Enter your guess for the generated number: ");
                    Scanner sc = new Scanner(System.in);
                    guess = sc.nextInt();
                    gsCnt++;

                    if (numb == guess) {
                        System.out.println("Your guess is correct!");
                        score++;
                        lastgs=gsCnt;
                        System.out.print("You want to play again? (1/0) ");
                        answer = sc.nextInt();
                    }

                    if(guess>numb && gsCnt<10)
                    {
                        System.out.println("The guess is high, please try again");
                    }

                    if(guess<numb && gsCnt<10)
                    {
                        System.out.println("The guess is low, please try again");
                    }
                }

            }
            while(gsCnt<=10 && numb!=guess);


        }

        if(answer==0)
        {
            if(score==1)
                System.out.println("You won 1 round!");
            else
                System.out.println("You won "+score+" rounds!");

            if(lastgs==1)
                System.out.println("And your last number of guesses is: 1 guess!");
            else
                System.out.println("And your last number of guesses is: "+lastgs+" guesses!");
        }


    }
}