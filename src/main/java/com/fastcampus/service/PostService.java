package com.fastcampus.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fastcampus.domain.Post;
import com.fastcampus.persistence.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	// 게시글 등록
	@Transactional
	public void insertPost(Post post) {
		postRepository.save(post);
	}

	// 게시글 수정
	@Transactional
	public void updatePost(Post post) {
		Post updatedPost = postRepository.findById(post.getId()).get();
		updatedPost.setTitle(post.getTitle());
		updatedPost.setContent(post.getContent());
	}

	// 게시글 삭제
	@Transactional
	public void deletePost(int id) {
		postRepository.deleteById(id);
	}

	// 게시글 조회
	@Transactional
	public Post getPost(int id) {
		return postRepository.findById(id).get();
	}

	// 게시글 목록 조회
	@Transactional
	public Page<Post> getPostList(Pageable pageable) {
		return postRepository.findAll(pageable);
	}
}
