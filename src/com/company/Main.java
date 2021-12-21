package com.company;

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

                polishBlaBlaBlaThings += processDigit(currentChar, i, inputString, digitStackSimulator, doubleStackIndex);
                doubleStackIndex++;

            } else {

                if (currentChar != ')') {

                    signStackSimulator[signStackIndx] = currentChar;
                    polishBlaBlaBlaThings += checkCurrentResultForStartBreket(currentChar, signStackSimulator, signStackIndx);
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

        System.out.println("go to count result:");

        double finalResult = 0;
        try {
            finalResult = resultCount(polishBlaBlaBlaThings);
        } catch (Exception e) {
            e.printStackTrace();
        }

        polishBlaBlaBlaThings += finalResultRounded(finalResult);

        System.out.println("polish result:");
        System.out.println(polishBlaBlaBlaThings);

    }


}
