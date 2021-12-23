package com.company;

import java.util.*;
import java.util.stream.Stream;

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

    private boolean checkCkreange(String inputString){

        boolean resultBoolean = true;

        List<Character> artificial = new ArrayList<>();
        for (char c : inputString.toCharArray()) {
            artificial.add(c);
        }

        if(artificial.stream().anyMatch(c -> ((c<48||c>57)&&!c.equals('(')&&!c.equals(')')
                &&!c.equals('*')&&!c.equals('/')&&!c.equals('+')&&!c.equals('-')&&!c.equals('^')))){
            return false;
        }

        long numbersAmount = artificial.stream().filter(c -> (c>=48&&c<=57)).count();
        if (artificial.size() - numbersAmount > numbersAmount){
            return false;
        }

        Stream<Character> onlyBreakets = artificial.stream().filter(character -> {
            return character.equals('(') || character.equals(')');
        });

        if (onlyBreakets.filter(c -> c.equals('(')).count() != onlyBreakets.filter(c -> c.equals(')')).count()){
            return false;
        }

        return true;
    }
}
