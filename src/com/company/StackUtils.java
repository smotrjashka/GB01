package com.company;

import java.util.List;

import static com.company.PriorityUtils.*;

public class StackUtils {

    public static String finalResultRounded(double finalResult){
        if ((finalResult == Math.floor(finalResult)) && !Double.isInfinite(finalResult)) {
            //if we have integer value
            return  "" + ((int) finalResult);
        } else {
            //I opt not to round to integer if we have result that is a double value
            return  "" + finalResult;
        }
    }

    public static String checkCurrentResultForStartBreket(char currentChar, List<Character> signStackSimulator, int signStackIndx){
        String additionForPolish = "";
        if (currentChar != '(') {
            int priorityDiff = checkPriorityForNewAndPreviousOperands(signStackSimulator, signStackIndx);
            if (priorityDiff <= 0) {
                char prevSign = findPreviousSign(signStackSimulator, signStackIndx);
                if (prevSign != ' ') {
                    additionForPolish = (prevSign + " ");

                    ///remove sign from stack
                    int prevSignIndex = signStackIndx - 1;
                    if (prevSignIndex >= 0) {
                        signStackSimulator.remove(prevSignIndex);
                    }
                }
            }
        }

        return additionForPolish;
    }
}
