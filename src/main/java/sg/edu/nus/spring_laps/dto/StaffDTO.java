package sg.edu.nus.spring_laps.dto;

public class StaffDTO {
    private String userId;
    private String name;
    private String email;
    private boolean status;
    private int hierarchy;
    private int entitle;
    private String departmentName;

    public StaffDTO() {
    }
    
    public StaffDTO(String userId, String name, String email, boolean status, int hierarchy, int entitle, String departmentName) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.status = status;
        this.hierarchy = hierarchy;
        this.entitle = entitle;
        this.departmentName = departmentName;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(int hierarchy) {
        this.hierarchy = hierarchy;
    }

    public int getEntitle() {
        return entitle;
    }

    public void setEntitle(int entitle) {
        this.entitle = entitle;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
