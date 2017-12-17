package luo.system.service.impl;

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
        List<String> roleList = getRoles(username);
        for (String roleName: roleList) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+roleName));
        }
        return new User(username,"admin",true,true,true,true,authorities);
    }
    private List<String> getRoles(String username){
        List<String> list = new ArrayList<>();
        if(username.equals("admin")){
            list.add("ADMIN");
            list.add("ADD");
            list.add("UPDATE");
            list.add("DELETE");
            list.add("GET");
        }else{
            list.add("GET");
        }
        return list;
    }
}
