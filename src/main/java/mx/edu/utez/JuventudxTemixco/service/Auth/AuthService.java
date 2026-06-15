package mx.edu.utez.JuventudxTemixco.service.Auth;

import mx.edu.utez.JuventudxTemixco.Dto.Login.LoginDTO;
import mx.edu.utez.JuventudxTemixco.Dto.Login.LoginResponseDTO;
import mx.edu.utez.JuventudxTemixco.Security.JwtService;
import mx.edu.utez.JuventudxTemixco.models.users.BeanUser;
import mx.edu.utez.JuventudxTemixco.models.users.UserRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    private final JwtService jwtService;


    private final Map<String, String> codigosTemporales = new ConcurrentHashMap<>();

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JavaMailSender mailSender, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
        this.jwtService = jwtService;
    }

    @Transactional(readOnly = true)
    public LoginResponseDTO login(LoginDTO loginDTO) {
        // 1. Buscar por correo
        BeanUser usuario = userRepository.findByCorreo(loginDTO.getCorreo());
        if (usuario == null) {
            throw new RuntimeException("Usuario o contraseña incorrectos"); // Se corrigió a 'throw'
        }

        // 2. Comparar contraseñas encriptadas
        if (!passwordEncoder.matches(loginDTO.getPassword(), usuario.getContrasena())) {
            throw new RuntimeException("Usuario o contraseña incorrectos");
        }

        // 3. Extraer el rol (se maneja el estándar con prefijo para el token)
        String nombreRol = "ADMIN";
        if (usuario.getRol() != null) {
            nombreRol = usuario.getRol().name().toUpperCase();
        }

        // Generamos el token usando el prefijo ROLE_ que pide Spring Security
        List<String> rolesList = Collections.singletonList("ROLE_" + nombreRol);
        String token = jwtService.generateAccessToken(usuario.getCorreo(), rolesList);

        // 4. Construir el objeto de respuesta completo para el Frontend
        LoginResponseDTO response = new LoginResponseDTO();
        response.setId(usuario.getId());
        response.setNombre(usuario.getNombre() + " " + usuario.getApellidoP() + " " + usuario.getApellidoM());
        response.setCorreo(usuario.getCorreo());
        response.setRol("ROLE_" + nombreRol); 
        response.setAccessToken(token);
        response.setTokenType("Bearer");

        return response;
    }

    @Transactional
    public void actualizarPassword(String correo, String nuevaPassword) {
        BeanUser usuario = userRepository.findByCorreo(correo);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        // Encriptamos la nueva contraseña usando BCrypt antes de guardar
        usuario.setContrasena(passwordEncoder.encode(nuevaPassword));
        userRepository.save(usuario);

        // Limpiar el código temporal para que no se pueda usar de nuevo
        codigosTemporales.remove(correo);
    }

    public void generarYEnviarCodigo(String correo) {
        // 1. Validar que el usuario realmente exista en el sistema
        BeanUser usuario = userRepository.findByCorreo(correo);
        if (usuario == null) {
            throw new RuntimeException("El correo ingresado no pertenece a ningún usuario registrado.");
        }

        // 2. Generar código de 5 dígitos
        String codigo = String.valueOf((int)(Math.random() * 90000) + 10000);
        codigosTemporales.put(correo, codigo);

        // 3. Enviar correo electrónico informativo
        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setFrom("mentorias.academicas.utez@gmail.com");
            mensaje.setTo(correo);
            mensaje.setSubject("Código de Verificación - Juventud x Temixco");
            mensaje.setText(
                    "Hola " + usuario.getNombre() + ",\n\n" +
                            "Recibimos una solicitud para restablecer la contraseña de tu cuenta de Juventud x Temixco.\n\n" +
                            "Tu código de verificación es:\n\n" +
                            codigo + "\n\n" +
                            "Este código es válido por tiempo limitado. Si no solicitaste este cambio, puedes ignorar este mensaje.\n\n" +
                            "Saludos,\n" +
                            "Equipo de Juventud x Temixco A.C."
            );

            mailSender.send(mensaje);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al enviar el correo de recuperación: " + e.getMessage());
        }
    }
}