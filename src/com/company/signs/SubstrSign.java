package com.company.signs;

import com.company.PureSign;
import com.company.SignPrioritized;

public class SubstrSign extends PureSign {
    public SubstrSign() {
        super(SignPrioritized.SUBSTR_SIGN);
    }

    @Override
    public double applySign(double firstDigit, double secondDigit) {
        return firstDigit-secondDigit;
    }
}
