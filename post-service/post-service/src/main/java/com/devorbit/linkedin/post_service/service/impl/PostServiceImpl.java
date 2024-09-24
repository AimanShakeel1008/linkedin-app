package com.devorbit.linkedin.post_service.service.impl;

import com.devorbit.linkedin.post_service.dto.PostCreateRequestDto;
import com.devorbit.linkedin.post_service.dto.PostDto;
import com.devorbit.linkedin.post_service.entity.Post;
import com.devorbit.linkedin.post_service.repository.PostRepository;
import com.devorbit.linkedin.post_service.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId) {

		Post post = modelMapper.map(postCreateRequestDto, Post.class);

		post.setUserId(userId);

		Post savedPost = postRepository.save(post);

		return modelMapper.map(savedPost, PostDto.class);
	}
}
