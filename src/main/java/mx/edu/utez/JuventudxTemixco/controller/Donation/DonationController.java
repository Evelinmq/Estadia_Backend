package mx.edu.utez.JuventudxTemixco.controller.Donation;

import mx.edu.utez.JuventudxTemixco.models.donations.BeanDonation;
import mx.edu.utez.JuventudxTemixco.service.Donation.DonationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Donacion")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class DonationController {

    DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody BeanDonation donacion){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(donationService.save(donacion));
    }

    @GetMapping("/")
    public ResponseEntity<List<BeanDonation>> getAll() {
        return ResponseEntity.ok(donationService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {

        Optional<BeanDonation> donacion = donationService.findById(id);

        if (donacion == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(donacion);
    }
}
