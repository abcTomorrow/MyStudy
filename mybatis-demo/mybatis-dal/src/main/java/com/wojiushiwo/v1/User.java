package com.wojiushiwo.v1;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    private String id;
    private String userName;
    private String password;
}
