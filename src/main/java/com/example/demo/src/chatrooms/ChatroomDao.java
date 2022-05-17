package com.example.demo.src.chatrooms;

import com.example.demo.src.chatrooms.model.GetChatroomsRes;

import javax.sql.DataSource;
import java.util.List;

import com.example.demo.src.chatrooms.model.GetChatsRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ChatroomDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource); }

    public List<GetChatroomsRes> getChatrooms(int userId) {
        String getChatroomsQuery =
                "select a.chatroomId, a.postId, a.createdAt, b.nickname, d.name, e.URL" +
                " from chatroom a" +

                " join (" +
                " select userId, nickname" +
                " from user" +
                " ) as b" +
                " on a.buyerId = b.userId" +

                " join (" +
                " select userId, village1id" +
                " from uservillage" +
                " ) as c" +
                " on a.buyerId = c.userId" +

                " join (" +
                " select village1id, name" +
                " from village1" +
                " ) as d" +
                " on c.village1id = d.village1id" +

                " left join (" +
                " select postImageId, postId, URL, representative" +
                " from postimage" +
                " where representative = true" +
                " ) as e" +
                " on a.postId = e.postId" +

                " where a.sellerId = ?";
        int getChatroomsByUserId = userId;
        return this.jdbcTemplate.query(getChatroomsQuery, (rs, rowNum) -> new GetChatroomsRes(
                rs.getInt("chatroomId"),
                rs.getInt("postId"),
                rs.getString("createdAt"),
                rs.getString("nickname"),
                rs.getString("name"),
                rs.getString("URL")),
                new Object[]{getChatroomsByUserId});
    }

    public List<GetChatsRes> getChats(int chatroomId) {
        String getChatsQuery =
                "select a.userId, b.nickname, a.content, a.createdAt" +
                " from chat a" +

                " join (" +
                " select userId, nickname" +
                " from user" +
                " ) as b" +
                " on a.userId = b.userId" +

                " where a.chatroomId = ?";
        return this.jdbcTemplate.query(getChatsQuery, (rs, rowNum) -> new GetChatsRes(
                rs.getInt("userId"),
                rs.getString("nickname"),
                rs.getString("content"),
                rs.getString("createdAt")),
                new Object[]{chatroomId});
    }

    public Integer deleteChats(int chatroomId) {
        String deleteChatsQuery =
                "update chatroom" +
                " set status = 'delete'" +
                " where chatroomId = ?";
        this.jdbcTemplate.update(deleteChatsQuery, new Object[]{chatroomId});
        int deleteChatsRes = chatroomId;
        return deleteChatsRes;
    }
}
