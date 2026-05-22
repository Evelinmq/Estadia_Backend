package mx.edu.utez.JuventudxTemixco.models.Goals;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<BeanGoal, Long> {
}
