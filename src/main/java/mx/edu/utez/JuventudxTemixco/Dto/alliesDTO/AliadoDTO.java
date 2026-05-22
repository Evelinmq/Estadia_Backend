package mx.edu.utez.JuventudxTemixco.Dto.alliesDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AliadoDTO {

    @NotBlank(message = "El nombre es onligatorio")
    private String nombre;


    @NotBlank (message = "La imagen es obligatoria")
    private MultipartFile imagen;

}
