package mx.edu.utez.JuventudxTemixco.Dto.Donation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.edu.utez.JuventudxTemixco.models.donations.BeanDonation;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonationDTO {

        private String nombre;
        private String apellidoP;
        private String apellidoM;
        private String correo;
        private Integer monto;

        private String paypalOrderId;
        private String paypalCaptureId;

        public BeanDonation toEntity() {
                BeanDonation donation = new BeanDonation();

                donation.setNombre(nombre);
                donation.setApellidoP(apellidoP);
                donation.setApellidoM(apellidoM);
                donation.setCorreo(correo);
                donation.setMonto(monto);

                donation.setPaypal_order_id(paypalOrderId);
                donation.setPaypal_capture_id(paypalCaptureId);

                return donation;
        }
}
