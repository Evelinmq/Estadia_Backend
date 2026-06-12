package mx.edu.utez.JuventudxTemixco.service.Auth;

import mx.edu.utez.JuventudxTemixco.models.users.BeanUser;
import mx.edu.utez.JuventudxTemixco.models.users.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Carga BeanUser por nombre de usuario y lo adapta a UserDetails para Spring
 Security.
 */
@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public ApplicationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String correo) throws
            UsernameNotFoundException {

        BeanUser user = userRepository.findByCorreo(correo);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + correo);
        }
        if (user.getRol() == null) {
            throw new UsernameNotFoundException("Usuario sin rol asignado: " + correo);
        }
        return User.builder()

                .username(user.getCorreo())
                .password(user.getContrasena())
                .roles(user.getRol().name())
                .build();

    }
}