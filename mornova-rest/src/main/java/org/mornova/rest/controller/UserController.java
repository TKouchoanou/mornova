package org.mornova.rest.controller;

import org.mornova.domain.core.model.entities.User;
import org.mornova.domain.core.model.objectValue.ids.UserId;
import org.mornova.domain.core.repository.RoleRepository;
import org.mornova.domain.core.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("users")
@RestController
public class UserController {
    UserRepository userRepository;

    RoleRepository roleRepository;
    DataFaker faker;


    public UserController(UserRepository userRepository, RoleRepository roleRepository, DataFaker faker) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.faker = faker;
        faker.initRole();
    }


    @GetMapping("/create")
    public User create(){
        User user=faker.fakeUser();
      User userp=  userRepository.save(user);
      System.out.println(userp);
      return userp;

    }


    @PostMapping
    public User create(@RequestBody UserCommand userCommand){
        User user=userCommand.toDomain();
       userCommand.roles.stream().map(roleRepository::findByName)
               .filter(Optional::isPresent)
               .map(Optional::get)
               .forEach(user::addRole);
        User userp=  userRepository.save(user);
        System.out.println(userp);
        return userp;

    }
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable  String id) {
        return userRepository.find(new UserId(UUID.fromString(id))).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }
    public static class UserCommand{
        private  String firstName;
        private String lastName;
        private String email;
        private List<String> roles;

        User toDomain(){
            return User.builder().email(email).lastName(lastName).firstName(firstName).build();
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }
    }
}
