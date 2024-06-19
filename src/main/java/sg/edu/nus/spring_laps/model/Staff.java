package sg.edu.nus.spring_laps.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Staff {
    @Id
    @Size(min=3, max=20, 
    message = "userId must be 3-20 characters")
    @NotBlank(message = "UserId is required")
    @Column(length = 20)
    private String userId;
    
    @Size(min=6, 
    message = "password must be at least 6 characters")
    @NotBlank(message = "Password is required")
    @Column(length = 20)
    private String password;
    
    @NotBlank(message = "Name is required")
    @Column(length = 15)
    private String name;
    
    @Email(message = "Please provide a valid email address")
    @Column(length = 25)
    private String email;
    private boolean status;
    private int hierarchy;
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false, foreignKey = @ForeignKey(name = "department_id"))
    private Department department;
    @OneToMany(mappedBy = "staff", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Application> applications;

    public Staff() {}

    public int getHierarchy() {
        return this.hierarchy;
    }

    public void setHierarchy(int hierarchy) {
        this.hierarchy = hierarchy;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Application> getApplications() {
        return this.applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", hierarchy=" + hierarchy +
                ", department='" + department.getName() + '\'' +
                ", applications=" + applications +
                '}';
    }
}