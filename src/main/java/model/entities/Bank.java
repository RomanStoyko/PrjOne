package model.entities;

import view.util.localization.Localization;

import static view.util.localization.Keys.ITV;

public class Bank {

    private int itv;
    private String name;

    public Bank() {
    }

    public Bank(int itv, String name) {
        this.itv = itv;
        this.name = name;
    }

    public int getItv() {
        return itv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bank bank = (Bank) o;

        return itv == bank.itv;
    }

    @Override
    public int hashCode() {
        return itv;
    }

    @Override
    public String toString() {
        return name + " " + Localization.INSTANCE.getString(ITV) + " " + itv ;
    }
}
