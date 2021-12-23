package com.company.signs;

import com.company.PureSign;

public class DivSign extends PureSign {
    public DivSign() {
        super('/');
    }

    @Override
    public double applySign(double firstDigit, double secondDigit) {
        return firstDigit/secondDigit;
    }
}
