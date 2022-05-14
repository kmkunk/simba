package com.example.demo.src.users;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.users.model.*;

import java.util.List;

import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserProvider userProvider;
    private final UserService userService;
    private final JwtService jwtService;

    /**
     * 나의 당근 조회 API
     * [GET] /users/:userId
     * @return BaseResponse<GetUserRes>
     */
    @GetMapping("/{userId}")
    public BaseResponse<GetUserRes> getUser(@PathVariable("userId") int userId) {
        try {
            if(userId<=0) { return new BaseResponse<>(BaseResponseStatus.REQUEST_ERROR); }
            int userIdByJwt = jwtService.getUserIdx();
            if(userId != userIdByJwt) { return new BaseResponse<>(BaseResponseStatus.INVALID_USER_JWT); }

            GetUserRes getUserRes = userProvider.getUser(userId);
            return new BaseResponse<>(getUserRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 나의 활동 배지 조회 API
     * [GET] /users/:userId/badges
     * @return BaseResponse<List<GetBadgesRes>>
     */
    @GetMapping("/{userId}/badges")
    public BaseResponse<List<GetBadgesRes>> getBadges(@PathVariable("userId") int userId) {
        try {
            if(userId<=0) { return new BaseResponse<>(BaseResponseStatus.REQUEST_ERROR); }
            int userIdByJwt = jwtService.getUserIdx();
            if(userId != userIdByJwt) { return new BaseResponse<>(BaseResponseStatus.INVALID_USER_JWT); }

            List<GetBadgesRes> getBadgesRes = userProvider.getBadges(userId);
            return new BaseResponse<>(getBadgesRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 나의 판매 내역 조회 API
     * [GET] /users/:userId/sales?status=
     * @return BaseResponse<List<GetSalesRes>>
     */
    @GetMapping("/{userId}/sales")
    public BaseResponse<List<GetSalesRes>> getSales(@PathVariable("userId") int userId, @RequestParam String status) {
        try {
            if(userId<=0) { return new BaseResponse<>(BaseResponseStatus.REQUEST_ERROR); }
            if(!status.equals("posting")&&!status.equals("completed")) {
                return new BaseResponse<>(BaseResponseStatus.REQUEST_ERROR);
            }

            int userIdByJwt = jwtService.getUserIdx();
            if(userId != userIdByJwt) { return new BaseResponse<>(BaseResponseStatus.INVALID_USER_JWT); }

            List<GetSalesRes> getSalesRes = userProvider.getSales(userId, status);
            return new BaseResponse<>(getSalesRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 나의 거래 후기 조회 API
     * [GET] /users/:userId/reviews
     * @return BaseResponse<List<GetReviewsRes>>
     */
    @GetMapping("/{userId}/reviews")
    public BaseResponse<List<GetReviewsRes>> getReviews(@PathVariable("userId") int userId) {
        try {
            if(userId<=0) { return new BaseResponse<>(BaseResponseStatus.REQUEST_ERROR); }
            int userIdByJwt = jwtService.getUserIdx();
            if(userId != userIdByJwt) { return new BaseResponse<>(BaseResponseStatus.INVALID_USER_JWT); }

            List<GetReviewsRes> getReviewsRes = userProvider.getReviews(userId);
            return new BaseResponse<>(getReviewsRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 나의 받은 매너 칭찬 조회 API
     * [GET] /users/:userId/manners
     * @return BaseResponse<List<GetMannersRes>>
     */
    @GetMapping("/{userId}/manners")
    public BaseResponse<List<GetMannersRes>> getManners(@PathVariable("userId") int userId) {
        try {
            List<GetMannersRes> getMannersRes = userProvider.getManners(userId);
            return new BaseResponse<>(getMannersRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 나의 받은 매너 칭찬 조회 API
     * [GET] /users/:userId/badmanners
     * @return BaseResponse<List<GetBadMannersRes>>
     */
    @GetMapping("/{userId}/badmanners")
    public BaseResponse<List<GetBadMannersRes>> getBadManners(@PathVariable("userId") int userId) {
        try {
            List<GetBadMannersRes> getBadMannersRes = userProvider.getBadManners(userId);
            return new BaseResponse<>(getBadMannersRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 나의 받은 평가 조회 API
     * [GET] /users/:userId/estimations
     * @return BaseResponse<GetEstimationsRes>
     */
    @GetMapping("/{userId}/estimations")
    public BaseResponse<GetEstimationsRes> getEstimations(@PathVariable("userId") int userId) {
        try {
            if(userId<=0) { return new BaseResponse<>(BaseResponseStatus.REQUEST_ERROR); }
            int userIdByJwt = jwtService.getUserIdx();
            if(userId != userIdByJwt) { return new BaseResponse<>(BaseResponseStatus.INVALID_USER_JWT); }

            GetEstimationsRes getEstimationsRes = userProvider.getEstimations(userId);
            return new BaseResponse<>(getEstimationsRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 회원가입 API
     * [POST] /users
     * @return BaseResponse<PostUserRes>
     */
    @PostMapping
    public BaseResponse<PostUserRes> postUser(@RequestBody PostUserReq postUserReq) {
        try {
            PostUserRes postUserRes = userService.postUser(postUserReq);
            return new BaseResponse<>(postUserRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 로그인 API
     * [POST] /users/login
     * @return BaseResponse<PostLoginRes>
     */
    @PostMapping("/logIn")
    public BaseResponse<PostLoginRes> postLogin(@RequestBody PostLoginReq postLoginReq) {
        try {
            PostLoginRes postLoginRes = userService.postLogin(postLoginReq);
            return new BaseResponse<>(postLoginRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
