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


    private String nombre;


    private String apellidoP;


    private String apellidoM;


    private Gender genero;


    private Integer edad;


    private String telefono;

    private Long id_Municipio;


    private String colonia;


    private String correo;

    private String foto;


}
