package hellojpa;


import jakarta.persistence.Entity;

@Entity
public class Model extends Item{

    private String Model;

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }
}
