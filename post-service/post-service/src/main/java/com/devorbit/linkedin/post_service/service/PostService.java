package com.devorbit.linkedin.post_service.service;

import com.devorbit.linkedin.post_service.dto.PostCreateRequestDto;
import com.devorbit.linkedin.post_service.dto.PostDto;

import java.util.List;

public interface PostService {

	PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId);

	PostDto getPostById(Long postId);

	List<PostDto> getAllPostsOfUser(Long userId);
}
