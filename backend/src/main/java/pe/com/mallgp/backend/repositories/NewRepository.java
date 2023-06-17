package pe.com.mallgp.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.mallgp.backend.entities.Mall;
import pe.com.mallgp.backend.entities.New;

import java.util.Date;
import java.util.List;

public interface NewRepository extends JpaRepository <New, Long>  {

    List<New> findByMall(Mall mall);

}
