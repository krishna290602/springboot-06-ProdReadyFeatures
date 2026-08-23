package com.springboot.sb_04_01_BasicSetup.services;

import com.springboot.sb_04_01_BasicSetup.dto.PostDTO;
import java.util.List;


public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long id);
}
