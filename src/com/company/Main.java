package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    @SuppressWarnings("Duplicates")

    public static void main(String[] args) {

        GameTools secretCode = new GameTools();
        String compCode = secretCode.codegenerator();


// get the computers secret number for the game
        String computerSecrtetCode = secretCode.codegenerator();
        UserGuessTools test = new UserGuessTools();
        GameTools aIcall = new GameTools();
        UserGuessTools test3 = new UserGuessTools();
        MediumAI callerMedium = new MediumAI();
        HardAI callerHard = new HardAI();
        HardAIgameFileOption callerHardFile = new HardAIgameFileOption();
        MediumAIFileOption callerMediumFile = new MediumAIFileOption();
        EasyAIFileOption callerEasyFile = new EasyAIFileOption();
        EasyAI callerEasy = new EasyAI();


        //intro to the game
        System.out.println("- - - - - - - - ");
        System.out.println("- - - - - - - - ");
        System.out.println("Welcome to the game of cows and bulls. Can you guess the computers 4 digit code before the computer guesses yours?");
        System.out.println("If you get a bulls it means you have a right number in the right position.");
        System.out.println("If you get a cow it means you have a right number but in the wrong position.");
        System.out.println("Use this info wisely to try and win the game!!!");
        System.out.println("- - - - - - - - ");
        //user decision to use a file or not
        //below are with file games
        boolean flag = false;
        while (!flag) {
            System.out.println("Would you like to play the game using a file for your guesses, or would you like to enter guesses manually? 1: for yes: a file  2: for no: manually");
            String answer = ictgradschool.industry.Keyboard.readInput();
            if (answer.equals("1")) {
                flag = true;
                String input = aIcall.aiChoiceExceptions();
                if (input.equals("1")) {
                    HashMap<Integer, ArrayList<String>> easyFile = callerEasyFile.easyAiGame(computerSecrtetCode);
                    callerEasyFile.doesUserWantPrintTxt(easyFile);
                } else if (input.equals("2")) {
                    HashMap<Integer, ArrayList<String>> mediumFile = callerMediumFile.mediumAiGameFileOption(computerSecrtetCode);
                    callerMediumFile.doesUserWantPrintTxt(mediumFile);
                } else if (input.equals("3")) {
                    HashMap<Integer, ArrayList<String>> hardFile = callerHardFile.hardAIgameUsingFile(computerSecrtetCode);
                    callerMediumFile.doesUserWantPrintTxt(hardFile);

                }
            } else if (answer.equals("2")) {
                flag = true;
                String input = aIcall.aiChoiceExceptions();
                if (input.equals("1")) {
                    HashMap<Integer, ArrayList<String>> easy = callerEasy.easyAiGame(computerSecrtetCode);
                    callerEasy.doesUserWantPrintTxt(easy);

                } else if (input.equals("2")) {
                    HashMap<Integer, ArrayList<String>> medium = callerMedium.mediumAiGame(computerSecrtetCode);
                    callerMedium.doesUserWantPrintTxt(medium);

                } else if (input.equals("3")) {
                    HashMap<Integer, ArrayList<String>> hard= callerHard.hardAIgame(computerSecrtetCode);
                    callerHard.doesUserWantPrintTxt(hard);


                }
            }else{
                System.out.println("use either number 1 or 2");
            }
        }
    }
}
