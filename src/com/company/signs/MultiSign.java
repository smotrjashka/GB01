package com.company.signs;

import com.company.PureSign;
import com.company.SignPrioritized;

public class MultiSign extends PureSign {
    public MultiSign() {
        super(SignPrioritized.MULTI_SIGN);
    }

    @Override
    public double applySign(double firstDigit, double secondDigit) {
        return firstDigit*secondDigit;
    }
}
