package mx.edu.utez.JuventudxTemixco.Config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomResponse {
    private boolean error;
    private int status;
    private String message;
}