package com.fastcampus.service;


import com.fastcampus.domain.Post;
import com.fastcampus.domain.Reply;
import com.fastcampus.domain.User;
import com.fastcampus.persistence.PostRepository;
import com.fastcampus.persistence.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private PostRepository postRepository;


    // 댓글 등록
    @Transactional
    public void insertReply(int postId, Reply reply, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 postId 없습니다. id=" + postId));
        reply.save(post, user);

        replyRepository.save(reply);
    }

    // 댓글 삭제
    @Transactional
    public void deleteReply(int replyId) {
        replyRepository.deleteById(replyId);
    }
}
