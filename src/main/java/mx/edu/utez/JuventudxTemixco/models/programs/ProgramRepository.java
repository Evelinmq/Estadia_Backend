package mx.edu.utez.JuventudxTemixco.models.programs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<BeanProgram, Long> {

    List<BeanProgram> findBySectionId(Long sectionId);

    Long id(Long id);

    List<BeanProgram> findBySectionName(String sectionName);
}
