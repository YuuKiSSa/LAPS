package sg.edu.nus.spring_laps.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.nus.spring_laps.model.Admin;

public interface AdministratorRepository extends JpaRepository<Admin, String>{

}
