<<<<<<< HEAD
package yoon.test.oAuthTest3.vo.response;

import lombok.Builder;
import lombok.Getter;
import yoon.test.oAuthTest3.enums.Provider;

import java.util.Map;

@Getter
public class OAuth2Attribute {

    private String email;

    private String name;

    private Provider provider;

    private String attributeKey;

    private Map<String, Object> attributes;

    @Builder
    OAuth2Attribute(String email, String name, Provider provider, String attributeKey, Map<String, Object> attributes){
        this.email = email;
        this.name = name;
        this.provider = provider;
        this.attributeKey = attributeKey;
        this.attributes = attributes;
    }

}
=======
package yoon.test.oAuthTest3.vo.response;

import lombok.Builder;
import lombok.Getter;
import yoon.test.oAuthTest3.enums.Provider;

import java.util.Map;

@Getter
public class OAuth2Attribute {

    private String email;

    private String name;

    private Provider provider;

    private String attributeKey;

    private Map<String, Object> attributes;

    @Builder
    OAuth2Attribute(String email, String name, Provider provider, String attributeKey, Map<String, Object> attributes){
        this.email = email;
        this.name = name;
        this.provider = provider;
        this.attributeKey = attributeKey;
        this.attributes = attributes;
    }

}
>>>>>>> YoonSeungKwon/master
