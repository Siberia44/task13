package task4.entity;


import task4.annotation.Setter;

import java.util.Objects;

public class DarkBeer extends Beer {
    private int percentageRoastedHop;
    private String type;

    public DarkBeer() {
    }

    public DarkBeer(int id, int cost, String name, int alcoholPercentage, String country, int percentageRoastedHop, String type) {
        super(id, cost, name, alcoholPercentage, country);
        this.percentageRoastedHop = percentageRoastedHop;
        this.type = type;
    }

    public int getPercentageRoastedHop() {
        return percentageRoastedHop;
    }

    @Setter(name = "SET_PERCENTAGE_ROASTED_HOP")
    public void setPercentageRoastedHop(int percentageRoastedHop) {
        this.percentageRoastedHop = percentageRoastedHop;
    }

    public String getType() {
        return type;
    }

    @Setter(name = "SET_TYPE")
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DarkBeer darkBeer = (DarkBeer) o;
        return percentageRoastedHop == darkBeer.percentageRoastedHop &&
                type.equals(darkBeer.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), percentageRoastedHop, type);
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + super.getId() +
                ", cost=" + super.getCost() +
                ", name='" + super.getName() + '\'' +
                ", alcoholPercentage=" + super.getAlcoholPercentage() +
                ", country='" + super.getCountry() + '\'' +
                " percentageRoastedHop='" + percentageRoastedHop + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
