package com.example.demo.src.users;

import com.example.demo.src.users.model.*;

import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource); }

    public GetUserRes getUser(int userId) {
        String getUserQuery =
                "select a.userId, a.nickname, a.URL, c.vname" +
                " from user a" +

                " join (" +
                " select village1Id, userId" +
                " from uservillage" +
                " ) as b" +
                " on a.userId = b.userId" +

                " join (" +
                " select village1id, name as 'vname'" +
                " from village1" +
                " ) as c" +
                " on b.village1id = c.village1id" +

                " where a.userId = ?";
        int getUserByUserId = userId;
        return this.jdbcTemplate.queryForObject(getUserQuery, (rs, rowNum) -> new GetUserRes(
                rs.getInt("userId"),
                rs.getString("nickname"),
                rs.getString("URL"),
                rs.getString("vname")),
                new Object[]{getUserByUserId});
    }

    public List<GetBadgesRes> getBadges(int userId) {
        String getBadgesQuery =
                "select a.badgeId, a.name, a.gold, b.representative" +
                " from badge a" +

                " join (" +
                " select userId, badgeId, representative" +
                " from userbadge" +
                " ) as b" +
                " on a.badgeId = b.badgeId" +

                " where b.userId = ?";
        int getBadgesByUserId = userId;
        return this.jdbcTemplate.query(getBadgesQuery, (rs, rowNum) -> new GetBadgesRes(
                rs.getInt("badgeId"),
                rs.getString("name"),
                rs.getBoolean("gold"),
                rs.getBoolean("representative")),
                new Object[]{getBadgesByUserId});
    }

    public List<GetSalesRes> getSales(int userId, String status) {
        String getSalesQuery =
                "select a.postId, a.title, a.price, a.createdAt, a.updatedAt, a.status," +
                " b.chatroomCount, c.interestPostCount, d.URL, e.name" +
                " from post a" +

                " left join (" +
                " select postId, count(postId) as 'chatroomCount'" +
                " from chatroom" +
                " group by postId" +
                " ) as b" +
                " on a.postId = b.postId" +

                " left join (" +
                " select postId, count(postId) as 'interestPostCount'" +
                " from interestpost" +
                " group by postId" +
                " ) as c" +
                " on a.postId = c.postId" +

                " left join (" +
                " select postId, URL, representative" +
                " from postimage" +
                " where representative = true" +
                " ) as d" +
                " on a.postId = d.postId" +

                " join (" +
                " select village1id, name" +
                " from village1" +
                " ) as e" +
                " on a.village1id = e.village1id" +

                " where a.userId = ? and a.status = ?";
        int getSalesByUserId = userId;
        String getSalesByStatus = status;
        return this.jdbcTemplate.query(getSalesQuery, (rs, rowNum) -> new GetSalesRes(
                rs.getInt("postId"),
                rs.getString("title"),
                rs.getInt("price"),
                rs.getString("createdAt"),
                rs.getString("updatedAt"),
                rs.getString("status"),
                rs.getInt("chatroomCount"),
                rs.getInt("interestPostCount"),
                rs.getString("URL"),
                rs.getString("name")),
                new Object[]{getSalesByUserId, getSalesByStatus});
    }

    public List<GetReviewsRes> getReviews(int userId) {
        String getReviewsQuery =
                "select a.postId, a.buyerId, a.content, a.createdAt, b.nickname, d.name" +
                " from review a" +

                " join (" +
                " select userId, nickname" +
                " from user" +
                " ) as b" +
                " on a.buyerId = b.userId" +

                " join (" +
                " select userId, village1Id" +
                " from uservillage" +
                " ) as c" +
                " on a.buyerId = c.userId" +

                " join (" +
                " select village1Id, name" +
                " from village1" +
                " ) as d" +
                " on c.village1Id = d.village1Id" +

                " where a.sellerId = ?";
        int getReviewsByUserId = userId;
        return this.jdbcTemplate.query(getReviewsQuery, (rs, rowNUm) -> new GetReviewsRes(
                rs.getInt("postId"),
                rs.getInt("buyerId"),
                rs.getString("content"),
                rs.getString("createdAt"),
                rs.getString("nickname"),
                rs.getString("name")),
                new Object[]{getReviewsByUserId});
    }

    public List<GetMannersRes> getManners(int userId) {
        String getMannersQuery =
                "select a.userId, c.name" +
                " from user a" +

                " left join (" +
                " select userId, mannerId" +
                " from usermanner" +
                " ) as b" +
                " on a.userId = b.userId" +

                " left join (" +
                " select mannerId, name" +
                " from manner" +
                " ) as c" +
                " on b.mannerId = c.mannerId" +

                " where a.userId = ?";
        int getMannersByUserId = userId;
        return this.jdbcTemplate.query(getMannersQuery, (rs, rowNum) -> new GetMannersRes(
                rs.getInt("userId"),
                rs.getString("name")),
                new Object[]{getMannersByUserId});
    }

    public List<GetBadMannersRes> getBadManners(int userId) {
        String getBadMannersQuery =
                "select a.userId, c.name" +
                " from user a" +

                " left join (" +
                " select userId, badmannerId" +
                " from userbadmanner" +
                " ) as b" +
                " on a.userId = b.userId" +

                " left join (" +
                " select badmannerId, name" +
                " from badmanner" +
                " ) as c" +
                " on b.badmannerId = c.badmannerId" +

                " where a.userId = ?";
        int getBadMannersByUserId = userId;
        return this.jdbcTemplate.query(getBadMannersQuery, (rs, rowNum) -> new GetBadMannersRes(
                rs.getInt("userId"),
                rs.getString("name")),
                new Object[]{getBadMannersByUserId});
    }

    public PostUserRes postUser(PostUserReq postUserReq) {
        String postUserQuery =
                "insert into user" +
                " (nickname, phone, email, URL)" +
                " values" +
                " (?, ?, ?, ?)";
        this.jdbcTemplate.update(postUserQuery,
                new Object[]{postUserReq.getNickname(), postUserReq.getPhone(),
                        postUserReq.getEmail(), postUserReq.getURL()});
        String resultQuery =
                "select last_insert_id() as 'userId'";
        return this.jdbcTemplate.queryForObject(resultQuery, (rs, rowNum) -> new PostUserRes(
                rs.getInt("userId")));
    }

    public GetUserPhoneRes getUserPhone(int userId) {
        String getUserPhoneQuery =
                "select userId, phone" +
                " from user" +
                " where userId = ?";
        return this.jdbcTemplate.queryForObject(getUserPhoneQuery, (rs, rowNum) -> new GetUserPhoneRes(
                rs.getInt("userId"),
                rs.getString("phone")),
                new Object[]{userId});
    }

    public Integer patchUser(int userId, PatchUserReq patchUserReq) {
        String patchUserQuery =
                "update user" +
                " set nickname = ?, URL = ?" +
                " where userId = ?";
        this.jdbcTemplate.update(patchUserQuery,
                new Object[]{patchUserReq.getNickname(), patchUserReq.getURL(), userId});
        int patchPostRes = userId;
        return patchPostRes;
    }

    public Integer deleteReview(int userId, int postId) {
        String deleteReviewQuery =
                "update review" +
                " set status = 'hide'" +
                " where sellerId = ? and postId = ?";
        this.jdbcTemplate.update(deleteReviewQuery, new Object[]{userId, postId});
        int patchPostRes = userId;
        return patchPostRes;
    }

    public Integer postInterestCategory(int userId, int interestcategoryId) {
        String postInterestCategoryQuery =
                "insert into interestpostcategory (userId, postcategoryid)" +
                " values" +
                " (?, ?)";
        this.jdbcTemplate.update(postInterestCategoryQuery, new Object[]{userId, interestcategoryId});
        int postInterestCategoryRes = userId;
        return postInterestCategoryRes;
    }

    public Integer deleteInterestCategory(int userId, int interestcategoryId) {
        String deleteInterestCategoryQuery =
                "delete from interestpostcategory" +
                " where userId = ? and postCategoryId = ?";
        this.jdbcTemplate.update(deleteInterestCategoryQuery, new Object[]{userId, interestcategoryId});
        int deleteInterestCategoryRes = userId;
        return deleteInterestCategoryRes;
    }

    public List<GetInterestCategoryRes> getInterestCategoryList(int userId) {
        String getInterestCategoryListQuery =
                "select a.postCategoryId, b.name" +
                " from interestpostcategory a" +

                " join (" +
                " select postCategoryId, name" +
                " from postcategory" +
                " ) as b" +
                " on a.postCategoryId = b.postCategoryId" +

                " where userId = ?";
        return this.jdbcTemplate.query(getInterestCategoryListQuery, (rs, rowNum) -> new GetInterestCategoryRes(
                rs.getInt("postCategoryId"),
                rs.getString("name")),
                new Object[]{userId});
    }

    public Integer postKeyword(int userId, PostKeywordReq postKeywordReq) {
        String postKeywordQuery =
                "insert into keyword (userId, name)" +
                " values" +
                " (?, ?)";
        this.jdbcTemplate.update(postKeywordQuery, new Object[]{userId, postKeywordReq.getName()});
        int postInterestCategoryRes = userId;
        return postInterestCategoryRes;
    }

    public List<GetKeywordsRes> getKeywords(int userId) {
        String getKeywordsQuery =
                "select keywordId, name" +
                " from keyword" +
                " where userId = ?";
        return this.jdbcTemplate.query(getKeywordsQuery, (rs, rowNum) -> new GetKeywordsRes(
                rs.getInt("keywordId"),
                rs.getString("name")),
                new Object[]{userId});
    }

    public Integer deleteKeyword(int userId, int keywordId) {
        String deleteKeywordQuery =
                "update keyword" +
                " set status = 'delete'" +
                " where userId = ? and keywordId = ?";
        this.jdbcTemplate.update(deleteKeywordQuery, new Object[]{userId, keywordId});
        int postInterestCategoryRes = userId;
        return postInterestCategoryRes;
    }

    public List<GetCommentsRes> getComments(int userId) {
        String getCommentsQuery =
                "select commentId, villageLifePostId, content, createdAt" +
                " from comment" +
                " where userId = ?";
        return this.jdbcTemplate.query(getCommentsQuery, (rs, rowNum) -> new GetCommentsRes(
                rs.getInt("commentId"),
                rs.getInt("villageLifePostId"),
                rs.getString("content"),
                rs.getString("createdAt")),
                new Object[]{userId});
    }
}
