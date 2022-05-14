package com.example.demo.src.villagelifeposts;

import com.example.demo.src.posts.model.GetPostUrlsRes;
import com.example.demo.src.villagelifeposts.model.*;

import javax.sql.DataSource;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VillageLifePostDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource); }

    public List<GetVillageLifePostsRes> getVillageLifePosts(String village) {
        String getVillageLifePostsQuery =
                "select distinct(a.villageLifePostId), a.title, a.createdAt, b.cname, c.vname," +
                " d.nickname, e.commentCount, f.villageLifePostLikeCount, g.URL" +
                " from villagelifepost a" +

                " join (" +
                " select villageLifePostCategoryId, name as 'cname'" +
                " from villagelifepostcategory" +
                " ) as b" +
                " on a.villageLifePostCategoryId = b.villageLifePostCategoryId" +

                " join (" +
                " select village1Id, name as 'vname'" +
                " from village1" +
                " ) as c" +
                " on a.village1id = c.village1Id" +

                " join (" +
                " select userId, nickname" +
                " from user" +
                " ) as d" +
                " on a.userId = d.userId" +

                " left join (" +
                " select commentId, villageLifePostId, count(commentId) as 'commentCount'" +
                " from comment" +
                " group by commentId" +
                " ) as e" +
                " on a.villageLifePostId = e.villageLifePostId" +

                " left join (" +
                " select villageLifePostId, count(villageLifePostId) as 'villageLifePostLikeCount'" +
                " from villagelifepostlike" +
                " group by villageLifePostId" +
                " ) as f" +
                " on a.villageLifePostId = f.villageLifePostId" +

                " left join (" +
                " select villageLifePostImageId, villageLifePostId, URL, representative" +
                " from villagelifepostimage" +
                " where representative = true" +
                " ) as g" +
                " on a.villageLifePostId = g.villageLifePostId" +

                " where c.vname = ?";
        String getVillageLifePostsByVillage = village;
        return this.jdbcTemplate.query(getVillageLifePostsQuery, (rs, rowNum) -> new GetVillageLifePostsRes(
                rs.getInt("villageLifePostId"),
                rs.getString("title"),
                rs.getString("createdAt"),
                rs.getString("cname"),
                rs.getString("vname"),
                rs.getString("nickname"),
                rs.getInt("commentCount"),
                rs.getInt("villageLifePostLikeCount"),
                rs.getString("URL")),
                new Object[]{getVillageLifePostsByVillage});
    }

    public GetVillageLifePostInfoRes getVillageLifePost(String village, int postId) {
        String getVillageLifePostQuery =
                "select a.villageLifePostId, a.content, a.createdAt, b.nickname, d.vname," +
                " e.cname, f.villageLifePostLikeCount, g.commentCount" +
                " from villagelifepost a" +

                " join (" +
                " select userId, nickname" +
                " from user" +
                " ) as b" +
                " on a.userId = b.userId" +

                " join (" +
                " select userId, village1Id" +
                " from uservillage" +
                " ) as c" +
                " on a.userId = c.userId" +

                " join (" +
                " select village1id, name as 'vname'" +
                " from village1" +
                " ) as d" +
                " on c.village1Id = d.village1id" +

                " join (" +
                " select villageLifePostCategoryId, name as 'cname'" +
                " from villagelifepostcategory" +
                " ) as e" +
                " on a.villageLifePostCategoryId = e.villageLifePostCategoryId" +

                " join (" +
                " select villageLifePostId, count(villageLifePostId) as 'villageLifePostLikeCount'" +
                " from villagelifepostlike" +
                " group by villageLifePostId" +
                " ) as f" +
                " on a.villageLifePostId = f.villageLifePostId" +

                " join (" +
                " select villageLifePostId, count(villageLifePostId) as 'commentCount'" +
                " from comment" +
                " group by villageLifePostId" +
                " ) as g" +
                " on a.villageLifePostId = g.villageLifePostId" +

                " where d.vname = ? and a.villageLifePostId = ?";
        String getVillageLifePostByVillage = village;
        int getVillageLifePostByPostId = postId;
        return this.jdbcTemplate.queryForObject(getVillageLifePostQuery, (rs, rowNum) -> new GetVillageLifePostInfoRes(
                rs.getInt("villageLifePostId"),
                rs.getString("content"),
                rs.getString("createdAt"),
                rs.getString("nickname"),
                rs.getString("vname"),
                rs.getString("cname"),
                rs.getInt("villageLifePostLikeCount"),
                rs.getInt("commentCount")),
                new Object[]{getVillageLifePostByVillage, getVillageLifePostByPostId});
    }

    public PostVillageLifePostRes postVillageLifePost(String village, PostVillageLifePostReq postVillageLifePostReq) {
        String postVillageLifePostQuery =
                "insert into villagelifepost" +
                " (userId, villageLifePostCategoryId, village1Id," +
                " title, content, createdAt, updatedAt, status)" +
                " values" +
                " (?, ?, (select village1Id from village1 where name = ?), ?, ?, now(), null, 'posting')";
        String postVillageLifePostByVillage = village;
        this.jdbcTemplate.update(postVillageLifePostQuery,
                new Object[]{postVillageLifePostReq.getUserId(), postVillageLifePostReq.getVillageLifePostCategoryId(),
                        postVillageLifePostByVillage, postVillageLifePostReq.getTitle(),
                        postVillageLifePostReq.getContent()});
        String resultQuery =
                "select last_insert_id() as 'villageLifePostId'";
        return this.jdbcTemplate.queryForObject(resultQuery, (rs, rowNum) -> new PostVillageLifePostRes(
                rs.getInt("villageLifePostId")));
    }

    public Integer patchVillageLifePost(String village, int postId, PatchVillageLifePostReq patchVillageLifePostReq) {
        String patchVillageLifePostQuery =
                "update villagelifepost" +
                " set villageLifePostCategoryId = ?, title = ?, content = ?" +
                " where village1Id =" +
                " (select village1Id from village1 where name = ?)" +
                " and villageLifePostId = ?";
        String patchVillageLifePostByVillage = village;
        int patchVillageLifePostByPostId = postId;
        this.jdbcTemplate.update(patchVillageLifePostQuery,
                new Object[]{patchVillageLifePostReq.getVillageLifePostCategoryId(),
                        patchVillageLifePostReq.getTitle(), patchVillageLifePostReq.getContent(),
                        patchVillageLifePostByVillage, patchVillageLifePostByPostId});
        int patchVillageLifePostRes = postId;
        return patchVillageLifePostRes;
    }

    public List<GetCommentsRes> getComments(int villageLifePostId) {
        String getComments =
                "select a.commentId, a.villageLifePostId, b.nickname, a.content, a.createdAt" +
                " from comment a" +
                "" +
                " join (" +
                " select userId, nickname" +
                " from user" +
                " ) as b" +
                " on a.userId = b.userId" +
                "" +
                " where a.villageLifePostId = ?";
        return this.jdbcTemplate.query(getComments, (rs, rowNum) -> new GetCommentsRes(
                rs.getInt("commentId"),
                rs.getInt("villageLifePostId"),
                rs.getString("nickname"),
                rs.getString("content"),
                rs.getString("createdAt")),
                new Object[]{villageLifePostId});
    }

    public List<GetVillageLifePostUrlsRes> getVillageLifePostUrls(int postId) {
        String getVillageLifePostURLsQuery =
                "select villageLifePostImageId, URL, representative" +
                        " from villagelifepostimage" +
                        " where villageLifePostImageId = ?";
        return this.jdbcTemplate.query(getVillageLifePostURLsQuery, (rs, rowNum) -> new GetVillageLifePostUrlsRes(
                        rs.getInt("villageLifePostImageId"),
                        rs.getString("URL"),
                        rs.getBoolean("representative")),
                new Object[]{postId});
    }
}
