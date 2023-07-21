<<<<<<< HEAD
package yoon.test.oAuthTest3.enums;

import lombok.Getter;

@Getter
public enum Provider {

    Google(1, "Google"),
    Naver(2, "Naver"),
    Kakao(3, "Kakao");

    private final int key;
    private final String value;

    Provider(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
=======
package yoon.test.oAuthTest3.enums;

import lombok.Getter;

@Getter
public enum Provider {

    Google(1, "Google"),
    Naver(2, "Naver"),
    Kakao(3, "Kakao");

    private final int key;
    private final String value;

    Provider(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
>>>>>>> YoonSeungKwon/master
