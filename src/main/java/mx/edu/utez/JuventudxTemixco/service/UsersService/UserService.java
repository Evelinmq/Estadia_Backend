package mx.edu.utez.JuventudxTemixco.service.UsersService;

import jakarta.validation.constraints.Size;
import mx.edu.utez.JuventudxTemixco.Dto.usersDto.AfiliadoDTO;
import mx.edu.utez.JuventudxTemixco.Dto.usersDto.BeneficiarioDTO;
import mx.edu.utez.JuventudxTemixco.models.municipalities.BeanMunicipality;
import mx.edu.utez.JuventudxTemixco.models.municipalities.MunicipalityRepository;
import mx.edu.utez.JuventudxTemixco.models.users.BeanUser;
import mx.edu.utez.JuventudxTemixco.models.users.UserRepository;
import mx.edu.utez.JuventudxTemixco.models.users.UserType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@Service
public class UserService {

    private MunicipalityRepository municipalityRepository;

    private UserRepository userRepository;

    public UserService(MunicipalityRepository municipalityRepository, UserRepository userRepository) {
        this.municipalityRepository = municipalityRepository;
        this.userRepository = userRepository;
    }

    // Beneficiario
    public BeanUser createUserBeneficiario(BeneficiarioDTO datos){

        BeanUser user = new BeanUser();
        BeanMunicipality municipio = municipalityRepository.getReferenceById(datos.getId_Municipio());

        if(userRepository.existsByCorreo(datos.getCorreo())){
            throw new IllegalArgumentException("El correo ya se encuentra registrado, intenta con otro correo");
        }

        user.setNombre(datos.getNombre());
        user.setApellidoP(datos.getApellidoP());
        user.setApellidoM(datos.getApellidoM());
        user.setGenero(datos.getGenero());
        user.setEdad(datos.getEdad());
        user.setTelefono(datos.getTelefono());
        user.setMunicipio(municipio);
        user.setColonia(datos.getColonia());
        user.setCorreo(datos.getCorreo());
        user.setRol(UserType.BENEFICIARIO);
        if (datos.getFoto() != null) {
            user.setFoto(datos.getFoto());
        }


        return userRepository.save(user);

    }

    public BeanUser editarBeneficiario(BeneficiarioDTO datos, Long id) {

        BeanUser existente = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        BeanMunicipality municipio = municipalityRepository.getReferenceById(datos.getId_Municipio());


        existente.setNombre(datos.getNombre());
        existente.setApellidoP(datos.getApellidoP());
        existente.setApellidoM(datos.getApellidoM());
        existente.setGenero(datos.getGenero());
        existente.setMunicipio(municipio);
        existente.setColonia(datos.getColonia());
        existente.setCorreo(datos.getCorreo());
        existente.setTelefono(datos.getTelefono());
        if (existente.getFoto() != null) {
            existente.setFoto(datos.getFoto());
        }


        return userRepository.save(existente);


    }

    public List<BeneficiarioDTO> listarBeneficiarios() {
        List<BeanUser> beneficiariosEntidad = userRepository.findByRol(UserType.BENEFICIARIO);

        return beneficiariosEntidad.stream()
                .map(user -> {
                    BeneficiarioDTO dto = new BeneficiarioDTO();

                    dto.setNombre(user.getNombre());
                    dto.setApellidoP(user.getApellidoP());
                    dto.setApellidoM(user.getApellidoM());
                    dto.setGenero(user.getGenero());
                    dto.setEdad(user.getEdad());
                    dto.setTelefono(user.getTelefono());
                    dto.setId_Municipio(user.getMunicipio().getId());
                    dto.setColonia(user.getColonia());
                    dto.setCorreo(user.getCorreo());
                    if (user.getFoto() != null) {
                        dto.setFoto(user.getFoto());
                    }


                    return dto;
                })
                .toList();
    }



    // Afiliado
    public BeanUser createUserAfiliado(AfiliadoDTO datos){

        BeanUser user = new BeanUser();

        user.setNombre(datos.getNombre());
        user.setApellidoP(datos.getApellidoP());
        user.setApellidoM(datos.getApellidoM());
        user.setGenero(datos.getGenero());
        user.setEdad(datos.getEdad());
        user.setTelefono(datos.getTelefono());
        user.setRol(UserType.AFILIADO);
        if (datos.getFoto() != null) {
            user.setFoto(datos.getFoto());
        }

        return userRepository.save(user);

    }





    public BeanUser editarAfiliado(AfiliadoDTO datos, Long id) {

        BeanUser existente = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));


        existente.setNombre(datos.getNombre());
        existente.setApellidoP(datos.getApellidoP());
        existente.setApellidoM(datos.getApellidoM());
        existente.setEdad(datos.getEdad());
        existente.setTelefono(datos.getTelefono());
        existente.setGenero(datos.getGenero());

        if (datos.getFoto() != null) {
            existente.setFoto(datos.getFoto());
        }

        return userRepository.save(existente);
    }


    public List<AfiliadoDTO> listarAfiliados() {
        List<BeanUser> afiliadosEntidad = userRepository.findByRol(UserType.AFILIADO);

        return afiliadosEntidad.stream()
                .map(user -> {
                    AfiliadoDTO dto = new AfiliadoDTO();

                    dto.setNombre(user.getNombre());
                    dto.setApellidoP(user.getApellidoP());
                    dto.setApellidoM(user.getApellidoM());
                    dto.setGenero(user.getGenero());
                    dto.setEdad(user.getEdad());
                    dto.setTelefono(user.getTelefono());
                    if (user.getFoto() != null) {
                        dto.setFoto(user.getFoto());
                    }


                    return dto;
                })
                .toList();
    }


    // eliminacion
    public ResponseEntity eliminarUsuario(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("El usuario que intentas eliminar no existe");
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }




    // Filtros
    public List<BeanUser> buscarConFiltros(LocalDate inicio, LocalDate fin) {

        if (inicio == null) inicio = LocalDate.now().minusMonths(1); // Hace un mes
        if (fin == null) fin = LocalDate.now().plusMonths(1);       // En un mes

        return userRepository.filtrarDinamico(inicio, fin);
    }


    public List<BeanUser> buscarPorNombreYApellidos(String nombre, String apellidoP, String apellidoM) {
        String buscarNombre = (nombre != null) ? nombre : "";
        String buscarApellidoP = (apellidoP != null) ? apellidoP : "";
        String buscarApellidoM = (apellidoM != null) ? apellidoM : "";


        return userRepository.BusquedaNombre(buscarNombre, buscarApellidoP, buscarApellidoM);
    }


}
