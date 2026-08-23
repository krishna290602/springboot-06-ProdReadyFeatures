package com.springboot.sb_04_01_BasicSetup.controllers;

import com.springboot.sb_04_01_BasicSetup.dto.PostDTO;
import com.springboot.sb_04_01_BasicSetup.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDTO> getPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public PostDTO getPostById(@PathVariable(name="postId") Long id) {
        return postService.getPostById(id);
    }

    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO inputPost) {
        return postService.createNewPost(inputPost);
    }
}
