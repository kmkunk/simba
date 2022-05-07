package com.example.demo.src.posts;

import com.example.demo.src.posts.model.GetPostRes;
import com.example.demo.src.posts.model.GetPostsRes;

import javax.sql.DataSource;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PostDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource); }

    public List<GetPostsRes> getPosts(String village) {
        String getPostsQuery =
                "select a.postId, a.title, a.createdAt, a.price, b.name, c.chatroomCount, d.interestPostCount, e.URL" +
                " from post a" +

                " join (" +
                " select village1id, name" +
                " from village1" +
                " ) as b" +
                " on a.village1id = b.village1id" +

                " join (" +
                " select postId, count(postId) as 'chatroomCount'" +
                " from chatroom" +
                " group by postId" +
                " ) as c" +
                " on a.postId = c.postId" +

                " join (" +
                " select postId, count(postId) as 'interestPostCount'" +
                " from chatroom" +
                " group by postId" +
                " ) as d" +
                " on a.postId = d.postId" +

                " join (" +
                " select postId, URL, representative" +
                " from postimage" +
                " where representative = true " +
                " ) as e" +
                " on a.postId = e.postId" +

                " where b.name = ?";
        String getPostsByVillage = village;
        return this.jdbcTemplate.query(getPostsQuery, (rs, rowNum) -> new GetPostsRes(
                rs.getInt("postId"),
                rs.getString("title"),
                rs.getInt("price"),
                rs.getString("createdAt"),
                rs.getString("name"),
                rs.getInt("chatroomCount"),
                rs.getInt("interestPostCount"),
                rs.getString("URL")),
                new Object[]{getPostsByVillage});
    }

    public GetPostRes getPost(String village, int userId) {
        String getPostQuery =
                "select a.postId, a.title, a.price, a.content, a.createdAt, b.nickname, d.vname, e.cname, f.URL" +
                " from post a" +

                " join (" +
                " select userId, nickname" +
                " from user" +
                " ) as b" +
                " on a.userId = b.userId" +

                " join (" +
                " select village1Id, userId" +
                " from uservillage" +
                " ) as c" +
                " on a.userId = c.userId" +

                " join (" +
                " select village1Id, name as 'vname'" +
                " from village1" +
                " ) as d" +
                " on c.village1Id = d.village1Id" +

                " join (" +
                " select postCategoryId, name as 'cname'" +
                " from postcategory" +
                " ) as e" +
                " on a.postCategoryId = e.postCategoryId" +

                " join (" +
                " select postImageId, postId, URL" +
                " from postimage" +
                " ) as f" +
                " on a.postId = f.postId" +

                " where d.vname = ? and a.postId = ?";
        String getPostByVillage = village;
        int getPostByUserId = userId;
        return this.jdbcTemplate.queryForObject(getPostQuery, (rs, rowNum) -> new GetPostRes(
                rs.getInt("postId"),
                rs.getString("title"),
                rs.getInt("price"),
                rs.getString("content"),
                rs.getString("createdAt"),
                rs.getString("nickname"),
                rs.getString("vname"),
                rs.getString("cname"),
                rs.getString("URL")),
                new Object[]{getPostByVillage, getPostByUserId});
    }
}