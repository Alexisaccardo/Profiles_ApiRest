package com.example.demo;

public class Users {

    public String code;
    public String name;
    public String charge;
    public String nit;
    public String cellphone;

    public Users(String code, String name, String charge, String nit, String cellphone) {
        this.code = code;
        this.name = name;
        this.charge = charge;
        this.nit = nit;
        this.cellphone = cellphone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
}
