package sg.edu.nus.spring_laps.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Admin {
    @Id
    @Column(length = 20)
    private String userId;
    
  
    @Column(length = 20)
    private String password;

    public Admin() {}

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

    @Override
    public String toString() {
        return "Admin{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}