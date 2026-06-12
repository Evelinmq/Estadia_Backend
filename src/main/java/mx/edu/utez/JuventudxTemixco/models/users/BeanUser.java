package mx.edu.utez.JuventudxTemixco.models.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.edu.utez.JuventudxTemixco.models.municipalities.BeanMunicipality;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table (name= "user")
public class BeanUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private UserType rol;

    private String nombre;
    private String apellidoP;
    private String apellidoM;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero")
    private Gender genero;

    private Integer edad;

    @Column(length = 10)
    private String telefono;

    private String correo;

    private String contrasena;


    //fecha de registro de los usuarios
    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDate fechaRegistro;

    @PrePersist
    protected void onCreate() {
        this.fechaRegistro = LocalDate.now();
    }

    @ManyToOne
    @JoinColumn(name = "id_municipio")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private BeanMunicipality municipio;

    private String colonia;

    @Lob
    @Column(name = "foto", columnDefinition = "LONGBLOB")
    private byte[] foto;
}
