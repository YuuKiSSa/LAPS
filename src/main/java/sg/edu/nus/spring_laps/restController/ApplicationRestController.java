package sg.edu.nus.spring_laps.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.edu.nus.spring_laps.dto.ApplicationDTO;
import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.repository.ApplicationRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/applications")
public class ApplicationRestController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @GetMapping
    public List<ApplicationDTO> getAllApplications() {
        return applicationRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationDTO> getApplicationById(@PathVariable Long id) {
        return applicationRepository.findById(id)
                .map(application -> ResponseEntity.ok(convertToDTO(application)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ApplicationDTO createApplication(@RequestBody Application application) {
        Application savedApplication = applicationRepository.save(application);
        return convertToDTO(savedApplication);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationDTO> updateApplication(@PathVariable Long id, @RequestBody Application applicationDetails) {
        return applicationRepository.findById(id)
                .map(application -> {
                    application.setStartTime(applicationDetails.getStartTime());
                    application.setEndTime(applicationDetails.getEndTime());
                    application.setStatus(applicationDetails.getStatus());
                    application.setDescription(applicationDetails.getDescription());
                    application.setStaff(applicationDetails.getStaff());
                    application.setApplicationType(applicationDetails.getApplicationType());
                    Application updatedApplication = applicationRepository.save(application);
                    return ResponseEntity.ok(convertToDTO(updatedApplication));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteApplication(@PathVariable Long id) {
        return applicationRepository.findById(id)
                .map(application -> {
                    applicationRepository.delete(application);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    private ApplicationDTO convertToDTO(Application application) {
        ApplicationDTO applicationDTO = new ApplicationDTO();
        applicationDTO.setId(application.getId());
        applicationDTO.setStartTime(application.getStartTime());
        applicationDTO.setEndTime(application.getEndTime());
        applicationDTO.setStatus(application.getStatus());
        applicationDTO.setDescription(application.getDescription());
        applicationDTO.setStaffName(application.getStaff().getName());
        applicationDTO.setApplicationTypeName(application.getApplicationType().getType());
        return applicationDTO;
    }
}
