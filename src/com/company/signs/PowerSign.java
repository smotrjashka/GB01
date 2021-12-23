package com.company.signs;

import com.company.PureSign;

public class PowerSign extends PureSign {
    public PowerSign() {
        super('*');
    }

    @Override
    public double applySign(double firstDigit, double secondDigit) {
        return Math.pow(firstDigit, secondDigit);
    }
}
