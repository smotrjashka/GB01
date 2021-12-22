package com.company;

public class DigitUtils {

    public static boolean isDigit(String digitCandidate){

        boolean forDebug = digitCandidate.matches("[0-9]");
        System.out.println("srt:" + digitCandidate + " is " + forDebug);
        return forDebug;
    }


    //it has written function in java too
    public static boolean isDigit(char singleDigitCandidate){
        return singleDigitCandidate>=48&&singleDigitCandidate<=57;
    }

}
