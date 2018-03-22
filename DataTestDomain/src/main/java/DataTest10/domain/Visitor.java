package DataTest10.domain;

import DataTest10.persistence.Entity;

public class Visitor extends Entity {



    private String name;
    private  String surname;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
