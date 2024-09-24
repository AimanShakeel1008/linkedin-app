package com.devorbit.linkedin.post_service.controller;

import com.devorbit.linkedin.post_service.dto.PostCreateRequestDto;
import com.devorbit.linkedin.post_service.dto.PostDto;
import com.devorbit.linkedin.post_service.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
