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
        for (String element : splited) {
            if (isDigit(element)) {
                finalDigitPool.set(++digitIndx, Double.parseDouble(element));
            } else {

                try {
                    finalDigitPool.set(digitIndx - 1, findSignByString(element.charAt(0))
                            .applySign(finalDigitPool.get(digitIndx - 1), finalDigitPool.get(digitIndx)));
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

   private static PureSign findSignByString(Character signString) throws Exception {
       Map<Character, PureSign> map = Map.of('+', new AdditionSign(),
               '-', new SubstrSign(),
               '*', new MultiSign(),
               '/', new DivSign(),
               '^', new PowerSign()
               );

       if (map.containsKey(signString)) {
          return map.get(signString);
       }

        throw new Exception("poblem with sign!!");

   }

}
