package sg.edu.nus.spring_laps.model;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import sg.edu.nus.spring_laps.validation.ValidAnnualLeave;
import sg.edu.nus.spring_laps.validation.ValidMedicalLeaveDays;
import sg.edu.nus.spring_laps.validation.ValidRangeDate;
import sg.edu.nus.spring_laps.validation.ValidRepeatedDays;

import java.time.Duration;
import java.time.LocalDateTime;

@ValidAnnualLeave
@ValidRangeDate
@ValidMedicalLeaveDays
@ValidRepeatedDays
public class ApplicationForm {
    private Long applicationId;
    private String userId;
    private String applicationType;
    @NotNull(message = "Start time is required!")
    private LocalDateTime startTime;
    @NotNull(message = "End time is required!")
    private LocalDateTime endTime;
    private String selectTime;
    private String reason;

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public @NotNull(message = "Start time is required!") LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(@NotNull(message = "Start time is required!") LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public @NotNull(message = "End time is required!") LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(@NotNull(message = "End time is required!") LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getSelectTime() {
        return selectTime;
    }

    public void setSelectTime(String selectTime) {
        this.selectTime = selectTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
