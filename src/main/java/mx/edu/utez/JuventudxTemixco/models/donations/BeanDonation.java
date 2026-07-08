package mx.edu.utez.JuventudxTemixco.models.donations;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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

    @NotBlank (message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank (message = "El apellido Paterno es obligatorio")
    private String apellidoP;
    @NotBlank (message = "El apellido materno es obligatorio")
    private String apellidoM;
    @NotBlank(message = "El correo es obligatorio")
    private String correo;
    @NotNull (message = "El monto es obligatorio")
    private Integer monto;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status estado;

    @Column(nullable = false, updatable = false)
    @PastOrPresent
    private LocalDateTime fecha;

    @PrePersist
    protected void onCreate() {
        fecha = LocalDateTime.now();
    }
    private String paypal_order_id;
    private String paypal_capture_id;
}
