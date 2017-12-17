package luo.system.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SecurityService implements UserDetailsService{
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities=new ArrayList<>();
        User user = null;
        if("admin".equals(username)){
            List<String> roleList = getRoles(username);
            for (String roleName: roleList) {
                authorities.add(new SimpleGrantedAuthority("ROLE"+roleName));
            }
            user = new User("admin","admin",true,true,true,true,authorities);
        }else{
            user = new User("admin","admin",authorities);
        }
        return user;
    }
    private List<String> getRoles(String username){
        List<String> list = new ArrayList<>();
        list.add("DBA");
        list.add("USER");
        list.add("ADMIN");
        list.add("SUPER");
        return list;
    }
}
