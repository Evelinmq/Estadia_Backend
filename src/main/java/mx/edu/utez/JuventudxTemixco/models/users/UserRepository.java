package mx.edu.utez.JuventudxTemixco.models.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <BeanUser, Long> {
}
