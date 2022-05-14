package com.example.demo.src.users.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetEstimationsRes {
    List<GetMannersRes> getMannersRes;
    List<GetBadMannersRes> getBadMannersRes;
}
