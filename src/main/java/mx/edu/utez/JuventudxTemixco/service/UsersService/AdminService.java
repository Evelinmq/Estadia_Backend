package mx.edu.utez.JuventudxTemixco.service.UsersService;

import mx.edu.utez.JuventudxTemixco.Dto.usersDto.AdministradorDTO;
import mx.edu.utez.JuventudxTemixco.Dto.usersDto.AfiliadoDTO;
import mx.edu.utez.JuventudxTemixco.Dto.usersDto.BeneficiarioDTO;
import mx.edu.utez.JuventudxTemixco.models.municipalities.BeanMunicipality;
import mx.edu.utez.JuventudxTemixco.models.municipalities.MunicipalityRepository;
import mx.edu.utez.JuventudxTemixco.models.users.BeanUser;
import mx.edu.utez.JuventudxTemixco.models.users.UserRepository;

import java.time.LocalDate;
import java.util.List;

public class AdminService {

    private MunicipalityRepository municipalityRepository;

    UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public BeanUser createUserAdmin(AdministradorDTO datos) {

        BeanUser user = new BeanUser();

        if (userRepository.existsByCorreo(datos.getCorreo())) {
            throw new IllegalArgumentException("El correo ya se encuentra registrado, intenta con otro correo");
        }

        user.setNombre(datos.getNombre());
        user.setApellidoP(datos.getApellidoP());
        user.setApellidoM(datos.getApellidoP());
        user.setCorreo(datos.getCorreo());
        user.setContrasena(datos.getContrasena());

        return userRepository.save(user);

    }

    public List<BeanUser> buscarPorNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return userRepository.findAll();
        }

        return userRepository.NombreUsuario(nombre);
    }

    public void eliminarUsuario(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("El usuario que intentas eliminar no existe");
        }
        userRepository.deleteById(id);
    }

    public BeanUser editarAdmin(AdministradorDTO datos, Long id) {

        BeanUser existente = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        existente.setNombre(datos.getNombre());
        existente.setApellidoP(datos.getApellidoP());
        existente.setApellidoM(datos.getApellidoM());
        existente.setCorreo(datos.getCorreo());
        existente.setContrasena(datos.getContrasena());


        return userRepository.save(existente);


    }


    public BeanUser editarBeneficiario(BeneficiarioDTO datos, Long id) {

        BeanUser existente = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        BeanMunicipality municipio = municipalityRepository.getReferenceById(datos.getId_Municipio());

        if(userRepository.existsByCorreo(datos.getCorreo())){
            throw new IllegalArgumentException("El correo ya se encuentra registrado, intenta con otro correo");
        }

        existente.setNombre(datos.getNombre());
        existente.setApellidoP(datos.getApellidoP());
        existente.setApellidoM(datos.getApellidoM());
        existente.setGenero(datos.getGenero());
        existente.setMunicipio(municipio);
        existente.setColonia(datos.getColonia());
        existente.setCorreo(datos.getCorreo());
        existente.setTelefono(datos.getTelefono());
        existente.setFoto(datos.getFoto());


        return userRepository.save(existente);


    }

    public BeanUser editarAfiliado(AfiliadoDTO datos, Long id) {

        BeanUser existente = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        datos.setNombre(existente.getNombre());
        datos.setApellidoP(existente.getApellidoP());
        datos.setApellidoM(existente.getApellidoM());
        datos.setEdad(existente.getEdad());
        datos.setTelefono(existente.getTelefono());
        datos.setGenero(existente.getGenero());
        datos.setFoto(existente.getFoto());

        return userRepository.save(existente);


    }


    public List<BeanUser> listarPorRangoFechas(LocalDate inicio, LocalDate fin) {
        return userRepository.findByFechaBetween(inicio, fin);
    }

    public List<BeanUser> buscarConFiltros(LocalDate inicio, LocalDate fin, Long materiaId, Long mentorId) {

        if (inicio == null) inicio = LocalDate.now().minusMonths(1); // Hace un mes
        if (fin == null) fin = LocalDate.now().plusMonths(1);       // En un mes

        return userRepository.filtrarDinamico(inicio, fin);
    }








}
