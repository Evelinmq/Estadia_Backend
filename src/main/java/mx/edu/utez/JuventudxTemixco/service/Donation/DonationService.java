package mx.edu.utez.JuventudxTemixco.service.Donation;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.JuventudxTemixco.Dto.Donation.DonationDTO;
import mx.edu.utez.JuventudxTemixco.models.donations.BeanDonation;
import mx.edu.utez.JuventudxTemixco.models.donations.DonationRepository;
import mx.edu.utez.JuventudxTemixco.models.donations.Status;
import mx.edu.utez.JuventudxTemixco.models.users.Gender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;

    @Transactional
    public BeanDonation save (BeanDonation donation) {

        return donationRepository.save(donation);
    }

    @Transactional
    public BeanDonation registrarDonacion(BeanDonation donacion) {

        donacion.setEstado(Status.PENDIENTE);

        return donationRepository.save(donacion);
    }

    @Transactional
    public BeanDonation confirmarPago(
            Long idDonacion,
            String orderId,
            String captureId
    ) {

        BeanDonation donacion = donationRepository.findById(idDonacion)
                .orElseThrow();

        donacion.setPaypal_order_id(orderId);
        donacion.setPaypal_capture_id(captureId);
        donacion.setEstado(Status.COMPLETADA);

        return donationRepository.save(donacion);
    }

    @Transactional(readOnly = true)
    public List<BeanDonation> list() {
        return donationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<BeanDonation> findById(Long id) {
        return donationRepository.findById(id);
    }
}
