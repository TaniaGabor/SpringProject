package com.solid.s.good;

public class Report {

    private BadEmployee badEmployee;

    public Report( BadEmployee badEmployee)
    {
        this.badEmployee=badEmployee;
    }

    public String reportHours() {
        return String.format("%s worked %d hours.\n", this.badEmployee.getName(), this.badEmployee.getHours());
    }
}
