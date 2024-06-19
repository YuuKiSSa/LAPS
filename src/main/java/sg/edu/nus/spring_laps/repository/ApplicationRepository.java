package sg.edu.nus.spring_laps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.nus.spring_laps.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long>{
	@Query("select a from Application a where a.staff.userId= :query")
	public List<Application> findApplicationByUserId(String query);
	
	@Query("select a from Application a where a.staff.name= :query")
	public List<Application> findApplicationByName(String query);
}
