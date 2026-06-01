package mx.edu.utez.JuventudxTemixco.models.Goals;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoalRepository extends JpaRepository<BeanGoal, Long> {

    Optional<BeanGoal> findByName (String name);
}
