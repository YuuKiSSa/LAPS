package sg.edu.nus.spring_laps.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.edu.nus.spring_laps.dto.StaffDTO;
import sg.edu.nus.spring_laps.model.Staff;
import sg.edu.nus.spring_laps.repository.StaffRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/staffs")
public class StaffRestController {

    @Autowired
    private StaffRepository staffRepository;

    @GetMapping
    public List<StaffDTO> getAllStaff() {
        return staffRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<StaffDTO> getStaffByUserId(@PathVariable String userId) {
        return staffRepository.findById(userId)
                .map(staff -> ResponseEntity.ok(convertToDTO(staff)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public StaffDTO createStaff(@RequestBody Staff staff) {
        Staff savedStaff = staffRepository.save(staff);
        return convertToDTO(savedStaff);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<StaffDTO> updateStaff(@PathVariable String userId, @RequestBody Staff staffDetails) {
        return staffRepository.findById(userId)
                .map(staff -> {
                    staff.setPassword(staffDetails.getPassword());
                    staff.setName(staffDetails.getName());
                    staff.setEmail(staffDetails.getEmail());
                    staff.setStatus(staffDetails.getStatus());
                    staff.setHierarchy(staffDetails.getHierarchy());
                    staff.setEntitle(staffDetails.getEntitle());
                    staff.setDepartment(staffDetails.getDepartment());
                    Staff updatedStaff = staffRepository.save(staff);
                    return ResponseEntity.ok(convertToDTO(updatedStaff));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteStaff(@PathVariable String userId) {
        return staffRepository.findById(userId)
                .map(staff -> {
                    staffRepository.delete(staff);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    private StaffDTO convertToDTO(Staff staff) {
        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setUserId(staff.getUserId());
        staffDTO.setName(staff.getName());
        staffDTO.setEmail(staff.getEmail());
        staffDTO.setStatus(staff.getStatus());
        staffDTO.setHierarchy(staff.getHierarchy());
        staffDTO.setEntitle(staff.getEntitle());
        staffDTO.setDepartmentName(staff.getDepartment().getName());
        return staffDTO;
    }
}
