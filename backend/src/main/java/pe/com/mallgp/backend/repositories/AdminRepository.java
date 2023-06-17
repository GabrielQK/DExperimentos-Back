package pe.com.mallgp.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.mallgp.backend.entities.Admin;

import java.util.List;

public interface AdminRepository extends JpaRepository <Admin, Long> {
    List<Admin> findByName(String name);
}
