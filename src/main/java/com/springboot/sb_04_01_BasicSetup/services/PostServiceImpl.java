package com.springboot.sb_04_01_BasicSetup.services;

import com.springboot.sb_04_01_BasicSetup.dto.PostDTO;
import com.springboot.sb_04_01_BasicSetup.entities.PostEntity;
import com.springboot.sb_04_01_BasicSetup.exceptions.ResourceNotFoundException;
import com.springboot.sb_04_01_BasicSetup.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    // equivalent to @Autowired - Constructor Injection
    // Lombok will create the constructor and assign it to below
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPosts() {
//        List<PostEntity> postEntityList = postRepository.findAll();
//        List<PostDTO> postDTOList = new ArrayList<>();
//        for (PostEntity postEntity : postEntityList) {
//            postDTOList.add(modelMapper.map(postEntity, PostDTO.class));
//        }
//        return postDTOList;

        return postRepository.findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost) {
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
        postRepository.save(postEntity);
        return modelMapper.map(postEntity, PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long id) {
        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found."));
        return modelMapper.map(postEntity, PostDTO.class);
    }

    @Override
    public PostDTO updatePostById(Long id, PostDTO inputPost) {
        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found."));

        inputPost.setId(id);

        return modelMapper.map(
                postRepository.save(modelMapper.map(inputPost, PostEntity.class)), PostDTO.class);
    }
}
