package mx.edu.utez.JuventudxTemixco.service.Donation;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.JuventudxTemixco.models.donations.BeanDonation;
import mx.edu.utez.JuventudxTemixco.models.donations.DonationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;

    @Transactional
    public BeanDonation save(BeanDonation donation) {
        return donationRepository.save(donation);
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
