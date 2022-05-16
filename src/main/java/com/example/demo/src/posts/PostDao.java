package com.example.demo.src.posts;

import com.example.demo.src.posts.model.*;

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
        return this.jdbcTemplate.query(getPostsQuery, (rs, rowNum) -> new GetPostsRes(
                rs.getInt("postId"),
                rs.getString("title"),
                rs.getInt("price"),
                rs.getString("createdAt"),
                rs.getString("name"),
                rs.getInt("chatroomCount"),
                rs.getInt("interestPostCount"),
                rs.getString("URL")),
                new Object[]{village});
    }

    public GetPostInfoRes getPostInfo(String village, int postId) {
        String getPostQuery =
                "select a.postId, a.title, a.price, a.content, a.createdAt, b.nickname, d.vname, e.cname" +
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

                " where d.vname = ? and a.postId = ?";
        return this.jdbcTemplate.queryForObject(getPostQuery, (rs, rowNum) -> new GetPostInfoRes(
                rs.getInt("postId"),
                rs.getString("title"),
                rs.getInt("price"),
                rs.getString("content"),
                rs.getString("createdAt"),
                rs.getString("nickname"),
                rs.getString("vname"),
                rs.getString("cname")),
                new Object[]{village, postId});
    }

    public List<GetPostUrlsRes> getPostUrls(int postId) {
        String getPostUrlsQuery =
                "select a.postImageId, a.URL, a.representative" +
                " from postimage a" +
                " where a.postId = ?";
        return this.jdbcTemplate.query(getPostUrlsQuery, (rs, rowNum) -> new GetPostUrlsRes(
                rs.getInt("postImageId"),
                rs.getString("URL"),
                rs.getBoolean("representative")),
                new Object[]{postId});
    }

    public PostPostRes postPost(String village, PostPostReq postPostReq) {
        String postPostQuery =
                "insert into post" +
                " (userId, postCategoryId, village1Id, title, content, price, createdAt, updatedAt, status)" +
                " values" +
                " (?, ?, (select village1Id from village1 where name = ?), ?, ?, ?, now(), null, 'posting')";
        String postPostByVillage = village;
        this.jdbcTemplate.update(postPostQuery,
                new Object[]{postPostReq.getUserId(), postPostReq.getPostCategoryId(), postPostByVillage,
                        postPostReq.getTitle(), postPostReq.getContent(), postPostReq.getPrice()});
        String resultQuery =
                "select last_insert_id() as 'postId'";
        return this.jdbcTemplate.queryForObject(resultQuery, (rs, rowNum) -> new PostPostRes(
                rs.getInt("postId")));
    }

    public Integer patchPost(String village, int postId, PatchPostReq patchPostReq) {
        String patchPostQuery =
                "update post" +
                " set postCategoryId = ?, title = ?, content = ?, price = ?" +
                " where village1Id =" +
                " (select village1Id from village1 where name = ?)" +
                " and postid = ?";
        String patchPostByVillage = village;
        int patchPostByPostId = postId;
        this.jdbcTemplate.update(patchPostQuery,
                new Object[]{patchPostReq.getPostCategoryId(), patchPostReq.getTitle(), patchPostReq.getContent(),
                        patchPostReq.getPrice(), patchPostByVillage, patchPostByPostId});
        int patchPostRes = postId;
        return patchPostRes;
    }

    public Integer deletePost(String village, int postId) {
        String deletePostQuery =
                "update post" +
                " set status = 'delete'" +
                " where village1Id =" +
                " (select village1Id from village1 where name = ?)" +
                " and postid = ?";
        this.jdbcTemplate.update(deletePostQuery, new Object[]{village, postId});
        return postId;
    }

    public List<GetPostsRes> getPostsByCategoryId(String village, int postcategoryId) {
        String getPostsByCategoryIdQuery =
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

                        " where b.name = ? and a.postcategoryId = ?";
        return this.jdbcTemplate.query(getPostsByCategoryIdQuery, (rs, rowNum) -> new GetPostsRes(
                        rs.getInt("postId"),
                        rs.getString("title"),
                        rs.getInt("price"),
                        rs.getString("createdAt"),
                        rs.getString("name"),
                        rs.getInt("chatroomCount"),
                        rs.getInt("interestPostCount"),
                        rs.getString("URL")),
                new Object[]{village, postcategoryId});
    }
}
