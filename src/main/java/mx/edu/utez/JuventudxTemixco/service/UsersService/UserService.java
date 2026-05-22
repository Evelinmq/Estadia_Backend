package mx.edu.utez.JuventudxTemixco.service.UsersService;

import jakarta.validation.constraints.Size;
import mx.edu.utez.JuventudxTemixco.Dto.usersDto.AfiliadoDTO;
import mx.edu.utez.JuventudxTemixco.Dto.usersDto.BeneficiarioDTO;
import mx.edu.utez.JuventudxTemixco.models.municipalities.BeanMunicipality;
import mx.edu.utez.JuventudxTemixco.models.municipalities.MunicipalityRepository;
import mx.edu.utez.JuventudxTemixco.models.users.BeanUser;
import mx.edu.utez.JuventudxTemixco.models.users.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private MunicipalityRepository municipalityRepository;

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
        user.setCorreo(datos.getCorreo());
        user.setFoto(datos.getFoto());

        return userRepository.save(user);

    }


    public BeanUser createUserAfiliado(AfiliadoDTO datos){

        BeanUser user = new BeanUser();

        user.setNombre(datos.getNombre());
        user.setApellidoP(datos.getApellidoP());
        user.setApellidoM(datos.getApellidoM());
        user.setGenero(datos.getGenero());
        user.setEdad(datos.getEdad());
        user.setTelefono(datos.getTelefono());
        user.setFoto(datos.getFoto());

        return userRepository.save(user);

    }

}
