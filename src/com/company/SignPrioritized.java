package com.company;

import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicInteger;

public enum SignPrioritized {
    END_BREACKET(')', Priority.ABOVE_A_SUSPICION),  //after I change my mind about algorithm and not use this now
    START_BREACKET('(', Priority.UNDER_THE_LINE), // just for not to be popt
    POWER_SIGN('^', Priority.NORMAL),
    MULTI_SIGN('*', Priority.LOW),
    DIVIDE_SIGN('/', Priority.LOW),
    ADD_SIGN('+', Priority.LOWEST),
    SUBSTR_SIGN('-', Priority.LOWEST)
     ;


    SignPrioritized(char sign, Priority priority) {
        this.sign = sign;
        this.priority = priority;
    }

    private char sign;
    private Priority priority;

    private static EnumSet<SignPrioritized> allSigns = EnumSet.allOf(SignPrioritized.class);

    public static int getPriorityNumberForSign(char signCandidate){
        AtomicInteger priority = new AtomicInteger(-1);
        allSigns.forEach(signPrioritized -> {
            if (signCandidate == signPrioritized.sign){
                priority.set(signPrioritized.priority.getPriority());
            }
        });

        //for sign that not added normally we wouldnt be here
        //unless someone forgot to add new sign for our "calculator"
        /// maybe better to throw exception... I dont know
        return priority.get();
    }

    public static SignPrioritized getValueByChar(char signCandidate){
        final SignPrioritized[] sign = new SignPrioritized[1];
        allSigns.forEach(signPrioritized -> {
            if (signPrioritized.sign == signCandidate){
                sign[0] = signPrioritized;
            }
        });
        return sign[0];
    }

    public char getSign() {
        return sign;
    }

    public Priority getPriority() {
        return priority;
    }
}
