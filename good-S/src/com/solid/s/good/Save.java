package com.solid.s.good;

public class Save {

    private BadEmployee badEmployee;

    public Save( BadEmployee badEmployee)
    {
        this.badEmployee=badEmployee;
    }

    public void save() {
        System.out.printf("%s saved to database.\n", this.badEmployee.getName());
    }
}
