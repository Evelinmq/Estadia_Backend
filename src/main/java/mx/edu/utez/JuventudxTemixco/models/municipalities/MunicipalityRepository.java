package mx.edu.utez.JuventudxTemixco.models.municipalities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  MunicipalityRepository extends JpaRepository<BeanMunicipality, Long> {
}
