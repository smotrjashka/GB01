package com.company;

public abstract class PureSign {

    public PureSign(Priority priority) {
        this.priority = priority;
    }

    public PureSign(Character character){
        priority = PriorityUtils.getPriorityForSign(character);
    }

    protected Priority priority;

    public abstract double applySign(double firstDigit, double secondDigit);
}
