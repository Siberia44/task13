package task4.entity;

import task4.annotation.Setter;

import java.io.Serializable;
import java.util.Objects;

public class Beer implements Serializable {
    private int id;
    private int cost;
    private String name;
    private int alcoholPercentage;
    private String country;

    public Beer() {
    }

    public Beer(int id, int cost, String name, int alcoholPercentage, String country) {
        this.id = id;
        this.cost = cost;
        this.name = name;
        this.alcoholPercentage = alcoholPercentage;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCost() {
        return cost;
    }

    @Setter(name = "SET_COST")
    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    @Setter(name = "SET_NAME")
    public void setName(String name) {
        this.name = name;
    }

    public int getAlcoholPercentage() {
        return alcoholPercentage;
    }

    @Setter(name = "SET_ALC_PERCENT")
    public void setAlcoholPercentage(int alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
    }

    public String getCountry() {
        return country;
    }

    @Setter(name = "SET_COUNTRY")
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beer beer = (Beer) o;
        return id == beer.id &&
                cost == beer.cost &&
                alcoholPercentage == beer.alcoholPercentage &&
                Objects.equals(name, beer.name) &&
                Objects.equals(country, beer.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cost, name, alcoholPercentage, country);
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", cost=" + cost +
                ", name='" + name + '\'' +
                ", alcoholPercentage=" + alcoholPercentage +
                ", country='" + country + '\'' +
                '}';
    }
}

