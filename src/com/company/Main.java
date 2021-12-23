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

        List<Character> signStackSimulator = new ArrayList<>(inputString.length());

        //первый незанятый индекс
        int signStackIndx = 0;

        for(int i = 0; i < inputString.length(); i++){

            char currentChar = inputString.charAt(i);

            System.out.println("i="+i+" char=" + currentChar);

            if (isDigit(currentChar)){

                String inputStringS = inputString.substring(i).replaceFirst("[^0-9].*", "");
                /// here I suppose that we write correct expression because wiki say that is part of "polish expression" ideology

                polishBlaBlaBlaThings.append(inputStringS).append(" ");

            } else {

                if (currentChar != ')') {

                    if (currentChar != '(' && signStackIndx>1
                            && (checkPriorityForNewAndPreviousOperands(currentChar, signStackSimulator.get(signStackIndx-1)) <= 0) ) {

                        polishBlaBlaBlaThings.append(signStackSimulator.get(signStackIndx-1)).append(" ");
                        signStackSimulator.set(signStackIndx-1, currentChar);
                        signStackSimulator.remove(signStackIndx);

                    } else {
                        signStackSimulator.add(currentChar);
                        signStackIndx++;

                    }

                } else {
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

        double finalResult = 0;
        try {
            finalResult = resultCount(polishBlaBlaBlaThings.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        polishBlaBlaBlaThings.append(finalResultRounded(finalResult));


        System.out.println(polishBlaBlaBlaThings);

    }


}
