package com.example.demo.src.chatrooms;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.chatrooms.model.GetChatroomsRes;

import java.util.List;

import com.example.demo.src.chatrooms.model.GetChatsRes;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatroomProvider {
    private final ChatroomDao chatroomDao;

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public List<GetChatroomsRes> getChatrooms(int userId) throws BaseException {
        try {
            List<GetChatroomsRes> getChatroomsRes = chatroomDao.getChatrooms(userId);
            return getChatroomsRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public List<GetChatsRes> getChats(int chatroomId) throws BaseException {
        try {
            List<GetChatsRes> getChatsRes = chatroomDao.getChats(chatroomId);
            return getChatsRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}
