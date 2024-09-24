package com.devorbit.linkedin.post_service.service;

import com.devorbit.linkedin.post_service.dto.PostCreateRequestDto;
import com.devorbit.linkedin.post_service.dto.PostDto;

public interface PostService {

	PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId);

	PostDto getPostById(Long postId);
}
