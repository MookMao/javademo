package com.mook.java.polymorphism;

import java.util.Objects;

public class Manager extends Employee {
    private double bonus;

    public Manager(String name, double salary, int year, int month, int day, double bonus) {
        super(name, salary, year, month, day);
        this.bonus = bonus;
    }

    public double getSalary()
    {
        double baseSalary = super.getSalary();
        return baseSalary + bonus;
    }

    public void setBonus(double b)
    {
        this.bonus = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        return Double.compare(manager.bonus, bonus) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), bonus);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "bonus=" + bonus +
                '}';
    }

    public static void main(String[] args) {
        Employee e;
        e = new Employee("Wang", 10.0, 2019, 1, 1);
        System.out.println(e.getSalary()); // 10.0
        e = new Manager("Li", 10.0, 2019, 1, 1, 1);
        System.out.println(e.getSalary()); // 11.0
        // e.setBonus(1.0); // Error
        System.out.println(e.getClass());
    }
}
