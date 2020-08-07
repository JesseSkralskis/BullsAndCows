package com.company;

import java.io.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class GameTools extends UserGuessTools {


    // method for scoring amount of cows and bulls
    public String cowsBullsFinder(String userguess, String compCode) {
        String sameNumbers = "";
        String bulls = "";
        //iterates through places similiar characters in a newNumbers string.
        for (int i = 0; i < userguess.length(); i++) {
            if (compCode.contains(Character.toString(userguess.charAt(i)))) {
                sameNumbers += userguess.charAt(i);

            }
        }

        // checks for same characters at same index which are bulls
        for (int j = 0; j < 4; j++) {
            char chA = userguess.charAt(j);
            char chB = compCode.charAt(j);
            if (chA == chB) {
                bulls += userguess.charAt(j);


            }


        }
        // keeps track of bulls and cows
        int bullsCount = bulls.length();
        int cowsCount = sameNumbers.length() - bulls.length();


        return (("result: " + bullsCount + "bulls" + " and " + cowsCount + "cows"));
    }

    // method for generating 4 digit non repeating codes between 0 and 9
    public String codegenerator() {
        String code = "";

        while (code.length() < 4) {
            int num = ((int) (Math.random() * 10));
            String numgen = Integer.toString(num);
            if (code.contains(numgen)) {
                //loop again
            } else {
                code += numgen;

            }

        }

        return code;
    }

    // method used for the AI in hard mode. prunes a list with all possibilities accordingly.
    public ArrayList<String> compSmartAlgorythm(String compguess, String userCode, ArrayList nextList) {

        Iterator it = nextList.iterator();
        ArrayList<String> newOptions = new ArrayList();
        while (it.hasNext()) {
            String string = (String) it.next();
            if (cowsBullsFinder(string, compguess).equals(cowsBullsFinder(compguess, userCode))) {


                newOptions.add(string);

            }


        }
        return newOptions;
    }

    //method that tests if a guess has already been used by computer in Medium AI mode

    public boolean checker(ArrayList<String> guesses) {
        HashSet set = new HashSet();
        for (int i = 0; i < guesses.size(); i++) {
            boolean val = set.add(guesses.get(i));
            if (val == false) {
                return val;
            }
        }
        return true;
    }

    // method that prints out every possible none repeating 4 digit code between 0 and 9
    public ArrayList<String> everyCombo() {
        ArrayList<String> every = new ArrayList<String>();

        for (int i = 0; i <= 9999; i++) {
            if (i < 10) {
                every.add("000" + i);
            } else if (i < 100) {
                every.add("00" + i);
            } else if (i < 1000) {
                every.add("0" + i);
            } else {
                every.add("" + i);
            }
        }

        Iterator it = every.iterator();
        while (it.hasNext()) {
            String string = (String) it.next();
            if (!repeatCheck(string)) {
                it.remove();
            }
        }


        return every;

    }


    // method that navigates players choice for picking AI level and prompts them if input is incorrect
    public String aiChoiceExceptions() {

        String choiceHolder = "";
        boolean flag = false;
        while (!flag) {

            System.out.println("Please pick an AI for the computer. 1 for easy AI, 2 for medium AI, or 3 for Hard AI");
            String choice = ictgradschool.industry.Keyboard.readInput();
            if (!exceptionCheck2(choice)) {
                System.out.println("please only use digits");


            } else if (choice.length() != 1) {
                System.out.println("Please choose a number that is only 1 digit");
            } else if (Integer.parseInt(choice) > 3 || Integer.parseInt(choice) < 1) {
                System.out.println("only choose 1 for easy, 2 for medium, or three for hard");
            } else {
                flag = true;
                choiceHolder = choice;

            }

        }


        return choiceHolder;
    }


    // method that asks a player for a file name and prompts them to pick a valid file if it isnt. returns list of codes.
    public ArrayList exceptionNavigator() {
        ArrayList<String> codes = new ArrayList<>();
        ArrayList<String> holder = new ArrayList<>();
        boolean thrown = false;
        boolean flag = true;
        while (flag) {
            thrown = false;
            System.out.println("Please enter a file name files are being read from the src file.");
            String txtFile = ictgradschool.industry.Keyboard.readInput();
            File myFile = new File("src/" + txtFile);

            try (BufferedReader reader = new BufferedReader(new FileReader(myFile))) {


                String line = null;
                while ((line = reader.readLine()) != null) {
                    codes.add(line);
                }

            } catch (IOException e) {
                System.out.println("Please pick a proper file name that exists");
                thrown = true;
            }


            if (!thrown) {
                flag = false;

            }
            holder = codes;

        }

        return holder;

    }
    public void doesUserWantPrintTxt(HashMap<Integer, ArrayList<String>> hmap) {

        ArrayList<String> uGuess = hmap.get(1);
        ArrayList<String> uResults =hmap.get(2);
        ArrayList<String> compguess =hmap.get(3);
        ArrayList<String> compResults =hmap.get(4);
        ArrayList<String> userCodecompCode = hmap.get(5);
        String userCode = userCodecompCode.get(0);
        String computerCode = userCodecompCode.get(1);
        ArrayList<String> flags = hmap.get(6);
        String userWinFlag = flags.get(0);
        String compWinFlag = flags.get(1);
        String drawFlag = flags.get(2);


        System.out.println("Would you like to save the results of this epic game to a text file?");
        System.out.println(("Please press 1 if yes you would like to save or press 2 if you would rather not"));
        String answer = ictgradschool.industry.Keyboard.readInput();
        if (answer.equals("1")) {
            System.out.println("please name the file");
            String fileName = ictgradschool.industry.Keyboard.readInput();
            File file = new File("src/" + fileName);
            FileWriter fw = null;
            try {
                fw = new FileWriter(file);
            } catch (IOException e) {
                System.out.println("IO error");
            }
            PrintWriter pw = new PrintWriter(fw);
            pw.println("Bulls and Cows game Results.");
            pw.println("Your Code: " + userCode);
            pw.println("Computer's Code: " + computerCode);
            pw.println("- - - - - - - -");
            int turnCount = 1;
            while (compResults.size() > 0) {
                pw.println("Turn" + turnCount);
                pw.printf("You Guessed " + uGuess.get(0) + ", ");
                uGuess.remove(0);
                pw.println("scoring the " + uResults.get(0));
                uResults.remove(0);
                pw.printf("Computer guessed " + compguess.get(0) + ", ");
                compguess.remove(0);
                pw.println("scoring the " + compResults.get(0));
                compResults.remove(0);
                pw.println("- - - - - - -");
                turnCount += 1;

            }
            if (userWinFlag.equals("true")) {
                pw.println("You won!!!!");
                pw.close();
                return;

            } else if (compWinFlag.equals("true")) {
                pw.println("The Computer Won");
                pw.close();
                return;
            } else if (drawFlag.equals("true")) {
                pw.println("The Game was a Draw");
                pw.close();
                return;





            }
            pw.close();
        }


    }


}








