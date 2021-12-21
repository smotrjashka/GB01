package com.company;

public enum Priority {
    ///first and last not used now, but just in case
    ABOVE_A_SUSPICION(10),

    NORMAL(3),
    LOW(2),
    LOWEST(1),
    UNDER_THE_LINE(0);


    Priority(int priority_number) {
        this.priority = priority_number;
    }

    private int priority;

    public int getPriority() {
        return priority;
    }


}
