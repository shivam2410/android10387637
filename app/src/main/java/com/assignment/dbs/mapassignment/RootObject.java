package com.assignment.dbs.mapassignment;

public class RootObject {
    private int number;
    private String name;
    private String address;
    private boolean banking;
    private boolean bonus;
    private String status;
    private String contract_name;
    private int bike_stands;
    private int available_bike_stands;
    private int available_bikes;
    private int last_update;






    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public boolean getBanking() {
        return this.banking;
    }

    public void setBanking(boolean banking) {
        this.banking = banking;
    }

    public boolean getBonus() {
        return this.bonus;
    }

    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }


    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getContractName() {
        return this.contract_name;
    }

    public void setContractName(String contract_name) {
        this.contract_name = contract_name;
    }


    public int getBikeStands() {
        return this.bike_stands;
    }

    public void setBikeStands(int bike_stands) {
        this.bike_stands = bike_stands;
    }


    public int getAvailableBikeStands() {
        return this.available_bike_stands;
    }

    public void setAvailableBikeStands(int available_bike_stands) {
        this.available_bike_stands = available_bike_stands;
    }


    public int getAvailableBikes() {
        return this.available_bikes;
    }

    public void setAvailableBikes(int available_bikes) {
        this.available_bikes = available_bikes;
    }

    public int getLastUpdate() {
        return this.last_update;
    }

    public void setLastUpdate(int last_update) {
        this.last_update = last_update;
    }
}

