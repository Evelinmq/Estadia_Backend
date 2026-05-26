package mx.edu.utez.JuventudxTemixco.Dto.usersDto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.edu.utez.JuventudxTemixco.models.users.Gender;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AfiliadoDTO {

    private String nombre;

    private String apellidoP;

    private String apellidoM;

    private String foto;

    private Gender genero;

    private Integer edad;

    private String telefono;


}
