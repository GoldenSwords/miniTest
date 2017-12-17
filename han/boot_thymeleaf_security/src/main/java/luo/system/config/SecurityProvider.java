package luo.system.config;

import luo.system.service.SecurityService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityProvider implements AuthenticationProvider{
    private SecurityService securityService;

    public SecurityProvider(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String username = token.getName();
        //从数据库找到的用户
        UserDetails userDetails = null;
        if(username != null) {
            userDetails = securityService.loadUserByUsername(username);
        }
        if(userDetails == null) {
            System.out.println("用户名/密码无效");
//            throw new UsernameNotFoundException("用户名/密码无效");
        }else if (!userDetails.isEnabled()){
            System.out.println("用户已被禁用");
//            throw new DisabledException("用户已被禁用");
        }else if (!userDetails.isAccountNonExpired()) {
            System.out.println("账号已过期");
//            throw new AccountExpiredException("账号已过期");
        }else if (!userDetails.isAccountNonLocked()) {
            System.out.println("账号已被锁定");
//            throw new LockedException("账号已被锁定");
        }else if (!userDetails.isCredentialsNonExpired()) {
            System.out.println("凭证已过期");
//            throw new LockedException("凭证已过期");
        }
        //数据库用户的密码
        String password = userDetails.getPassword();
        //与authentication里面的credentials相比较
        if(!password.equals(token.getCredentials())) {
            throw new BadCredentialsException("Invalid username/password");
        }
        //授权
        return new UsernamePasswordAuthenticationToken(userDetails, password,userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        //返回true后才会执行上面的authenticate方法,这步能确保authentication能正确转换类型
        return UsernamePasswordAuthenticationToken.class.equals(aClass);
    }
}
