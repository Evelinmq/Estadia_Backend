package mx.edu.utez.JuventudxTemixco.models.Allies;

import mx.edu.utez.JuventudxTemixco.models.users.BeanUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllyRepository extends JpaRepository<BeanAlly, Long> {

    List<BeanAlly> nombreAlianza(String nombre);
}
