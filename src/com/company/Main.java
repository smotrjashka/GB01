package com.company;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello, this is my implementation of useless program");
        System.out.println("please use enter after you finish your expression");
        System.out.println("please write you mathematical expression here:");
        /// in my version I only use + - * / ^ ( )

        // here normally i would use trim but as I am not sure which function I can use and which not
        // just for do not do it 2 times I will manually detect and eliminate the whitespaces
        //and I know that all written belove is along to be more efficient way but task itself about old structural way...

        Scanner in = new Scanner(System.in);

        String inputString = in.nextLine();
        String polishBlaBlaBlaThings = "";

        System.out.println("your entered:" + inputString);

        ///also I would use in normal real life regexp, but n real code we can use already written functions at all
        double result = 0;
        ///some I will use list or maybe hash map but I not sure what structures exactly I can use thats why I will use array
        char[] signStackSimulator = new char[inputString.length()];
        long[] digitStackSimulator = new long[inputString.length()];

        int signStackIndx = 0;
        int doubleStackIndex = 0;
        int i = 0;
        while ( i < inputString.length()){

            char currentChar = inputString.charAt(i);

            if (currentChar == ' '){
                i++;
                continue;
            }

            if (isDigit(currentChar)){
                String entireDigitCandidate = "" + currentChar;
                while(isNextDigit(i, inputString)){
                    //I would ignore string builder alert because of "simplicity" of used functions
                    entireDigitCandidate+=inputString.charAt(++i);
                    System.out.println("" + entireDigitCandidate);
                }
                /// here I suppose that we write correct expression because wiki say that is part of "polish expression" ideology
                long entireDigit = Long.parseLong(entireDigitCandidate);
                digitStackSimulator[doubleStackIndex++] = entireDigit;
                polishBlaBlaBlaThings += (entireDigit + " ");
            } else {
                if (currentChar != ')') {

                    signStackSimulator[signStackIndx] = currentChar;

                    if (currentChar != '(') {
                        int priorityDiff = checkPriorityForNewAndPreviousOperands(signStackSimulator, signStackIndx);
                        if (priorityDiff <= 0) {
                            char prevSign = findPreviousSign(signStackSimulator, signStackIndx);
                            if (prevSign != ' ') {
                                polishBlaBlaBlaThings += (prevSign + " ");
                            }
                            ///remove sign from stack
                            int prevSignIndex = findPreviousSignIndex(signStackSimulator, signStackIndx);
                            if (prevSignIndex >= 0) {
                                signStackSimulator[prevSignIndex] = ' ';
                            }
                        }
                    }

                    signStackIndx++;
                } else {

                    int firstBreaketIndx = findFirstBreakedIndex(signStackSimulator, signStackIndx);
                    signStackSimulator[firstBreaketIndx] = ' ';
                    for (firstBreaketIndx++; firstBreaketIndx<=signStackIndx; firstBreaketIndx++){
                        //we wouldnt have second condition but just in case
                        if (signStackSimulator[firstBreaketIndx]!=' ' && signStackSimulator[firstBreaketIndx] != '\u0000'){
                            polishBlaBlaBlaThings += (signStackSimulator[firstBreaketIndx] + " ");
                            signStackSimulator[firstBreaketIndx] = ' ';
                        }

                    }

                }
            }
            i++;

        }

        for (int i1 = 0; i1 < signStackSimulator.length; i1++) {
            /// \u0000 is an empty inizialized char in array
            if (signStackSimulator[i1] != ' ' && signStackSimulator[i1] != '\u0000'){
                polishBlaBlaBlaThings += (signStackSimulator[i1] + " ");
            }
        }



/*
        //debug purpose
        System.out.println("symbols:");
        for (char c : signStackSimulator){
            System.out.print(c + " ");
        }

        System.out.println();
        System.out.println("digits:");
        for (double d : digitStackSimulator){
            System.out.print(d + " ");
        }
        */

        System.out.println("go to count result:");

        double finalResult = resultCount(polishBlaBlaBlaThings);
        if ((finalResult == Math.floor(finalResult)) && !Double.isInfinite(finalResult)) {
            //if we have integer value
            polishBlaBlaBlaThings += "" + ((int) finalResult);
        } else {
            //I opt to not to round to integer if we have result that is a double value
            polishBlaBlaBlaThings += "" + finalResult;
        }

        System.out.println("polish result:");
        System.out.println(polishBlaBlaBlaThings);

    }

    private static double resultCount(String polishBlaBlaBlaThings) {
        double[] finalDigitPool = new double[polishBlaBlaBlaThings.length()/2];
        int digitIndx = -1;
        while (polishBlaBlaBlaThings.contains(" ")){
            int indxFirstOfSpace = polishBlaBlaBlaThings.indexOf(" ");
            String element = polishBlaBlaBlaThings.substring(0, indxFirstOfSpace);
            System.out.println("element " + element);
            polishBlaBlaBlaThings = polishBlaBlaBlaThings.substring(indxFirstOfSpace+1);
            System.out.println("rest of the line {" + polishBlaBlaBlaThings + "}");

            if (isDigit(element)){
                finalDigitPool[++digitIndx] = Double.parseDouble(element);
            } else {
                switch (element){
                    case "+":
                        finalDigitPool[digitIndx-1] += finalDigitPool[digitIndx];
                        break;
                    case "-":
                        finalDigitPool[digitIndx-1] -= finalDigitPool[digitIndx];
                        break;
                    case "*":
                        finalDigitPool[digitIndx-1] *= finalDigitPool[digitIndx];
                        break;
                    case "/":
             //           System.out.println("/ for " + finalDigitPool[digitIndx-1] + " / " + finalDigitPool[digitIndx]);
                        finalDigitPool[digitIndx-1] /= finalDigitPool[digitIndx];
               //         System.out.println("after / = {" + finalDigitPool[digitIndx-1] + "}");
                        break;
                    case "^":
                        finalDigitPool[digitIndx-1] = Math.pow(finalDigitPool[digitIndx-1], finalDigitPool[digitIndx]);
                        break;

                }

                finalDigitPool[digitIndx--] = 0.0;
            }

        }

        return finalDigitPool[0];
    }

    private static int findFirstBreakedIndex(char[] signStackSimulator, int signStackIndx) {

        for (int i = signStackIndx; i >= 0; i--){
            if(signStackSimulator[i] == '(')
                return i;
        }
        //but if we assume that we work with correct data we wouldnt be here
        return -1;
    }

    private static char findPreviousSign(char[] signStackSimulator, int signStackIndx) {
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

    private static int findPreviousSignIndex(char[] signStackSimulator, int signStackIndx) {
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
    private static int checkPriorityForNewAndPreviousOperands(char[] signStackSimulator, int newSignStackIndx) {
        if (newSignStackIndx == 0){
            return 100;    //TODO
        } else{
            int newOperadPriority = getSignPriority(signStackSimulator[newSignStackIndx]);
            int prevOperadPriority = getSignPriority(signStackSimulator[newSignStackIndx-1]);
            return newOperadPriority-prevOperadPriority;
        }
    }

    private static int getSignPriority(char c) {
        switch (c) {
            case ')':
                return 10;      //after I change my mind about algorithm and not use this now
            case '(':
                return 0;       // just for not to be popt
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
        }
        return -100500; ///for error signaling but we not suppose to have this case
    }

    private static boolean isNextDigit(int i, String inputString) {
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


    static boolean isDigit(String digitCandidate){

        try {
            int unusedIntForAvoidCompilationOptimisation = Integer.parseInt(digitCandidate);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    //it has written function in java too
    static boolean isDigit(char singleDigitCandidate){

        try {
            int unusedIntForAvoidCompilationOptimisation = Integer.parseInt(""+singleDigitCandidate);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }



}
