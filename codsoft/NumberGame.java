import java.util.Random;
import java.util.Scanner;

public class NumberGame 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 10;
        int score = 0;
        int rounds = 0;
        String playAgain = "yes";
        System.out.println("Welcome to the Number Guessing Game!!!");
        while (playAgain.equalsIgnoreCase("yes")) 
	{
            rounds++;
            int attempts = 0;
            int numberToGuess = random.nextInt(maxRange - minRange + 1) + minRange;
            System.out.println("\nRound " + rounds + ": Guess a number between " + minRange + " and " + maxRange + ". You have " + maxAttempts + " attempts.");

            while (attempts < maxAttempts) 
	    {
                System.out.print("Attempt " + (attempts + 1) + ": Your guess: ");
                int guess;
                try 
		{
                    guess = Integer.parseInt(sc.nextLine());
                } 
		catch (NumberFormatException e) 
		{
                    System.out.println("Please enter a valid integer.");
                    continue;
                }
                attempts++;
                if (guess < numberToGuess) 
		{
                    System.out.println("Your Guess is Too low!");
                } 
		else if (guess > numberToGuess) 
		{
                    System.out.println("Your Guess is Too high!");
                } 
		else 
		{
                    System.out.println("Congratulations! You've guessed the correct number.");
                    score++;
                    break;
                }
            }
            if (attempts >= maxAttempts) 
	    {
                System.out.println("Sorry, All your attempts are finished. The correct number was " + numberToGuess + ".");
            }
            System.out.print("Do you want to continue? (yes/no): ");
            playAgain = sc.nextLine();
        }
        System.out.println("\nGame Over!! You played " + rounds + " rounds and won " + score + " times.");
        System.out.println("Thanks for playing!!!");
        sc.close();
    }
}
