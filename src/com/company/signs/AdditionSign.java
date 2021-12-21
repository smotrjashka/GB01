package com.company.signs;

import com.company.PureSign;
import com.company.SignPrioritized;

public class AdditionSign extends PureSign {


    public AdditionSign() {
        super(SignPrioritized.ADD_SIGN);
    }

    @Override
    public double applySign(double firstDigit, double secondDigit) {
        return (firstDigit + secondDigit);
    }
}
