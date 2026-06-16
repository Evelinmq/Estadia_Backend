package mx.edu.utez.JuventudxTemixco.Dto.NewPassword;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetDTO {

    private String correo;
    private String codigo;
    private String contrasena;
}
