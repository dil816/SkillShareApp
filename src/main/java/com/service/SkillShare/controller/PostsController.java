package com.service.SkillShare.controller;

import com.service.SkillShare.dto.CreatePostDto;
import com.service.SkillShare.dto.GetPostDto;
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

        CreatePostDto createPostDto = new CreatePostDto();
        createPostDto.setImgFile(file);
        createPostDto.setTags(Tags);
        createPostDto.setContentTitle(contentTitle);
        createPostDto.setPostDescription(postDescription);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postsService.createPost(createPostDto));
    }
    // can use @RequestPart than @ModelAttribute

    @GetMapping()
    public  ResponseEntity<List<GetPostDto>> getAllPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(postsService.getAllPosts());
    }
}
