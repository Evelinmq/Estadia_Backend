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


    private Long id;

    private String nombre;

    private String imagen;

}
