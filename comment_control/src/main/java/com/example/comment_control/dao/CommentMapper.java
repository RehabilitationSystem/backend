package com.example.comment_control.dao;


import com.example.comment_control.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface CommentMapper {
    @Insert("insert into comment(authorId,content,datetime,authorName,is_Parent,newsId,parent_id,target)values" +
            "(#{authorId},#{content},#{dateTime},#{authorName},#{isParent},#{newsId},#{parentId},#{target})")
    public void addComment(Comment comment);
    @Select("select * from comment where authorId=${id}")
    public Comment findById(String id);
    @Select("select * from comment where is_Parent= '1'")
    public List<Comment> listParentComment();
    @Select("select * from comment where parent_id = ${parentId}")
    public List<Comment> listChildComments(Long parentId);
}
