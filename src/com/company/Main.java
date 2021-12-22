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

        String inputString = in.nextLine().replace(" ", "");
        StringBuilder polishBlaBlaBlaThings = new StringBuilder();

        System.out.println("your entered:" + inputString);

        List<Character> signStackSimulator = new ArrayList<>(inputString.length());
        ArrayList<Long> digitStackSimulator = new ArrayList<>(inputString.length());

        int signStackIndx = 0;

        for(int i = 0; i < inputString.length(); i++){

            char currentChar = inputString.charAt(i);

            System.out.println("i="+i+" char=" + currentChar);

            if (isDigit(currentChar)){

                String inputStringS = inputString.substring(i).replaceFirst("[^0-9].*", "");
                /// here I suppose that we write correct expression because wiki say that is part of "polish expression" ideology

                long entireDigit = Long.parseLong(inputStringS);
                digitStackSimulator.add(entireDigit);
                polishBlaBlaBlaThings.append(inputStringS).append(" ");

            } else {

                if (currentChar != ')') {

                    signStackSimulator.add(currentChar);

                    if (currentChar != '(') {
                        int priorityDiff = checkPriorityForNewAndPreviousOperands(signStackSimulator, signStackIndx);
                        if (priorityDiff <= 0) {
                            char prevSign = findPreviousSign(signStackSimulator, signStackIndx);
                            if (prevSign != ' ') {
                                polishBlaBlaBlaThings.append(prevSign).append(" ");
                                int prevSignIndex = signStackSimulator.lastIndexOf(prevSign) - 1;

                                ///remove sign from stack
                                if (prevSignIndex >= 0) {
                                    signStackSimulator.set(prevSignIndex, currentChar);
                                    signStackSimulator.remove(prevSignIndex + 1);
                                }
                            }
                        }
                    } else {
                        signStackIndx++;
                    }

                } else {
                    System.out.println("end breaket");
                    int firstBreaketIndx = findFirstBreakedIndex(signStackSimulator, signStackIndx);
                    signStackSimulator.set(firstBreaketIndx, ' ');
                    for (firstBreaketIndx++; firstBreaketIndx<=signStackIndx; firstBreaketIndx++){
                        //we wouldnt have second condition but just in case
                        if (!signStackSimulator.get(firstBreaketIndx).equals(' ') && !signStackSimulator.get(firstBreaketIndx).equals('\u0000')){
                            polishBlaBlaBlaThings.append(signStackSimulator.get(firstBreaketIndx)).append(" ");
                            signStackSimulator.set(firstBreaketIndx, ' ');
                        }
                    }

                }
            }

        }


        signStackSimulator.forEach( c -> {
            /// \u0000 is an empty inizialized char in array
            if (!c.equals(' ') && !c.equals('\u0000')) {
                polishBlaBlaBlaThings.append(c).append(" ");
            }
        });

        System.out.println("go to count result:");

        double finalResult = 0;
        try {
            finalResult = resultCount(polishBlaBlaBlaThings.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        polishBlaBlaBlaThings.append(finalResultRounded(finalResult));

        System.out.println("Polish result:");
        System.out.println(polishBlaBlaBlaThings);

    }


}
