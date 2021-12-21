package com.company;

public class DigitUtils {

    public static boolean isNextDigit(int i, String inputString) {
        if (i+1==inputString.length())
            return false;
        else {
            char nextChar = inputString.charAt(i+1);
            if (nextChar == ' ')
                return false;
            else
                return isDigit(nextChar);
        }



    }


    public static boolean isDigit(String digitCandidate){

        try {
            int unusedIntForAvoidCompilationOptimisation = Integer.parseInt(digitCandidate);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }


    //it has written function in java too
    public static boolean isDigit(char singleDigitCandidate){

        try {
            int unusedIntForAvoidCompilationOptimisation = Integer.parseInt(""+singleDigitCandidate);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

}
