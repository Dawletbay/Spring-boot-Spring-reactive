package uz.best.company.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.best.company.service.UserService;
import uz.best.company.domain.auth.User;
import uz.best.company.dto.auth.UserDTO;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Tag(name = "User controller")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @Operation(method = "GET", description = "User list ", summary = "Get User list")
    @GetMapping
    public ResponseEntity<Flux<UserDTO>> getList() {
        return ResponseEntity.ok(userService.getList());
    }

    @Operation(method = "GET", description = "User get by ID ", summary = "Get User by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Mono<UserDTO>> get(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getById(id).map(User::getDto));
    }
}
