package com.example.springboot.service;

import com.example.springboot.entity.User;
import com.example.springboot.request.LoginRequest;
import com.example.springboot.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 用户登录认证
     *
     * @param userLogin 用户登录信息
     */
    public User authLogin(LoginRequest userLogin) {
        String email = userLogin.getEmail();
        String password = userLogin.getPassword();

        // 根据登录名获取用户信息
        //Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
        Optional<User> userOptional = userService.findByEmail(email);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("User not found with userName: " + email);
        }
        User user = userOptional.get();
        // 验证登录密码是否正确。如果正确，则赋予用户相应权限并生成用户认证信息
        if (this.bCryptPasswordEncoder.matches(password, user.getPassword())) {
//            List<String> roles = userService.listUserRoles(userName);
            // 如果用户角色为空，则默认赋予 ROLE_USER 角色
//            if (CollectionUtils.isEmpty(roles)) {
//                roles = Collections.singletonList(UserRoleConstants.ROLE_USER);
//            }
            // 生成 token
            String token = JwtUtils.generateToken(email, false);

            // 认证成功后，设置认证信息到 Spring Security 上下文中
            Authentication authentication = JwtUtils.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 用户信息
//            UserDTO userDTO = new UserDTO();
//            userDTO.setUserName(userName);
//            userDTO.setEmail(user.getEmail());
//            userDTO.setRoles(roles);
//
            user.setEmail(email);
            user.setToken(token);




            return user;
        }
        //打斷
        throw new BadCredentialsException("The user name or password error.");
    }

    /**
     * 用户退出登录
     *
     * <p>
     * 清除 Spring Security 上下文中的认证信息
     */
    public void logout() {
        SecurityContextHolder.clearContext();
    }
}
