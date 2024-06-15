package sg.edu.nus.spring_laps.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20,name="type")
    private String applicationtype;

    private Date startTime;
    private Date endTime;

    @Column(length = 10)
    private String status;

    @ManyToOne(fetch = FetchType.EAGER) // 设置为急加载
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "user_id"))
    private Staff staff;
    
    @Column(length = 1000)
    private String description;

    public Application() {}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicationtype() {
        return this.applicationtype;
    }

    public void setApplicationtype(String applicationtype) {
        this.applicationtype = applicationtype;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Staff getStaff() {
        return this.staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", type='" + applicationtype + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status='" + status + '\'' +
                ", staff=" + staff +
                ", description='" + description + '\'' +
                '}';
    }
}
