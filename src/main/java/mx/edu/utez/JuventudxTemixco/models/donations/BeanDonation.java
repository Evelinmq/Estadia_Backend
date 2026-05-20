package mx.edu.utez.JuventudxTemixco.models.donations;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.edu.utez.JuventudxTemixco.models.users.Gender;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name= "donation")
public class BeanDonation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String correo;
    private Integer monto;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Gender estado;

    @Column(name = "stripe_session_id")
    private String stripeSessionId;

    private LocalDateTime fecha;

}
