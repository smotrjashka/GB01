package com.company;

import java.util.*;

public class PriorityUtils {

    public static int findFirstBreakedIndex(List<Character> signStackSimulator, int signStackIndx) {

        List<Character> subList = signStackSimulator.subList(0, signStackIndx+1);
        return subList.lastIndexOf('(');

    }

    public static char findPreviousSign(List<Character> signStackSimulator, int signStackIndx) {
        if (signStackIndx == 0){
            return ' ';
        }
        //here I trimmed previously entry set -> we not need any check just return prev or ' ' if not any prev

        return signStackSimulator.get(signStackIndx-1);
    }

    ///we would return diff beatween new and prev operand (like in comparator function)
    public static int checkPriorityForNewAndPreviousOperands(List<Character> signStackSimulator, int newSignStackIndx) {
        if (newSignStackIndx == 0){
            return 100;    //TODO
        } else{
            int newOperadPriority = SignPrioritized.getPriorityNumberForSign(signStackSimulator.get(newSignStackIndx));
            int prevOperadPriority = SignPrioritized.getPriorityNumberForSign(signStackSimulator.get(newSignStackIndx-1));
            return newOperadPriority-prevOperadPriority;
        }
    }

}
