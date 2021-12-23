package com.company;

import com.company.signs.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static com.company.DigitUtils.isDigit;

public class ResultUtils {

    public static double resultCount(String polishBlaBlaBlaThings) throws Exception {
        ArrayList<Double> finalDigitPool = new ArrayList<>(polishBlaBlaBlaThings.length()/2 + 1);
        int digitIndx = -1;
        ArrayList<String> splited = new ArrayList<>();
        Collections.addAll(splited, polishBlaBlaBlaThings.split(" "));
        System.out.println("polish{" +polishBlaBlaBlaThings + "}");
        for (String element : splited) {
            System.out.println("step {" + element + "}");
            if (isDigit(element)) {
                finalDigitPool.add(Double.parseDouble(element));
                digitIndx++;
            } else {

                try {
                    Map<Character, PureSign> map = Map.of(
                            '+', new AdditionSign(),
                            '-', new SubstrSign(),
                            '*', new MultiSign(),
                            '/', new DivSign(),
                            '^', new PowerSign()
                    );



                    finalDigitPool.set(digitIndx - 1, map.get(element.charAt(0)).applySign(finalDigitPool.get(digitIndx - 1), finalDigitPool.get(digitIndx)));
                    finalDigitPool.remove(digitIndx--);
                } catch (ArithmeticException ex) {
                    ex.printStackTrace();
                    throw new Exception("Division by zero!");
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new Exception("problem with sign");
                }

            }

        }

        return finalDigitPool.get(0);
    }


}
