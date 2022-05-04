package com.example.demo.src.post;

import com.example.demo.src.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PostDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Post> getPosts() {
        String getPostsQuery = "select * from post";
        return this.jdbcTemplate.query(getPostsQuery, (rs, rowNum) -> new Post(
                rs.getInt("postId"),
                rs.getInt("userId"),
                rs.getInt("postCategoryId"),
                rs.getInt("village1Id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getInt("price"),
                rs.getString("createdAt"),
                rs.getString("updatedAt"),
                rs.getString("status")
                )
        );
    }
}