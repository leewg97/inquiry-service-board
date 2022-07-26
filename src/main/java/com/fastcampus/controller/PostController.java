package com.fastcampus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.fastcampus.domain.Post;
import com.fastcampus.security.jpa.UserDetailsImpl;
import com.fastcampus.service.PostService;

@Controller
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping({"", "/"})
	public String getPostList(Model model, @PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) {
		model.addAttribute("postList", postService.getPostList(pageable));
		return "welcome";
	}

	@GetMapping("/post/insertPost")
	public String insertPost() {
		return "post/insertPost";
	}

	@PostMapping("/post/insertPost")
	@ResponseBody
	public String insertPost(@RequestBody Post post, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		// Post 객체를 등록하기 위해서는 반드시 User 객체를 Post 에 설정해야 한다.
		// 그래야 Post 가 POST 테이블에 등록될 때 FK(USER_ID) 컬럽에 회원의 PK(ID)를 등록해준다.
		post.setUser(userDetailsImpl.getUser());
		postService.insertPost(post);
		return "새로운 1:1 문의를 등록했습니다.";
	}

	@GetMapping("/post/{id}")
	public String getPost(@PathVariable int id, Model model, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		model.addAttribute("post", postService.getPost(id));
		return "post/getPost";
	}

	@GetMapping("/post/updatePost/{id}")
	public String updatePost(@PathVariable int id, Model model) {
		model.addAttribute("post", postService.getPost(id));
		return "post/updatePost";
	}

	@PutMapping("/post/updatePost")
	@ResponseBody
	public String updatePost(@RequestBody Post post) {
		postService.updatePost(post);
		return "1:1 문의가 수정되었습니다.";
	}

	@DeleteMapping("/post/deletePost/{id}")
	@ResponseBody
	public String deletePost(@PathVariable int id) {
		postService.deletePost(id);
		return "1:1 문의가 삭제되었습니다.";
	}
}