package entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Bicycle  extends  Entity{
    private int id;
    private String model;
    private String producer;
    private BigDecimal rentPrice;
    private  String description;
    private boolean booked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public BigDecimal getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(BigDecimal rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bicycle bicycle = (Bicycle) o;
        return id == bicycle.id &&
                rentPrice == bicycle.rentPrice &&
                Objects.equals(model, bicycle.model) &&
                Objects.equals(producer, bicycle.producer) &&
                Objects.equals(description, bicycle.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, model, producer, rentPrice, description);
    }

    @Override
    public String toString() {
        return "Bicycle{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", producer='" + producer + '\'' +
                ", rentPrice=" + rentPrice +
                ", description='" + description + '\'' +
                '}';
    }
}
