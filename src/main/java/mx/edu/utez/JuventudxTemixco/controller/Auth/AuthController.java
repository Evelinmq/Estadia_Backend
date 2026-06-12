package mx.edu.utez.JuventudxTemixco.controller.Auth;

import jakarta.validation.Valid;
import mx.edu.utez.JuventudxTemixco.Dto.Login.LoginRequestDTO;
import mx.edu.utez.JuventudxTemixco.Dto.Login.LoginResponseDTO;
import mx.edu.utez.JuventudxTemixco.Security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin({"*"})
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService) {

        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody
                                                  LoginRequestDTO body) {

        Authentication authentication = authenticationManager.authenticate(

                new

                        UsernamePasswordAuthenticationToken(body.getCorreo(), body.getPassword()));
        List<String> authorities = authentication.getAuthorities().stream()

                .map(GrantedAuthority::getAuthority)
                .toList();
        String token =

                jwtService.generateAccessToken(authentication.getName(), authorities);

        return new ResponseEntity<>(new LoginResponseDTO(token, "Bearer"),

                HttpStatus.OK);
    }
}
