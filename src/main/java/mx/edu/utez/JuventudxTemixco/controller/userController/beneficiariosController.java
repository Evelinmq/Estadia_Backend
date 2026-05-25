package mx.edu.utez.JuventudxTemixco.controller.userController;

import jakarta.validation.Valid;
import mx.edu.utez.JuventudxTemixco.Dto.usersDto.AfiliadoDTO;
import mx.edu.utez.JuventudxTemixco.Dto.usersDto.BeneficiarioDTO;
import mx.edu.utez.JuventudxTemixco.models.users.BeanUser;
import mx.edu.utez.JuventudxTemixco.service.UsersService.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/beneficiarios")
@CrossOrigin
public class beneficiariosController {

    private UserService userService;

    public beneficiariosController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping
    public List<BeneficiarioDTO> listaBeneficiario() {
        return userService.listarBeneficiarios();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarBeneficiario(@PathVariable Long id) {
        return userService.eliminarUsuario(id);
    }

    @PostMapping
    public BeanUser agregaBeneficiario(@Valid @RequestBody BeneficiarioDTO user) {
        return userService.createUserBeneficiario(user);
    }

    @PutMapping("/{id}")
    public BeanUser actualizaBeneficiario(@Valid @RequestBody BeneficiarioDTO user, @PathVariable Long id) {
        return userService.editarBeneficiario(user, id);
    }



    //Filtros
    @GetMapping("/rangoFecha") //
    public List<BeanUser> rangoFecha(LocalDate inicio, LocalDate fin) {
        return userService.buscarConFiltros(inicio, fin);
    }

    @GetMapping("/buscarBeneficiario") //
    public ResponseEntity<List<BeanUser>> buscarPorNombreYApellidos(
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "apellidoP", required = false) String apellidoP,
            @RequestParam(value = "apellidoM", required = false) String apellidoM) {


        List<BeanUser> usuarios = userService.buscarPorNombreYApellidos(nombre, apellidoP, apellidoM);

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarios);
    }


}
