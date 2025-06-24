package deploy.project.cloud_parking.Controller;

import deploy.project.cloud_parking.Controller.DTO.UserCreateDTO;
import deploy.project.cloud_parking.Model.User;
import deploy.project.cloud_parking.Repository.UserRepository;
import deploy.project.cloud_parking.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Cria um usuário autenticado", description = "Testando Spring Security")
    public User createUsers(@RequestBody UserCreateDTO dto) {
        User createdUser = userService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser).getBody();
    }

    @GetMapping
    @Operation(summary = "Lista todos os usuários autenticados", description = "Testando Spring Security")
    public List<User> findAll() {
        return userService.findAll();
    }
}
