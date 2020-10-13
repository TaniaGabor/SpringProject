package com.solid.s.good;

public class Status {

    private BadEmployee badEmployee;

    public Status( BadEmployee badEmployee)
    {
        this.badEmployee=badEmployee;
    }

    public int calculatePay() {
        switch (this.badEmployee.getStatus()) {
            case "A":
                return 1;
            case "B":
                return 2;
            default:
                return 0;
        }
    }
}
