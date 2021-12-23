package com.company;

import java.util.*;

public class PriorityUtils {

    private static Map<Character, Priority> priorityMap = Map.of('^', Priority.NORMAL, '*', Priority.LOW, '/', Priority.LOW,
            '+', Priority.LOWEST, '-', Priority.LOWEST);

    public static Priority getPriorityForSign(Character signChar){
        return priorityMap.get(signChar);
    }

    ///we would return diff beatween new and prev operand (like in comparator function)
    public static int checkPriorityForNewAndPreviousOperands(Character newSign, Character prevSign) {
        if (newSign.equals(prevSign)){
            return 0;
        } else{
            int newOperadPriority = priorityMap.get(newSign).getPriority();
            int prevOperadPriority = priorityMap.get(prevSign).getPriority();
            return newOperadPriority-prevOperadPriority;
        }
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

}
