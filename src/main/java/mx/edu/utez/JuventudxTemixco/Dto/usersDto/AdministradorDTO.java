package mx.edu.utez.JuventudxTemixco.Dto.usersDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministradorDTO {

    private Long id;

    private String nombre;

    private String apellidoP;

    private String apellidoM;

    private String correo;

    private String contrasena;


}
