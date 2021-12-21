package com.company;

import static com.company.DigitUtils.isNextDigit;
import static com.company.PriorityUtils.*;

public class StackUtils {

    public static String processDigit(char currentChar, int i, String inputString, long[] digitStackSimulator,
                                      int doubleStackIndex){
        String entireDigitCandidate = "" + currentChar;
        while(isNextDigit(i, inputString)){
            //I would ignore string builder alert because of "simplicity" of used functions
            entireDigitCandidate+=inputString.charAt(++i);
            System.out.println("" + entireDigitCandidate);
        }
        /// here I suppose that we write correct expression because wiki say that is part of "polish expression" ideology
        long entireDigit = Long.parseLong(entireDigitCandidate);
        digitStackSimulator[doubleStackIndex] = entireDigit;
        return (entireDigit + " ");
    }

    public static String finalResultRounded(double finalResult){
        if ((finalResult == Math.floor(finalResult)) && !Double.isInfinite(finalResult)) {
            //if we have integer value
            return  "" + ((int) finalResult);
        } else {
            //I opt not to round to integer if we have result that is a double value
            return  "" + finalResult;
        }
    }

    public static String checkCurrentResultForStartBreket(char currentChar, char[] signStackSimulator, int signStackIndx){
        String additionForPolish = "";
        if (currentChar != '(') {
            int priorityDiff = checkPriorityForNewAndPreviousOperands(signStackSimulator, signStackIndx);
            if (priorityDiff <= 0) {
                char prevSign = findPreviousSign(signStackSimulator, signStackIndx);
                if (prevSign != ' ') {
                    additionForPolish = (prevSign + " ");
                }
                ///remove sign from stack
                int prevSignIndex = findPreviousSignIndex(signStackSimulator, signStackIndx);
                if (prevSignIndex >= 0) {
                    signStackSimulator[prevSignIndex] = ' ';
                }
            }
        }

        return additionForPolish;
    }
}
