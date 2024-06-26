package sg.edu.nus.spring_laps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sg.edu.nus.spring_laps.model.ApplicationType;

public interface ApplicationTypeRepository extends JpaRepository<ApplicationType, Integer> {
    @Query("select t from ApplicationType t where t.type= :applicationTypeName")
    ApplicationType findApplicationTypeByName(@Param("applicationTypeName") String applicationTypeName);
}
