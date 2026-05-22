package mx.edu.utez.JuventudxTemixco.models.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <BeanUser, Long> {
    boolean existsByCorreo(String correo);
    List<BeanUser> NombreUsuario(String nombre);

    List<BeanUser> findByFechaBetween(LocalDate inicio, LocalDate fin);

    List<BeanUser> filtrarDinamico(
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin);

}
