package mx.edu.utez.JuventudxTemixco.models.donations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends JpaRepository <BeanDonation, Long> {
}
