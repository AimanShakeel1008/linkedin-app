package com.devorbit.linkedin.post_service.controller;

import com.devorbit.linkedin.post_service.dto.PostCreateRequestDto;
import com.devorbit.linkedin.post_service.dto.PostDto;
import com.devorbit.linkedin.post_service.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostsController {

	private final PostService postService;

	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto, HttpServletRequest request) {

		PostDto createdPost = postService.createPost(postCreateRequestDto, 1L);

		return new ResponseEntity<>(createdPost, HttpStatus.CREATED);

	}

	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPost(@PathVariable Long postId) {

		PostDto post = postService.getPostById(postId);

		return new ResponseEntity<>(post, HttpStatus.OK);

	}

	@GetMapping("/users/{userId}/allPosts")
	public ResponseEntity<List<PostDto>> getAllPostsOfUser(@PathVariable Long userId) {
		List<PostDto> posts = postService.getAllPostsOfUser(userId);

		return ResponseEntity.ok(posts);
	}
}
