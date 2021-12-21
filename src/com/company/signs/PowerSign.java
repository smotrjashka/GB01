package com.company.signs;

import com.company.PureSign;
import com.company.SignPrioritized;

public class PowerSign extends PureSign {
    public PowerSign() {
        super(SignPrioritized.POWER_SIGN);
    }

    @Override
    public double applySign(double firstDigit, double secondDigit) {
        return Math.pow(firstDigit, secondDigit);
    }
}
