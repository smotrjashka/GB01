package com.company;

import java.util.*;

public class PriorityUtils {

    private static final Map<Character, Priority> priorityMap = Map.of('^', Priority.NORMAL, '*', Priority.LOW, '/', Priority.LOW,
            '+', Priority.LOWEST, '-', Priority.LOWEST);

    public static Priority getPriorityForSign(Character signChar){
        return priorityMap.get(signChar);
    }

    private static int getPriorityNumber(Character signChar){
        return priorityMap.get(signChar).getPriority();
    }

    ///we would return diff beatween new and prev operand (like in comparator function)
    public static int checkPriorityForNewAndPreviousOperands(Character newSign, Character prevSign) {
        if (newSign.equals(prevSign)){
            return 0;
        } else{
            return getPriorityNumber(newSign)-getPriorityNumber(prevSign);
        }
    }


    public static String finalResultRounded(double finalResult){
            return  "" + ( ((finalResult == Math.floor(finalResult)) && !Double.isInfinite(finalResult)) ? (int) finalResult
                    : finalResult);

    }

}
