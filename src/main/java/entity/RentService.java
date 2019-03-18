package entity;

import java.util.Objects;

public class RentService extends Entity {
    private int id;
    private String name;
    private String address;
    private Bicycle bicycle;
    private User user;


    public RentService(int id, String name, String address, Bicycle bicycle, User user) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.bicycle = bicycle;
        this.user = user;
    }

    public RentService() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Bicycle getBicycle() {
        return bicycle;
    }

    public void setBicycle(Bicycle bicycle) {
        this.bicycle = bicycle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RentService)) return false;
        RentService that = (RentService) o;
        return getId() == that.getId() &&
                getName().equals(that.getName()) &&
                getAddress().equals(that.getAddress()) &&
                getBicycle().equals(that.getBicycle()) &&
                getUser().equals(that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAddress(), getBicycle(), getUser());
    }

    @Override
    public String toString() {
        return "RentService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", bicycle=" + bicycle +
                ", user=" + user +
                '}';
    }
}
