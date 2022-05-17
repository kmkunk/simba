package com.example.demo.src.chatrooms;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.chatrooms.model.GetChatroomsRes;

import java.util.List;

import com.example.demo.src.chatrooms.model.GetChatsRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatrooms")
public class ChatroomController {
    private final ChatroomProvider chatroomProvider;
    private final ChatroomService chatroomService;
    private final JwtService jwtService;

    /**
     * 나의 채팅방 리스트 API
     * [GET] /chatrooms/:userId
     * @return BaseResponse<List<GetChatroomsRes>>
     */
    @GetMapping("/{userId}")
    public BaseResponse<List<GetChatroomsRes>> getChatrooms(@PathVariable("userId") int userId) {
        try {
            if(userId<=0) { return new BaseResponse<>(BaseResponseStatus.REQUEST_ERROR); }
            int userIdByJwt = jwtService.getUserIdx();
            if(userId != userIdByJwt) { return new BaseResponse<>(BaseResponseStatus.INVALID_USER_JWT); }

            List<GetChatroomsRes> getChatroomsRes = chatroomProvider.getChatrooms(userId);
            return new BaseResponse<>(getChatroomsRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 채팅방 조회 API
     * [GET] /chatrooms/:userId/:chatroomId
     * @return BaseResponse<List<GetChatsRes>>
     */
    @GetMapping("/{userId}/{chatroomId}")
    public BaseResponse<List<GetChatsRes>> getChats(@PathVariable("userId") int userId,
                                                    @PathVariable("chatroomId") int chatroomId) {
        try {
            if(userId<=0 || chatroomId<=0) { return new BaseResponse<>(BaseResponseStatus.REQUEST_ERROR); }
            int userIdByJwt = jwtService.getUserIdx();
            if(userId != userIdByJwt) { return new BaseResponse<>(BaseResponseStatus.INVALID_USER_JWT); }

            List<GetChatsRes> getChatsRes = chatroomProvider.getChats(chatroomId);
            return new BaseResponse<>(getChatsRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 채팅방 나가기 API
     * [DELETE] /chatrooms/:userId/:chatroomId
     * @return BaseResponse<Integer>
     */
    @DeleteMapping("/{userId}/{chatroomId}")
    public BaseResponse<Integer> deleteChats(@PathVariable("userId") int userId,
                                                    @PathVariable("chatroomId") int chatroomId) {
        try {
            if(userId<=0 || chatroomId<=0) { return new BaseResponse<>(BaseResponseStatus.REQUEST_ERROR); }
            int userIdByJwt = jwtService.getUserIdx();
            if(userId != userIdByJwt) { return new BaseResponse<>(BaseResponseStatus.INVALID_USER_JWT); }

            Integer deleteChatsRes = chatroomService.deleteChats(chatroomId);
            return new BaseResponse<>(deleteChatsRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
