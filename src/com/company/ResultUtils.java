package com.company;

import com.company.signs.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import static com.company.DigitUtils.isDigit;

public class ResultUtils {

    static final Map<Character, PureSign> map = Map.of(
            '+', new AdditionSign(),
            '-', new SubstrSign(),
            '*', new MultiSign(),
            '/', new DivSign(),
            '^', new PowerSign()
    );

    public static double resultCount(String polishBlaBlaBlaThings) throws Exception {
        ArrayList<Double> finalDigitPool = new ArrayList<>(polishBlaBlaBlaThings.length()/2 + 1);
        final int[] digitIndx = {-1};
        ArrayList<String> splited = new ArrayList<>();
        Collections.addAll(splited, polishBlaBlaBlaThings.split(" "));
        splited.forEach(element -> {
            if (isDigit(element)) {
                finalDigitPool.add(Double.parseDouble(element));
                digitIndx[0]++;
            } else {

                try {
                    finalDigitPool.set(digitIndx[0] - 1,
                            map.get(element.charAt(0)).applySign(finalDigitPool.get(digitIndx[0] - 1), finalDigitPool.get(digitIndx[0])));
                    finalDigitPool.remove(digitIndx[0]--);
                } catch (ArithmeticException ex) {
                    ex.printStackTrace();
                    System.out.println("Division by zero!");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("problem with sign");
                }

            }

        });

        return finalDigitPool.get(0);
    }


}
