package sg.edu.nus.spring_laps.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique=true, length = 20)
    private String name;
    private int annualLeave;
    @OneToMany(mappedBy = "department")
    private List<Staff> staffs;

    public Department() {}

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

    public int getAnnualLeave() {
        return annualLeave;
    }

    public void setAnnualLeave(int annualLeave) {
        this.annualLeave = annualLeave;
    }

    public List<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Staff> staffs) {
        this.staffs = staffs;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", annualLeave=" + annualLeave +
                ", staffs=" + staffs +
                '}';
    }
}
