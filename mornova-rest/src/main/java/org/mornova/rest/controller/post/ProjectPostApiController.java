package org.mornova.rest.controller.post;

import org.mornova.command.CommandManager;
import org.mornova.command.command.HasActionToPerform;
import org.mornova.command.command.post.comment.ProjectPostCommentCommand;
import org.mornova.command.command.post.project.CreateProjectPostCommand;
import org.mornova.query.projector.PostProjector;
import org.mornova.query.query.UserPostQuery;
import org.mornova.query.result.ProjectPostQR;
import org.mornova.rest.controller.Endpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(Endpoint.PROJECT_POST_API_ENDPOINT)
@RestController
public class ProjectPostApiController {
    CommandManager commandManager;
    PostProjector postProjector;

    public ProjectPostApiController(CommandManager commandManager, PostProjector postProjector) {
        this.commandManager = commandManager;
        this.postProjector = postProjector;
    }

    @GetMapping("/{userId}")
    List<ProjectPostQR> getUserProjectPost(@PathVariable String userId){
        UserPostQuery query=UserPostQuery
                .builder()
                .userIds(List.of(userId))
                .build();
        return postProjector.findProjectPost(query);
    }

    @PostMapping
    ResponseEntity<String> create(@RequestBody CreateProjectPostCommand createProjectPostCommand){
        System.out.println("Command reçu....................");
        commandManager.process(createProjectPostCommand);
        return ResponseEntity.ok(createProjectPostCommand.getId());
    }

    @PostMapping("/addComment")
    ResponseEntity<String> addComment(@RequestBody ProjectPostCommentCommand addCommentCommand){
        addCommentCommand.setActionType(HasActionToPerform.Action.ADD);
        commandManager.process(addCommentCommand);
        return ResponseEntity.ok(addCommentCommand.getPostId());
    }

    @PostMapping("/updateComment")
    ResponseEntity<String> updateComment(@RequestBody ProjectPostCommentCommand updateCommentCommand){
        updateCommentCommand.setActionType(HasActionToPerform.Action.UPDATE);
        commandManager.process(updateCommentCommand);
        return ResponseEntity.ok(updateCommentCommand.getPostId());
    }

    @PostMapping("/deleteComment")
    ResponseEntity<String> deleteComment(@RequestBody ProjectPostCommentCommand deleteCommentCommand){
        deleteCommentCommand.setActionType(HasActionToPerform.Action.DELETE);
        commandManager.process(deleteCommentCommand);
        return ResponseEntity.ok(deleteCommentCommand.getPostId());
    }

}
