package mx.edu.utez.JuventudxTemixco.models.Allies;

import mx.edu.utez.JuventudxTemixco.models.users.BeanUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllyRepository extends JpaRepository<BeanAlly, Long> {

    @Query("SELECT a FROM BeanAlly a WHERE a.name LIKE %:nombre%")
    List<BeanAlly> nombreAlianza(@Param("nombre") String nombre);
}
