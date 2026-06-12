package mx.edu.utez.JuventudxTemixco.Security;

import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpHeaders;
import
        org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import
        org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * Lee el JWT del header Authorization, lo valida y deja al usuario autenticado en
 SecurityContextHolder.
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException

    {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String jwt = authHeader.substring(7).trim();
        if (jwt.isEmpty() || !jwtService.isTokenValid(jwt)) {
            filterChain.doFilter(request, response);
            return;
        }
        String username = jwtService.extractUsername(jwt);
        if (username == null || username.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        List<SimpleGrantedAuthority> authorities =

                jwtService.extractRoleAuthorities(jwt).stream()

                        .map(SimpleGrantedAuthority::new)
                        .toList();

        UsernamePasswordAuthenticationToken authToken = new

                UsernamePasswordAuthenticationToken(

                username, null, authorities);

        authToken.setDetails(new

                WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }
}
