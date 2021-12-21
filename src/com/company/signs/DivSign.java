package com.company.signs;

import com.company.PureSign;
import com.company.SignPrioritized;

public class DivSign extends PureSign {
    public DivSign() {
        super(SignPrioritized.DIVIDE_SIGN);
    }

    @Override
    public double applySign(double firstDigit, double secondDigit) {
        return firstDigit/secondDigit;
    }
}
