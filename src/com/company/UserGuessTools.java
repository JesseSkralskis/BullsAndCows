package com.company;

public class UserGuessTools {

    public String guess() {

        System.out.println("- - - - - - - - ");
        System.out.println("please guess the computers four digit code. Do not use any repeated numbers.");
        String userGuess = ictgradschool.industry.Keyboard.readInput();
        return userGuess;
    }




    public boolean exceptionCheck1(String userGuess) {
        // code for checking if user input is 4 digits.
        if (userGuess.length() != 4) {
            return false;

        } else {

            return true;
        }
    }

    public boolean exceptionCheck2(String userGuess) {

        // code for if input is not an int.
        try {
            int yourNumber = Integer.parseInt(userGuess);
        } catch (NumberFormatException ex) {
            return false;

        }
        return true;
    }

    public boolean repeatCheck(String userGuess) {
        // code to check that numbers dont repeat.
        String checker = "";
        String altered = userGuess;

        innerloop:
        while (checker.length() < userGuess.length()) {


            if (checker.contains(Character.toString(altered.charAt(0)))) {

                return false;
            } else {

                checker += altered.charAt(0);
                altered = altered.substring(1);

            }
        }
        return true;

    }
    @SuppressWarnings("Duplicates")

    public String finalCheckComp() {
        boolean flag = false;
        String finalGuessss = "";
        while (!flag) {
            String uGuess = guess();
            if (!exceptionCheck1(uGuess)) {
                System.out.println("Please use 4 digits no more no less.");

            } else if (!exceptionCheck2(uGuess)) {
                System.out.println("Please use only numbers");

            } else if (!repeatCheck(uGuess)) {
                System.out.println("Please dont use any number more than once");

            } else {
                flag = true;
                finalGuessss = uGuess;
            }
        }


        return finalGuessss;



    }
    public String playersCode(){
        System.out.println("- - - - - - - - - - - ");
        System.out.println("please pick a 4 digit secret code between 0 and 9 with no repeat numbers");
        String secretCode = ictgradschool.industry.Keyboard.readInput();
        return secretCode;
    }
    @SuppressWarnings("Duplicates")

    public String finalCheckUser() {
        boolean flag = false;
        String finalGuess = "";
        while (!flag) {
            String uGuesss = playersCode();
            if (!exceptionCheck1(uGuesss)) {
                System.out.println("Please use 4 digits no more no less.");

            } else if (!exceptionCheck2(uGuesss)) {
                System.out.println("Please use only numbers");

            } else if (!repeatCheck(uGuesss)) {
                System.out.println("Please dont use any number more than once");

            } else {
                flag = true;
                finalGuess = uGuesss;
            }
        }
        System.out.println("this is a valid code");
        return finalGuess;



    }



}