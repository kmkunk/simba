package com.example.demo.src.users.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetBadgesRes {
    private int badgeId;
    private String name;
    private boolean gold;
    private boolean representative;
}
