package org.mornova.rest.controller;

import org.mornova.domain.core.model.entities.post.Comment;
import org.mornova.domain.core.model.entities.post.Like;
import org.mornova.domain.core.model.entities.post.ProjectPost;
import org.mornova.domain.core.model.entities.User;
import org.mornova.domain.core.model.objectValue.ids.PostId;
import org.mornova.domain.core.repository.ProjectPostRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "post")
public class PostController
{
    ProjectPostRepository postRepository;
    DataFaker faker;

    public PostController(ProjectPostRepository postRepository, DataFaker faker) {
        this.postRepository = postRepository;
        this.faker = faker;
    }


    @GetMapping("/create")
    public ProjectPost create(){
        ProjectPost post= faker.fakePost();
        return postRepository.save(post);
    }
    @GetMapping("/create/with/comment")
    public ProjectPost createWithCommentLike(){
        ProjectPost post= faker.fakePost();
        User commentAuthor=faker.fakeUserPersistedUser();
        User liker=faker.fakeUserPersistedUser();
        Comment comment=faker.fakeComment(commentAuthor);
        Like like=faker.fakeLike(liker);
        post.addLike(like);
        post.addComment(comment);
        return postRepository.save(post);
    }
    @PostMapping
    public ProjectPost create(@RequestBody ProjectPost post){
        return postRepository.save(post);
    }
    @GetMapping("/{id}")
    public ProjectPost get(@PathVariable String id){
        return postRepository.find(new PostId(UUID.fromString(id))).orElse(null);
    }
    @GetMapping("/{postId}")
    public ProjectPost addComment(@PathVariable String postId, @RequestBody Comment comment){
        Optional<ProjectPost> optionalPost= postRepository.find(new PostId(UUID.fromString(postId)));
      return  optionalPost.map(projectPost -> {
            projectPost.addComment(comment);
            return  projectPost;
        }).map(postRepository::save).orElse(null);
    }
}
