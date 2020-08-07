package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class HardAI extends MediumAI {



    public void aiChoice() {
        System.out.println("- - - - - - - - - - -");
        System.out.println("Welcome to the game of cows and bulls. Can you guess the computers 4 digit code before the computer guesses yours?");
        System.out.println("- - - - - - - - - - -");
        String input = aiChoiceExceptions();
        if (input.equals("1")) {
            easyAiGame(codegenerator());
        } else if (input.equals("2")) {
            mediumAiGame(codegenerator());

        } else if (input.equals("3")) {
            hardAIgame(codegenerator());

        }


    }
    @SuppressWarnings("Duplicates")

    public HashMap<Integer, ArrayList<String>> hardAIgame(String computerCode) {


        ArrayList<String> toPrinttofileUserGuess = new ArrayList<>();
        ArrayList<String> toPrinttofileComputerGuess = new ArrayList<>();
        ArrayList<String> toPrinttoFileUserResults = new ArrayList<>();
        ArrayList<String> toPrinttoFileCompResults = new ArrayList<>();
        HashMap<Integer, ArrayList<String>> hmap = new HashMap<>();
        String userWinFlag = "false";
        String compWinFlag = "false";
        String drawflag = "false";
        ArrayList<String> combos = new ArrayList();

        String userCode = finalCheckUser();
        int guessCount = 7;

        //storage for file print later
        ArrayList<String> uCodeCompCode = new ArrayList<>();
        ArrayList<String> useCompDrawFlags = new ArrayList<>();
        uCodeCompCode.add(userCode);
        uCodeCompCode.add(computerCode);
        hmap.put(5,uCodeCompCode);
        useCompDrawFlags.add(userWinFlag);
        useCompDrawFlags.add(compWinFlag);
        useCompDrawFlags.add(drawflag);


        Random r = new Random();
        //user turn

        while (guessCount > 0) {
            hmap.put(1,toPrinttofileUserGuess);
            hmap.put(2,toPrinttoFileUserResults);
            hmap.put(3,toPrinttofileComputerGuess);
            hmap.put(4,toPrinttoFileCompResults);
            // user turn with results and win option
            if (guessCount == 7) {
                String guess = super.finalCheckComp();
                System.out.println("you guess: " + guess);
                toPrinttofileUserGuess.add(guess);

                String userResult = (super.cowsBullsFinder(guess, computerCode));
                System.out.println(userResult);
                toPrinttoFileUserResults.add(userResult);
                System.out.println("- - - - - - - -");

                if (guess.equals(computerCode)) {
                    System.out.println("Congrats!!! You have won the game !!!!!");
                    userWinFlag = "true";
                    useCompDrawFlags.set(0,userWinFlag);
                    hmap.put(6,useCompDrawFlags);
                    return hmap;

                }
            }

            //Computer first turn

            innerloop:
            while (guessCount == 7) {
                hmap.put(1,toPrinttofileUserGuess);
                hmap.put(2,toPrinttoFileUserResults);
                hmap.put(3,toPrinttofileComputerGuess);
                hmap.put(4,toPrinttoFileCompResults);

                String computerGuess = super.codegenerator();
                toPrinttofileComputerGuess.add(computerGuess);
                System.out.println("computer guess: " + computerGuess);
               String compResults =(super.cowsBullsFinder(computerGuess, userCode));
               toPrinttoFileCompResults.add(compResults);
                System.out.println(compResults);

                System.out.println("- - - - - - - -");
                //change combos for next guess
                ArrayList called = compSmartAlgorythm(computerGuess, userCode, everyCombo());
                combos = called;
                guessCount -= 1;
                System.out.println("there are " + guessCount + " guesses left");
                System.out.println("- - - - - - - - -");


                break innerloop;


            }

            //player turn after first turn
            hmap.put(1,toPrinttofileUserGuess);
            hmap.put(2,toPrinttoFileUserResults);
            hmap.put(3,toPrinttofileComputerGuess);
            hmap.put(4,toPrinttoFileCompResults);

            String guess = super.finalCheckComp();
            System.out.println("you guess: " + guess);
            toPrinttofileUserGuess.add(guess);

            String userResult = (super.cowsBullsFinder(guess, computerCode));
            System.out.println(userResult);
            toPrinttoFileUserResults.add(userResult);
            System.out.println("- - - - - - - -");

            if (guess.equals(computerCode)) {
                System.out.println("Congrats!!! You have won the game !!!!!");
                userWinFlag = "true";
                useCompDrawFlags.set(0,userWinFlag);
                hmap.put(6,useCompDrawFlags);
                return hmap;

            }


            //computer turn every time after
            //random guess from all the possibilities remaining

            hmap.put(1,toPrinttofileUserGuess);
            hmap.put(2,toPrinttoFileUserResults);
            hmap.put(3,toPrinttofileComputerGuess);
            hmap.put(4,toPrinttoFileCompResults);

            String nextGuess = combos.get(r.nextInt(combos.size()));
            toPrinttofileComputerGuess.add(nextGuess);
            System.out.println("computer guess: " + nextGuess);
            String compResult2 = (super.cowsBullsFinder(nextGuess, userCode));
            System.out.println(compResult2);
            toPrinttoFileCompResults.add(compResult2);
            System.out.println("- - - - - - - -");

            //change array list on a loop now decreasing the guesses every turn
            combos = compSmartAlgorythm(nextGuess, userCode, combos);

            // rest of game goes on as usual
            if (nextGuess.equals(userCode)) {
                System.out.println("Too bad the computer is smarter than you and won");
                compWinFlag = "true";
                useCompDrawFlags.set(1,compWinFlag);
                hmap.put(6,useCompDrawFlags);
                return hmap;

                // turn counter prompt
            } else {
                guessCount -= 1;
                System.out.println("there are " + guessCount + " guesses left");
                System.out.println("- - - - - - - - -");

            }
            // draw option
        }
        System.out.println("Turns are finished!! this game is a draw");
        drawflag = "true";
        useCompDrawFlags.set(2,drawflag);
        hmap.put(6,useCompDrawFlags);

        return hmap;


    }



}