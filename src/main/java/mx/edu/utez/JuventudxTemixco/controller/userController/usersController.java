package mx.edu.utez.JuventudxTemixco.controller.userController;


import jakarta.validation.Valid;
import mx.edu.utez.JuventudxTemixco.Dto.usersDto.AdministradorDTO;
import mx.edu.utez.JuventudxTemixco.models.users.BeanUser;
import mx.edu.utez.JuventudxTemixco.service.UsersService.AdminService;
import mx.edu.utez.JuventudxTemixco.service.UsersService.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
public class usersController {

    private AdminService adminService;
    private UserService userService;

    public usersController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }


    @GetMapping
    public List<BeanUser> Administradores() {

        return adminService.listarAdministradores();
    }

    @PostMapping
    public BeanUser createUser(@Valid @RequestBody AdministradorDTO datos) {
        return adminService.createUserAdmin(datos);
    }

    @PutMapping("/{id}")
    public BeanUser updateUser(@Valid @RequestBody AdministradorDTO datos, @PathVariable long id) {
        return adminService.editarAdmin(datos, id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<BeanUser>> buscarPorNombre(
            @RequestParam(value = "nombre", required = false) String nombre) {

        List<BeanUser> usuarios = adminService.buscarPorNombre(nombre);

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable long id) {
        return adminService.eliminarUsuario(id);
    }


    @GetMapping("/rangoFecha")
    public List<BeanUser> rangoFecha(LocalDate inicio, LocalDate fin) {
        return adminService.buscarConFiltros(inicio, fin);
    }


}
