package com.service.SkillShare.controller;

import com.service.SkillShare.dto.CreatePostDto;
import com.service.SkillShare.dto.GetPostDto;
import com.service.SkillShare.dto.UpdatePostDto;
import com.service.SkillShare.dto.MediaDto;
import com.service.SkillShare.entity.Posts;
import com.service.SkillShare.service.impl.PostsServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
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
    public ResponseEntity<List<GetPostDto>> getAllPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(postsService.getAllPosts());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetPostDto> getPostById(@PathVariable("id") String postId) {
        return ResponseEntity.status(HttpStatus.OK).body(postsService.getPostById(postId));
    }

    @PutMapping(value = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Posts> updatePost(
            @PathVariable("id") String postId,
            @RequestPart("ImgFile") MultipartFile file,
            @RequestPart("Tags") String Tags,
            @RequestPart("ContentTitle") String contentTitle,
            @RequestPart("PostDescription") String postDescription
    ) {
        UpdatePostDto updatePostDto = new UpdatePostDto();
        updatePostDto.setImgFile(file);
        updatePostDto.setTags(Tags);
        updatePostDto.setContentTitle(contentTitle);
        updatePostDto.setPostDescription(postDescription);

        var result = postsService.updatePost(postId, updatePostDto);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePost(@PathVariable String id) {
        if (postsService.deletePost(id)) {
            return new ResponseEntity<>("Post deleted Successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Post deleted Successfully.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/media/stream/{id}")
    public void streamMedia(@PathVariable String id, HttpServletResponse response) throws Exception {
        MediaDto media = postsService.getMedia(id);
        FileCopyUtils.copy(media.getMediaStream(), response.getOutputStream());
    }
}
