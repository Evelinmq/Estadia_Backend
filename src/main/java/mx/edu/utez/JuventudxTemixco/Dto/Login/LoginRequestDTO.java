package mx.edu.utez.JuventudxTemixco.Dto.Login;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    @NotBlank(message = "El correo es requerido")
    private String correo;
    @NotBlank(message = "La contraseña es requerida")
    private String password;
}
