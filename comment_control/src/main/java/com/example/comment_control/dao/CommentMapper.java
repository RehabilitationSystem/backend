

package com.example.comment_control.dao;


import com.example.comment_control.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface CommentMapper {
    @Insert("insert into comment(authorId,content,datetime)values(#{authorId},#{content},#{dataTime})")
    public void addComment(Comment comment);
    @Select("select * from comment where authorId=${id}")
    public Comment findById(String id);
    @Select("select * from comment")
    public List<Comment> listAll();
}
