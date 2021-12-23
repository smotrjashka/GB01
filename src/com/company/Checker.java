package com.company;

import java.util.*;

public class Checker {
    public static boolean checkInput(String inputString){

        if (inputString.matches(".*[^0-9^/*+-].*")){
            return false;
        }
        char firstChar = inputString.charAt(0);
        if (firstChar!='('&&!( firstChar>=48&&firstChar<=57))
            return false;

       if (inputString.matches(".*[/^*+-]{2}.*"))
           return false;

       if (inputString.contains("(")||inputString.contains(")")){

          int counter = 0;

           char[] charArr = inputString.toCharArray();

           for (char c : charArr) {
               if (c == '(') {
                   counter++;
               } else if (c == ')') {
                   if (counter <= 0) {
                       return false;
                   } else {
                       counter--;
                   }
               }
           }


       }


        return true;
    }
}
