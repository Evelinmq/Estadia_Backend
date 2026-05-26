package mx.edu.utez.JuventudxTemixco.models.Sections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<BeanSection, Long> {
    Long id(Long id);

    List<BeanSection> findByNameContainingIgnoreCase(String name);
}
