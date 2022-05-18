package com.example.demo.src.chatrooms;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.chatrooms.model.GetChatsRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatroomService {
    private final ChatroomDao chatroomDao;

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public Integer deleteChats(int userId, int chatroomId) throws BaseException {
        try {
            Integer deleteChatsRes = chatroomDao.deleteChats(userId, chatroomId);
            return deleteChatsRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}
