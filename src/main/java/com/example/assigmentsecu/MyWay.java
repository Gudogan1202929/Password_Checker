package com.example.assigmentsecu;

public class MyWay {

    //according that the char small 26 i will give this 2
    //and the char capital 26 i will give this 2
    //and the number 10 i will give this 1
    //and the special char 32 i will give this 3
    //and the length of the password i will give this 2

    static boolean haveSmallChar;
    static boolean haveCapitalChar;
    static boolean haveNumber;
    static boolean haveSpecialChar;
    static int lengthOfPassword;

    static String passwordChecker (String password){

        String result = "";

        if (password.length() < 8) {
            return "Your password is too short";
        }

        haveSmallChar = false;
        lengthOfPassword = password.length();
        haveCapitalChar = false;
        haveNumber = false;
        haveSpecialChar = false;
        int Score = 0;

        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) >= 'a' && password.charAt(i) <= 'z') {
                haveSmallChar = true;
            }
            else if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') {
                haveCapitalChar = true;}
            else if (password.charAt(i) >= '0' && password.charAt(i) <= '9') {
                haveNumber = true;
            }else {
                haveSpecialChar = true;
            }
        }

        if (haveSmallChar) {
            Score += 2;
        }
        if (haveCapitalChar) {
            Score += 2;
        }
        if (haveNumber) {
            Score += 1;
        }
        if (haveSpecialChar) {
            Score += 3;
        }
        if (lengthOfPassword >= 12) {
            Score += 2;
        }

        if (Score >= 8) {
            result+= "Your password is strong";
        }
        else if (Score >= 5 && Score <= 8) {
            result+=  "Your password is medium";
        }
        else if (Score < 5) {
            result+=  "Your password is weak";
        }

        if (!haveSmallChar) {
            result+= "\nYou should add small characters";
        }
        if (!haveCapitalChar) {
            result+= "\nYou should add capital characters";
        }
        if (!haveNumber) {
            result+= "\nYou should add numbers";
        }
        if (!haveSpecialChar) {
            result+= "\nYou should add special characters";
        }
        if (lengthOfPassword < 12) {
            result+= "\nYou should add more characters";
        }
        return result;
    }
}
