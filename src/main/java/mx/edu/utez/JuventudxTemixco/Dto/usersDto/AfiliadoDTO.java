package mx.edu.utez.JuventudxTemixco.Dto.usersDto;


import jakarta.validation.constraints.NotBlank;
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

    @NotBlank (message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank (message = "El apellido paterno es obligatorio")
    private String apellidoP;

    @NotBlank (message = "El apellido materno es obligatorio")
    private String apellidoM;

    @NotBlank (message = "La foto es obligatoria")
    private MultipartFile foto;

    @NotBlank (message = "El genero es obligatorio")
    private Gender genero;

    @NotBlank (message = "La edad es obligatorio")
    private Integer edad;

    @NotBlank (message = "El telefono es obligatorio")
    @Size (min = 10, max = 10, message = "Solo se pueden 10 digitos")
    private String telefono;


}
