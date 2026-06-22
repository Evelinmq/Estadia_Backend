package mx.edu.utez.JuventudxTemixco.Dto.Donation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.edu.utez.JuventudxTemixco.models.users.Gender;

import java.time.LocalDateTime;

public class DonationDTO {

        private String nombre;
        private String apellidoP;
        private String apellidoM;
        private String correo;
        private Integer monto;

        @Enumerated(EnumType.STRING)
        @Column(name = "status")

        private LocalDateTime fecha;

        private String paypal_order_id;
        private String paypal_capture_id;

}
