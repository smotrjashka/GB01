package com.company.signs;

import com.company.PureSign;

public class SubstrSign extends PureSign {
    public SubstrSign() {
        super('-');
    }

    @Override
    public double applySign(double firstDigit, double secondDigit) {
        return firstDigit-secondDigit;
    }
}
