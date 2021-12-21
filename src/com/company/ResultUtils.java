package com.company;

import com.company.signs.*;

import static com.company.DigitUtils.isDigit;

public class ResultUtils {

    public static double resultCount(String polishBlaBlaBlaThings) throws Exception {
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

                PureSign signByString = null;
                try {
                    signByString = findSignByString(element);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new Exception("problem with sign");
                }

                finalDigitPool[digitIndx-1] = signByString.applySign(finalDigitPool[digitIndx-1], finalDigitPool[digitIndx]);
                finalDigitPool[digitIndx--] = 0.0;
            }

        }

        return finalDigitPool[0];
    }

   private static PureSign findSignByString(String signString) throws Exception {
        PureSign pureSign;
       switch (signString){
           case "+":
               pureSign = new AdditionSign();
               break;
           case "-":
               pureSign = new SubstrSign();
               break;
           case "*":
               pureSign = new MultiSign();
               break;
           case "/":
               pureSign = new DivSign();
               break;
           case "^":
               pureSign = new PowerSign();
               break;
           default:
               throw new Exception("poblem with sign!!");

       }

       return pureSign;
   }

}
