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
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    private AdminService adminService;


    public AdminController(AdminService adminService) {
        this.adminService = adminService;

    }


    @GetMapping
    public List<AdministradorDTO> Administradores() { //

        return adminService.listarAdministradores();
    }

    @PostMapping//
    public BeanUser createUser(@Valid @RequestBody AdministradorDTO datos) {
        return adminService.createUserAdmin(datos);
    }

    @PutMapping("/{id}") //
    public BeanUser updateUser(@Valid @RequestBody AdministradorDTO datos, @PathVariable long id) {
        return adminService.editarAdmin(datos, id);
    }


    @DeleteMapping("/{id}") //
    public ResponseEntity deleteUser(@PathVariable long id) {
        return adminService.eliminarUsuario(id);
    }


    // filtro de busqueda
    @GetMapping("/buscar") //
    public ResponseEntity<List<BeanUser>> buscarPorNombreYApellidos(
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "apellidoP", required = false) String apellidoP,
            @RequestParam(value = "apellidoM", required = false) String apellidoM) {


        List<BeanUser> usuarios = adminService.buscarPorNombreYApellidos(nombre, apellidoP, apellidoM);

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarios);
    }





}
