package com.my.salty_date.repository;

import com.my.salty_date.entity.Comment;
import com.my.salty_date.entity.Dating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    // select * from comment_table where board_id=? order by id desc;
    List<Comment> findAllByDatingOrderByCommentIdxDesc(Dating dating);
}
