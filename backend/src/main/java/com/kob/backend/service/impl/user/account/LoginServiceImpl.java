package com.kob.backend.service.impl.user.account;

import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.util.UserDetailsImpl;
import com.kob.backend.service.user.account.LoginService;
import com.kob.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Map<String, String> getToken(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password); // 封装用户名和密码，以密文存储

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);  // // 验证用户是否登录 登录失败，会自动处理；登录成功则会执行下面的代码
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticate.getPrincipal(); // 提取用户
        User user = loginUser.getUser();
        String jwt = JwtUtil.createJWT(user.getId().toString()); // 生成jwt-token

        Map<String, String> map = new HashMap<>();
        map.put("error_message", "success");
        map.put("token", jwt);

        return map;
    }
}
