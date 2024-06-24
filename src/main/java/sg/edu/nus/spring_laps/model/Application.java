package sg.edu.nus.spring_laps.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(length = 10, nullable = false)
    private String status;

    @Column(length = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_application_staff"))
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false, foreignKey = @ForeignKey(name = "fk_application_type"))
    private ApplicationType applicationType;

    public Application() {}

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public ApplicationType getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(ApplicationType applicationType) {
        this.applicationType = applicationType;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", staff=" + (staff != null ? staff.getName() : "null") + // Add null check
                ", applicationType=" + (applicationType != null ? applicationType.getType() : "null") + // Add null check
                "}";
    }
}
