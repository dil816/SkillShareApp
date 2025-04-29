package com.service.SkillShare.controller;

import com.service.SkillShare.dto.PostDto;
import com.service.SkillShare.entity.Posts;
import com.service.SkillShare.service.impl.PostsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/posts")
public class PostsController {
    private final PostsServiceImpl postsService;

    PostsController(PostsServiceImpl postsService) {
        this.postsService = postsService;
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Posts> postPost(
            @RequestPart("ImgFile") MultipartFile file,
            @RequestPart("Tags") String Tags,
            @RequestPart("ContentTitle") String contentTitle,
            @RequestPart("PostDescription") String postDescription
    ) {

        PostDto postDto = new PostDto();
        postDto.setImgFile(file);
        postDto.setTags(Tags);
        postDto.setContentTitle(contentTitle);
        postDto.setPostDescription(postDescription);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postsService.createPost(postDto));
    }
    // can use @RequestPart than @ModelAttribute
}
