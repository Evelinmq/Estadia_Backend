package mx.edu.utez.JuventudxTemixco.Dto.usersDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministradorDTO {

    @NotBlank (message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank (message = "El apellido paterno es obligatorio")
    private String apellidoP;

    @NotBlank (message = "El apellido materno es obligatorio")
    private String apellidoM;

    @NotBlank (message = "El correo es obligatorio")
    private String correo;

    @NotBlank (message = "La contraseña es obligatoria")
    private String contrasena;


}
