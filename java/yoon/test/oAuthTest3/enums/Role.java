package yoon.test.oAuthTest3.enums;

import lombok.Getter;

@Getter
public enum Role {

    GUEST(1, "ROLE_GUEST"),
    USER(2, "ROLE_USER"),
    ADMIN(3, "ROLE_ADMIN");

    private final int value;
    private final String key;

    Role(int value, String key){
        this.value = value;
        this.key = key;
    }


}
