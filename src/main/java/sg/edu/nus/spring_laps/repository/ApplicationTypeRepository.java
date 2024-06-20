package sg.edu.nus.spring_laps.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.model.ApplicationType;

public interface ApplicationTypeRepository extends JpaRepository<ApplicationType,Integer>{
	@Query("select t from ApplicationType t where t.type = : type")
	public ApplicationType findByType(String type);


		

}
