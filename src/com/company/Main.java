package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.company.DigitUtils.*;
import static com.company.PriorityUtils.*;
import static com.company.ResultUtils.resultCount;
import static com.company.StackUtils.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello, this is my implementation of useless program");
        System.out.println("please use enter after you finish your expression");
        System.out.println("please write you mathematical expression here:");
        /// in my version I only use + - * / ^ ( )

        Scanner in = new Scanner(System.in);

        String inputString = in.nextLine().replace(' ', '\u0000');
        System.out.println("after remove all spaces:{" + inputString + "}");
        StringBuilder polishBlaBlaBlaThings = new StringBuilder();

        System.out.println("your entered:" + inputString);

         double result = 0;
        List<Character> signStackSimulator = new ArrayList<>(inputString.length())
        List<Long> digitStackSimulator = new ArrayList<>(inputString.length());

        int signStackIndx = 0;
        int doubleStackIndex = 0;
        for(int i = 0; i < inputString.length();){

            char currentChar = inputString.charAt(i);

            if (isDigit(currentChar)){

                polishBlaBlaBlaThings.append(processDigit(currentChar, i, inputString, digitStackSimulator, doubleStackIndex));
                doubleStackIndex++;

            } else {

                if (currentChar != ')') {

                    signStackSimulator[signStackIndx] = currentChar;
                    polishBlaBlaBlaThings.append(checkCurrentResultForStartBreket(currentChar, signStackSimulator, signStackIndx));
                    signStackIndx++;

                } else {

                    int firstBreaketIndx = findFirstBreakedIndex(signStackSimulator, signStackIndx);
                    signStackSimulator[firstBreaketIndx] = ' ';
                    for (firstBreaketIndx++; firstBreaketIndx<=signStackIndx; firstBreaketIndx++){
                        //we wouldnt have second condition but just in case
                        if (signStackSimulator[firstBreaketIndx]!=' ' && signStackSimulator[firstBreaketIndx] != '\u0000'){
                            polishBlaBlaBlaThings.append(signStackSimulator[firstBreaketIndx]).append(" ");
                            signStackSimulator[firstBreaketIndx] = ' ';
                        }
                    }

                }
            }
            i++;

        }

        for (int i1 = 0; i1 < signStackSimulator.size(); i1++) {
            /// \u0000 is an empty inizialized char in array
            if (!signStackSimulator.get(i1).equals(' ') && !signStackSimulator.get(i1).equals('\u0000')){
                polishBlaBlaBlaThings.append(signStackSimulator[i1]).append(" ");
            }
        }

        System.out.println("go to count result:");

        double finalResult = 0;
        try {
            finalResult = resultCount(polishBlaBlaBlaThings.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        polishBlaBlaBlaThings.append(finalResultRounded(finalResult));

        System.out.println("polish result:");
        System.out.println(polishBlaBlaBlaThings);

    }


}
