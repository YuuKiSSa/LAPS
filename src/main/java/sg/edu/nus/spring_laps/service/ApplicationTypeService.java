package sg.edu.nus.spring_laps.service;

import sg.edu.nus.spring_laps.model.ApplicationType;

public interface ApplicationTypeService {
    public ApplicationType findByType(String type);
    public ApplicationType save(ApplicationType type);
    public ApplicationType findById(int id);
}
