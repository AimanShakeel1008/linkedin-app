package com.devorbit.linkedin.post_service.service.impl;

import com.devorbit.linkedin.post_service.entity.PostLike;
import com.devorbit.linkedin.post_service.exception.BadRequestException;
import com.devorbit.linkedin.post_service.exception.ResourceNotFoundException;
import com.devorbit.linkedin.post_service.repository.PostLikeRepository;
import com.devorbit.linkedin.post_service.repository.PostRepository;
import com.devorbit.linkedin.post_service.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeServiceImpl implements PostLikeService {

	private final PostLikeRepository postLikeRepository;
	private final PostRepository postRepository;

	@Override
	public void likePost(Long postId, Long userId) {

		boolean postExists = postRepository.existsById(postId);
		if(!postExists) throw new ResourceNotFoundException("Post not found with id: "+postId);

		boolean userAlreadyLikedPost = postLikeRepository.existsByUserIdAndPostId(userId, postId);
		if(userAlreadyLikedPost) throw new BadRequestException("Cannot like the same post again.");

		PostLike postLike = new PostLike();
		postLike.setPostId(postId);
		postLike.setUserId(userId);

		postLikeRepository.save(postLike);
	}

	@Override
	public void unlikePost(Long postId, Long userId) {

		boolean postExists = postRepository.existsById(postId);
		if(!postExists) throw new ResourceNotFoundException("Post not found with id: "+postId);

		boolean userAlreadyLikedPost = postLikeRepository.existsByUserIdAndPostId(userId, postId);
		if(!userAlreadyLikedPost) throw new BadRequestException("Cannot unlike the post which is not liked.");

		postLikeRepository.deleteByUserIdAndPostId(userId, postId);

	}
}
