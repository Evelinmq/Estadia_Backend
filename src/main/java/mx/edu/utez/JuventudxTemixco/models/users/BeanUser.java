package mx.edu.utez.JuventudxTemixco.models.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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

    @NotBlank
    @Size(min = 2, max = 64)
    private String nombre;
    @NotBlank
    @Size(min = 2, max = 21)
    private String apellidoP;
    @NotBlank
    @Size(min = 2, max = 21)
    private String apellidoM;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero")
    private Gender genero;

    @Min(value = 2)
    @Max(value = 120)
    private Integer edad;

    @Column(length = 10)
    private String telefono;

    @Email
    private String correo;

    @Column(name = "contrasena", length = 255)
    private String contrasena;


    //fecha de registro de los usuarios
    @PastOrPresent
    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDate fechaRegistro;

    @PrePersist
    protected void onCreate() {
        this.fechaRegistro = LocalDate.now();
    }

    @Valid
    @ManyToOne
    @JoinColumn(name = "id_municipio")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private BeanMunicipality municipio;

    private String colonia;

    @Lob
    @Column(name = "foto", columnDefinition = "LONGBLOB")
    private byte[] foto;
}
