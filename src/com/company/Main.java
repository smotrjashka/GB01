package com.company;

import java.util.*;

import static com.company.DigitUtils.*;
import static com.company.PriorityUtils.*;
import static com.company.ResultUtils.resultCount;

public class Main {

    public static void main(String[] args) {

        System.out.println("please use enter after you finish your expression");
        System.out.println("please write you mathematical expression here:");
        /// in my version I only use + - * / ^ ( )

        Scanner in = new Scanner(System.in);

        String inputString = in.nextLine().replace(" ", "");
        StringBuilder polishBlaBlaBlaThings = new StringBuilder();

        System.out.println("your entered:" + inputString);

        List<Character> signStackSimulator = new ArrayList<>(inputString.length());
        ArrayList<Long> digitStackSimulator = new ArrayList<>(inputString.length());

        //первый незанятый индекс
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

                System.out.println("sign is coming! polish now={" + polishBlaBlaBlaThings + "}");
                System.out.println("sign stack before:{" + Arrays.toString(signStackSimulator.toArray()) + "}");

                if (currentChar != ')') {
                    System.out.println("not end brecket");

                    //signStackSimulator.add(currentChar);

                    if (currentChar != '(' && signStackIndx>1) {
                        Character prevSign = signStackSimulator.get(signStackIndx-1);
                        int priorityDiff = checkPriorityForNewAndPreviousOperands(currentChar, prevSign);
                        if (priorityDiff <= 0) {
                                polishBlaBlaBlaThings.append(prevSign).append(" ");
                                    signStackSimulator.set(signStackIndx-1, currentChar);
                                    signStackSimulator.remove(signStackIndx);
                        } else {
                            signStackSimulator.add(currentChar);
                            signStackIndx++;
                        }
                    } else {
                        System.out.println("start breaket ");
                        signStackSimulator.add(currentChar);
                        signStackIndx++;

                    }

                } else {
                    System.out.println("end breaket");
                    int firstBreaketIndx = signStackSimulator.lastIndexOf('(');
                    signStackSimulator.set(firstBreaketIndx, ' ');
                    for (int j = signStackSimulator.size()-1; j>firstBreaketIndx; j--){
                            polishBlaBlaBlaThings.append(signStackSimulator.get(j)).append(" ");
                            signStackSimulator.remove(j);
                    }
                    signStackSimulator.remove(firstBreaketIndx);
                    signStackIndx = firstBreaketIndx;

                }
            }

        }


        // push for rest
        signStackSimulator.forEach( c -> {
            if (!c.equals(' ') ) {
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
