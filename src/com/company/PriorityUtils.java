package com.company;

public class PriorityUtils {


    public static int findFirstBreakedIndex(char[] signStackSimulator, int signStackIndx) {

        for (int i = signStackIndx; i >= 0; i--){
            if(signStackSimulator[i] == '(')
                return i;
        }
        //but if we assume that we work with correct data we wouldnt be here
        return -1;
    }

    public static char findPreviousSign(char[] signStackSimulator, int signStackIndx) {
        if (signStackIndx == 0){
            return ' ';
        }
        for (int i = signStackIndx-1; i>=0 ;i--){
            if (signStackSimulator[i] != ' '){
                return signStackSimulator[i];
            }
        }
        //if we emptied all stack
        return ' ';
    }

    public static int findPreviousSignIndex(char[] signStackSimulator, int signStackIndx) {
        if (signStackIndx == 0){
            return -2;
        }
        for (int i = signStackIndx-1; i>=0 ;i--){
            if (signStackSimulator[i] != ' '){
                return i;
            }
        }
        //if we emptied all stack
        return -1;
    }

    ///we would return diff beatween new and prev operand (like in comparator function)
    public static int checkPriorityForNewAndPreviousOperands(char[] signStackSimulator, int newSignStackIndx) {
        if (newSignStackIndx == 0){
            return 100;    //TODO
        } else{
            int newOperadPriority = SignPrioritized.getPriorityNumberForSign(signStackSimulator[newSignStackIndx]);
            int prevOperadPriority = SignPrioritized.getPriorityNumberForSign(signStackSimulator[newSignStackIndx-1]);
            return newOperadPriority-prevOperadPriority;
        }
    }

}
