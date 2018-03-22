package DataTest10.domain;

import DataTest10.persistence.Entity;

public class Department extends Entity {


    private String description;
    private int accessPolicy;



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAccessPolicy() {
        return accessPolicy;
    }

    public void setAccessPolicy(int accessPolicy) {
        this.accessPolicy = accessPolicy;
    }


//    public boolean isValid(){
//        return !StringUtils.isEmpty(description);
//
//    }
//
//
//    public String getValidationError() {
//        if (StringUtils.isEmpty(description))
//            return "Descrizione nulla";
//        return "";
//    }
}
