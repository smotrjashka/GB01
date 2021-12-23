package com.company.signs;

import com.company.PureSign;

public class MultiSign extends PureSign {
    public MultiSign() {
        super('*');
    }

    @Override
    public double applySign(double firstDigit, double secondDigit) {
        return firstDigit*secondDigit;
    }
}
