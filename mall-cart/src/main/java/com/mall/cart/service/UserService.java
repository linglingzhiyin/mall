package com.mall.cart.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.cart.pojo.User;
import com.mall.common.service.ApiService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Value("${MALL_SSO_URL}")
    public String MALL_SSO_URL;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private ApiService apiService;


//    @Autowired
//    private UserQueryService userQueryService;
//

    /**
     * 根据token查询用户信息
     *
     * @param token
     * @return
     */
    public User queryByToken(String token) {
        try {
            String url = MALL_SSO_URL+"/service/user/"+token;
            String jsonData = this.apiService.doGet(url);
            if (StringUtils.isNotEmpty(jsonData)) {
                return MAPPER.readValue(jsonData, User.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
//        User user = userQueryService.queryUserByToken(token);
//        if (null == user) {
//            System.out.println("这是空的。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
//        } else {
//            System.out.println(user.toString());
//        }
//        return user;
    }

}
