package mx.edu.utez.JuventudxTemixco.models.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <BeanUser, Long> {
    boolean existsByCorreo(String correo);

    List<BeanUser> findByRol(UserType rol);

    @Query("SELECT u FROM BeanUser u WHERE u.nombre = :nombre")
    List<BeanUser> NombreUsuario(@Param("nombre") String nombre);


    @Query("SELECT u FROM BeanUser u WHERE u.fechaRegistro BETWEEN :fechaInicio AND :fechaFin")
    List<BeanUser> filtrarDinamico(
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin
    );
}
