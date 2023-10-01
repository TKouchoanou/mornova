package org.mornova.rest.controller.user;

import org.mornova.command.CommandManager;
import org.mornova.command.command.user.CreateUserCommand;
import org.mornova.command.command.user.UpdateUserCommand;
import org.mornova.query.projector.UserProjector;
import org.mornova.query.query.AllUserQuery;
import org.mornova.query.result.UserQR;
import org.mornova.rest.controller.Endpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(Endpoint.USERS_API_ENDPOINT)
@RestController
public class UserApiController {
    CommandManager commandManager;
    UserProjector userProjector;

    public UserApiController(CommandManager commandManager, UserProjector userProjector) {
        this.commandManager = commandManager;
        this.userProjector = userProjector;
    }

    @GetMapping
    public List<UserQR> index(){
        return userProjector.findAll(new AllUserQuery());
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CreateUserCommand userCommand){
        commandManager.process(userCommand);
        return ResponseEntity.ok().body(userCommand.getId());
    }

    @PutMapping
    public ResponseEntity update(@RequestBody UpdateUserCommand userCommand){
        commandManager.process(userCommand);
        return ResponseEntity.ok().body(userCommand.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable  String id) {

        return userProjector.findAll(new AllUserQuery(List.of(id)))
                .stream()
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}
