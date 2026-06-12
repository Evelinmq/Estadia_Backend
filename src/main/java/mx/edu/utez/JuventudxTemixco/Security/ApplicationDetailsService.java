package mx.edu.utez.JuventudxTemixco.Security;

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
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {

        BeanUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
        if (user.getRole() == null) {
            throw new UsernameNotFoundException("Usuario sin rol asignado: " + username);
        }
        return User.builder()

                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .disabled(!user.isEnabled())
                .build();

    }
}
