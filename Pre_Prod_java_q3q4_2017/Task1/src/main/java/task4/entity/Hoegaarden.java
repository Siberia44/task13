package task4.entity;

import task4.annotation.Setter;

import java.util.Objects;

public class Hoegaarden extends LightBeer {
    private String typeOfBottle;
    private String taste;

    public Hoegaarden() {
    }

    public Hoegaarden(int id, int cost, String name, int alcoholPercentage, String country, String rawMaterials, String manufacturer, String typeOfBottle, String taste) {
        super(id, cost, name, alcoholPercentage, country, rawMaterials, manufacturer);
        this.typeOfBottle = typeOfBottle;
        this.taste = taste;
    }

    public String getTypeOfBottle() {
        return typeOfBottle;
    }

    @Setter(name = "SET_TYPE_OF_BOTTLE")
    public void setTypeOfBottle(String typeOfBottle) {
        this.typeOfBottle = typeOfBottle;
    }

    public String getTaste() {
        return taste;
    }

    @Setter(name = "SET_TASTE")
    public void setTaste(String taste) {
        this.taste = taste;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Hoegaarden that = (Hoegaarden) o;
        return typeOfBottle.equals(that.typeOfBottle) &&
                taste.equals(that.taste);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), typeOfBottle, taste);
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + super.getId() +
                ", cost=" + super.getCost() +
                ", name='" + super.getName() + '\'' +
                ", alcoholPercentage=" + super.getAlcoholPercentage() +
                ", country='" + super.getCountry() + '\'' +
                " rawMaterials='" + super.getRawMaterials() + '\'' +
                ", manufacturer='" + super.getManufacturer() + '\'' +
                " typeOfBottle='" + typeOfBottle + '\'' +
                ", taste='" + taste + '\'' +
                '}';
    }
}
