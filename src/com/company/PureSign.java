package com.company;

public abstract class PureSign {

    public PureSign(SignPrioritized signPrioritized) {
        this.signPrioritized = signPrioritized;
    }

    protected SignPrioritized signPrioritized;

    public abstract double applySign(double firstDigit, double secondDigit);
}
