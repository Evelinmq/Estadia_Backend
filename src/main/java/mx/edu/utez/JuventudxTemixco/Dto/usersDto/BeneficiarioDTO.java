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
public class BeneficiarioDTO {

    @NotBlank (message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank (message = "El Apellido paterno es obligatorio")
    private String apellidoP;

    @NotBlank (message = "El Apellido Materno es obligatorio")
    private String apellidoM;

    @NotNull (message = "El genero es obligatorio")
    private Gender genero;

    @NotNull (message = "La edad es obligatoria")
    private Integer edad;

    @NotBlank (message = "El numero de telefono es obligatorio")
    @Size (min = 10, max = 10, message = "El numero solo debe tener 10 digitos ")
    private String telefono;

    @NotNull (message = "El Municipio es obligatorio")
    private Long id_Municipio;

    @NotBlank (message = "La colonia es obligatoria")
    private String colonia;

    @NotBlank (message = "El correo es obligatorio")
    private String correo;

    private byte[] foto;


}
