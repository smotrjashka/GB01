package com.company.signs;

import com.company.PriorityUtils;
import com.company.PureSign;

public class AdditionSign extends PureSign {


    public AdditionSign() {
        super('+');
    }

    @Override
    public double applySign(double firstDigit, double secondDigit) {
        return (firstDigit + secondDigit);
    }
}
