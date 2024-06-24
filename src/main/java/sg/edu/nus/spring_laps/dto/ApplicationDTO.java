package sg.edu.nus.spring_laps.dto;

import java.time.LocalDateTime;

public class ApplicationDTO {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private String description;
    private String staffName;
    private String applicationTypeName;

    // Getters and setters

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

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getApplicationTypeName() {
        return applicationTypeName;
    }

    public void setApplicationTypeName(String applicationTypeName) {
        this.applicationTypeName = applicationTypeName;
    }
}
