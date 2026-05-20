package mx.edu.utez.JuventudxTemixco.models.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(columnDefinition = "LONGTEXT")
    private String foto;
}
