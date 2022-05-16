package com.example.demo.src.chatrooms;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.chatrooms.model.GetChatroomsRes;

import java.util.List;

import com.example.demo.src.chatrooms.model.GetChatsRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatrooms")
public class ChatroomController {
    private final ChatroomProvider chatroomProvider;
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

}
