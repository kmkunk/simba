package com.example.demo.src.chatrooms;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.chatrooms.model.GetChatroomsRes;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatroomProvider {
    private final ChatroomDao chatroomDao;

    public List<GetChatroomsRes> getChatrooms(int userId) throws BaseException {
        try {
            List<GetChatroomsRes> getChatroomsRes = chatroomDao.getChatrooms(userId);
            return getChatroomsRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}
