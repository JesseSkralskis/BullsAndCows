package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class EasyAI extends GameTools {




    @SuppressWarnings("Duplicates")
    public HashMap<Integer, ArrayList<String>> easyAiGame(String computerCode) {

        // user secret code with all exceptions and prompts
        ArrayList<String> toPrinttofileUserGuess = new ArrayList<>();
        ArrayList<String> toPrinttofileComputerGuess = new ArrayList<>();
        ArrayList<String> toPrinttoFileUserResults = new ArrayList<>();
        ArrayList<String> toPrinttoFileCompResults = new ArrayList<>();
        HashMap<Integer, ArrayList<String>> hmap = new HashMap<>();
        String userWinFlag = "false";
        String compWinFlag = "false";
        String drawflag = "false";




        String userCode = finalCheckUser();
        System.out.println(userCode);
        System.out.println("- - - - - - - -");
        int guessCount = 7;

        ArrayList<String> uCodeCompCode = new ArrayList<>();
        ArrayList<String> useCompDrawFlags = new ArrayList<>();
        uCodeCompCode.add(userCode);
        uCodeCompCode.add(computerCode);
        hmap.put(5,uCodeCompCode);
        useCompDrawFlags.add(userWinFlag);
        useCompDrawFlags.add(compWinFlag);
        useCompDrawFlags.add(drawflag);




        while (guessCount > 0) {
            hmap.put(1,toPrinttofileUserGuess);
            hmap.put(2,toPrinttoFileUserResults);
            hmap.put(3,toPrinttofileComputerGuess);
            hmap.put(4,toPrinttoFileCompResults);


            // user turn with results and win option
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

            //computer turn with results and win option
            String computerGuess = super.codegenerator();
            toPrinttofileComputerGuess.add(computerGuess);
            System.out.println("computer guess: " + computerGuess);
            String compResults = (super.cowsBullsFinder(computerGuess, userCode));
            System.out.println(compResults);
            toPrinttoFileCompResults.add(compResults);
            System.out.println("- - - - - - - -");

            // computer win sequence
            if (computerGuess.equals(userCode)) {
                System.out.println("Too bad the computer is smarter than you and won");
                compWinFlag = "true";
                useCompDrawFlags.set(1,compWinFlag);
                hmap.put(6,useCompDrawFlags);
                return hmap;




                // turn counter prompt
            } else {
                guessCount -= 1;
                System.out.println("there are " + guessCount + " guesses left");

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






