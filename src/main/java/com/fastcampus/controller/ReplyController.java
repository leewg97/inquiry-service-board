package com.fastcampus.controller;

import com.fastcampus.domain.Reply;
import com.fastcampus.security.jpa.UserDetailsImpl;
import com.fastcampus.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReplyController {

    @Autowired
    private ReplyService replyService;


    @PostMapping("/post/{postId}/insertReply")
    @ResponseBody
    public String insertReply(@PathVariable int postId, @RequestBody Reply reply, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        replyService.insertReply(postId, reply, userDetailsImpl.getUser());
        return "댓글이 등록되었습니다.";
    }

    @DeleteMapping("/post/{postId}/deleteReply/{replyId}")
    @ResponseBody
    public String deleteReply(@PathVariable int replyId) {
        replyService.deleteReply(replyId);
        return "댓글이 삭제되었습니다.";
    }

}
