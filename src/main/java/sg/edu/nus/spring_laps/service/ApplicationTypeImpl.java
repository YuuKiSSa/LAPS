package sg.edu.nus.spring_laps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.nus.spring_laps.model.ApplicationType;
import sg.edu.nus.spring_laps.repository.ApplicationTypeRepository;

@Service
@Transactional
public class ApplicationTypeImpl implements ApplicationTypeService {
    @Autowired
    private ApplicationTypeRepository applicationTypeRepository;
    @Override
    public ApplicationType findByType(String type) {
        // TODO Auto-generated method stub
        return applicationTypeRepository.findApplicationTypeByName(type);
    }
    @Override
    public ApplicationType save(ApplicationType type) {
        // TODO Auto-generated method stub
        return applicationTypeRepository.save(type);
    }
    @Override
    public ApplicationType findById(int id) {
        // TODO Auto-generated method stub
        if (applicationTypeRepository.findById(id).isPresent()) {
            return applicationTypeRepository.findById(id).get();}
        else
        {return null;}
    }
}
