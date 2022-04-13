import java.util.Locale;
import java.util.Scanner;
import java.util.Random;

public class Hangman {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[96m";
    public static final String ANSI_YELLOW = "\u001B[93m";
    public static final String ANSI_BRIGHTMAGENTA = "\u001B[95m";

    static Scanner input;
    public static void hangman(){

        input  =  new Scanner(System.in);

        //array of strings containing words to choose from
        String[] options = {"tree", "tulip", "rooftop", "balkony", "cat", "keyboard", "timetable"};

        System.out.println (ANSI_BLUE + "Welcome to the game! " +
                "\nIn this Hangman-Game you have 10 guesses to get the word. Everytime you enter a wrong guess you have 1 guess less..." +
                ANSI_RESET);


        Random obj = new Random();
        int Ran_num = obj.nextInt(6);

        String word = (options[Ran_num]);
        word = word.toUpperCase();

        //to show the word in underscores
        String word1 = word.replaceAll("[A-Z]", "_");

        System.out.println(ANSI_BLUE + "Good luck! Ready? Set! Go!" + ANSI_RESET);
        startHangman(word, word1);
    }
    public static void startHangman(String word, String word1){

        int guess_ = 0;
        int wrong = 0;

        //for each guess
        String guess;
        //stores the guessed letter
        char letter;

        //stores if the letter was already guessed
        boolean guessescontainsguess;
        String guesses = "";
        boolean guessingword;

        while (wrong <10 && word1.contains("_")){

            System.out.println(ANSI_BRIGHTMAGENTA + word1 + "\n" + ANSI_RESET);
            int temp = 10 - wrong;
            if (temp != 0){
                System.out.println(ANSI_YELLOW + "You  have " + temp + " guesses left." + ANSI_RESET);
            }

            System.out.print(ANSI_BLUE + "Your enter:"+ ANSI_RESET);

            guess = input.nextLine();
            guess = guess.toUpperCase();

            letter = guess.charAt(0);

            guessescontainsguess = (guesses.indexOf(letter)) != -1;

            guesses += letter;

            letter = Character.toUpperCase(letter);
            System.out.println();

            if (guessescontainsguess == true){
                System.out.println(ANSI_BLUE + "\nYou already tried the letter " + ANSI_RESET + ANSI_CYAN + letter + ANSI_RESET);

            }

            guessingword = (word.indexOf(letter)) != -1;

            if (guessingword ==true){
                System.out.print(ANSI_CYAN + letter + ANSI_RESET );
                System.out.println(ANSI_GREEN + " is in the word:)" + ANSI_RESET);
                System.out.println("\n");

                for(int position = 0; position < word.length(); position++){

                    if (word.charAt(position) == letter && word1.charAt(position) != letter){

                        word1 = word1.replaceAll("_", "_");
                        String word2;
                        word2 = word1.substring(0,position)
                                +letter
                                +word1.substring(position + 1);
                        word2 = word2.replaceAll("_", "_");
                        word1 = word2;
                    }

                }

            } else {
                System.out.print( ANSI_CYAN + letter + ANSI_RESET);
                System.out.println(ANSI_RED + " isn't in the word." + ANSI_RESET);
                wrong++;
            }
            guess_++;

        }
        if (wrong ==10){
            System.out.println(ANSI_RED+"Sorry, you failed:( Try again!"+ANSI_RESET);
        } else {
            System.out.println(ANSI_GREEN + "That's right, the word is: " + ANSI_RESET );
            System.out.println(ANSI_BRIGHTMAGENTA+ word1 + ANSI_RESET);
        }
    }
    public static void main (String[] args){
        hangman();
    }

}


