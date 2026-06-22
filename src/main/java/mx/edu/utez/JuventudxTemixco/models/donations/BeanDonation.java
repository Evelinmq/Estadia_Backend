package mx.edu.utez.JuventudxTemixco.models.donations;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    private Long id_donacion;

    @NotNull (message = "El nombre es obligatorio")
    private String nombre;
    @NotNull (message = "El apellido Paterno es obligatorio")
    private String apellidoP;
    @NotNull (message = "El apellido materno es obligatorio")
    private String apellidoM;
    @NotNull (message = "El correo es obligatorio")
    private String correo;
    @NotNull (message = "El monto es obligatorio")
    private Integer monto;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status estado;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fecha;

    @PrePersist
    protected void onCreate() {
        fecha = LocalDateTime.now();
    }
    private String paypal_order_id;
    private String paypal_capture_id;
}
