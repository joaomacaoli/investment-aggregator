package tech.buildrun.agregadordeinvestimentos.controller;

import tech.buildrun.agregadordeinvestimentos.entity.User;
import tech.buildrun.agregadordeinvestimentos.service.UserService;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/users")
public class UserController {
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping()
  public ResponseEntity<User> createUser(@RequestBody CreateUserDTO createUserDto) {
    var userId = userService.createUser(createUserDto);

    return ResponseEntity.created(URI.create("/v1/users/" + userId.toString())).build();
  }


  @GetMapping("/{userId}")
  public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
    var user = userService.getUserById(userId);

    if (user.isEmpty()) return ResponseEntity.notFound().build();

    return ResponseEntity.ok(user.get());
  }

  @GetMapping
  public ResponseEntity<List<User>> listUsers() {
    var users = userService.listUsers();

    return ResponseEntity.ok(users);
  }

  @PutMapping("/{userId}")
  public ResponseEntity<Void> updateUserById(@PathVariable("userId") String userId,
    @RequestBody UpdateUserDTO updateUserDTO) {

    userService.updateUserById(userId, updateUserDTO);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<Void> deleteById(@PathVariable("userId") String userId){
    userService.deleteById(userId);

    return ResponseEntity.noContent().build();
  }
}
