package mx.edu.utez.JuventudxTemixco.service.Auth;

import mx.edu.utez.JuventudxTemixco.Dto.Login.LoginDTO;
import mx.edu.utez.JuventudxTemixco.Dto.Login.LoginResponseDTO;
import mx.edu.utez.JuventudxTemixco.models.users.BeanUser;
import mx.edu.utez.JuventudxTemixco.models.users.UserRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public LoginResponseDTO login(LoginDTO loginDTO) {
        // 3. Buscar por correo usando tu BeanUsuario
        BeanUser usuario = userRepository.findByCorreo(loginDTO.getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 4. Comparar contraseñas encriptadas
        if (!passwordEncoder.matches(loginDTO.getPassword(), usuario.getContrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        //se verifica el estado
        if (usuario.getEstado() == null || usuario.getEstado().getId() != 1L) {
            if (usuario.getEstado() != null && usuario.getEstado().getId() == 3L) {
                throw new RuntimeException("Tu cuenta está pendiente de aprobación por un administrador.");
            }
            throw new RuntimeException("Tu cuenta no está activa.");
        }

        // 5. Extraer el rol
        String nombreRol = "aprendiz"; // valor por defecto
        if (usuario.getRoles() != null && !usuario.getRoles().isEmpty()) {
            nombreRol = usuario.getRoles().stream()
                    .map(rol -> rol.getNombre().toLowerCase())
                    .collect(Collectors.joining(","));
        }

        String token = jwtService.generateToken(usuario);

        // 6. Construir el DTO que pide el constructor (Long, String, String, String)
        return new LoginResponseDTO(
                usuario.getId(),
                usuario.getNombre() + " " + usuario.getApellidoP() + " " + usuario.getApellidoM(),
                usuario.getCorreo(),
                nombreRol.toLowerCase(),
                token
        );
    }

    public boolean verificarCodigo(String correo, String codigo) {
        String codigoEnMemoria = codigosTemporales.get(correo);
        System.out.println("DEBUG: Correo buscado: " + correo);
        System.out.println("DEBUG: Código en memoria: " + codigoEnMemoria);
        System.out.println("DEBUG: Código recibido del front: " + codigo);

        return codigo != null && codigo.equals(codigoEnMemoria);
    }

    @Transactional
    public void actualizarPassword(String correo, String nuevaPassword) {
        // Buscamos al usuario por el correo que traemos desde el flujo
        BeanUser usuario = userRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Encriptamos la nueva contraseña
        usuario.setContrasena(passwordEncoder.encode(nuevaPassword));

        // Guardamos los cambios
        userRepository.save(usuario);

        // Limpiar el código temporal para que no se pueda usar de nuevo
        codigosTemporales.remove(correo);
    }

    public void generarYEnviarCodigo(String correo) {
        // 1. Validar usuario
        usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Generar código
        String codigo = String.valueOf((int)(Math.random() * 90000) + 10000);
        codigosTemporales.put(correo, codigo);

        // 3. ENVIAR CORREO REAL
        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setFrom("mentorias.academicas.utez@gmail.com");
            mensaje.setTo(correo);
            mensaje.setSubject("Código de Verificación - Juventud x Temixco");
            mensaje.setText(
                    "Hola,\n\n" +
                            "Recibimos una solicitud para restablecer la contraseña de tu cuenta de Juventud x Temixco.\n\n" +
                            "Tu código de verificación es:\n\n" +
                            codigo + "\n\n" +
                            "Este código es válido por tiempo limitado. Si no solicitaste este cambio, puedes ignorar este mensaje.\n\n" +
                            "Saludos,\n" +
                            "Equipo de Juventud x Temixco A.C."
            );

            mailSender.send(mensaje);
        } catch (Exception e) {
            // Si falla, imprime el error completo en la consola para ver el motivo exacto
            e.printStackTrace();
            throw new RuntimeException("Error al enviar: " + e.getMessage());
        }
    }
}
