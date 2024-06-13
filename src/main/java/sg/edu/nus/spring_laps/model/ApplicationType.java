package sg.edu.nus.spring_laps.model;

import jakarta.persistence.*;

@Entity
public class ApplicationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 20)
    private String type;

    public ApplicationType() {}

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ApplicationType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}

