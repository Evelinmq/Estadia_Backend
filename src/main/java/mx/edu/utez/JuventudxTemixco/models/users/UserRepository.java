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

    @Query("SELECT u FROM BeanUser u WHERE (" +
            "LOWER(u.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) OR " +
            "LOWER(u.apellidoP) LIKE LOWER(CONCAT('%', :apellidoP, '%')) OR " +
            "LOWER(u.apellidoM) LIKE LOWER(CONCAT('%', :apellidoM, '%'))" +
            ") AND u.rol = :rol")
    List<BeanUser> BusquedaNombre(
            @Param("nombre") String nombre,
            @Param("apellidoP") String apellidoP,
            @Param("apellidoM") String apellidoM,
            @Param("rol") UserType rol
    );



    @Query("SELECT u FROM BeanUser u WHERE u.fechaRegistro BETWEEN :fechaInicio AND :fechaFin AND u.rol = :rol")
    List<BeanUser> filtrarDinamico(
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin,
            @Param("rol") UserType rol
    );
}
