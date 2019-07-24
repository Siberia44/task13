package task4.entity;

import task4.annotation.Setter;

import java.util.Objects;

public class LightBeer extends Beer {
    private String rawMaterials;
    private String manufacturer;

    public LightBeer() {
    }

    public LightBeer(int id, int cost, String name, int alcoholPercentage, String country, String rawMaterials, String manufacturer) {
        super(id, cost, name, alcoholPercentage, country);
        this.rawMaterials = rawMaterials;
        this.manufacturer = manufacturer;
    }

    public String getRawMaterials() {
        return rawMaterials;
    }

    @Setter(name = "SET_RAW_MATERIALS")
    public void setRawMaterials(String rawMaterials) {
        this.rawMaterials = rawMaterials;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    @Setter(name = "SET_MANUFACTURER")
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LightBeer lightBeer = (LightBeer) o;
        return rawMaterials.equals(lightBeer.rawMaterials) &&
                manufacturer.equals(lightBeer.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rawMaterials, manufacturer);
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + super.getId() +
                ", cost=" + super.getCost() +
                ", name='" + super.getName() + '\'' +
                ", alcoholPercentage=" + super.getAlcoholPercentage() +
                ", country='" + super.getCountry() + '\'' +
                " rawMaterials='" + rawMaterials + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}
