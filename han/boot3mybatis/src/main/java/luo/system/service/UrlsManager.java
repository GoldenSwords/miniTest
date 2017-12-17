package luo.system.service;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface UrlsManager {
    /**
     * 通过权限获取访问资源
     */
    List<?> getUrlsResources(List<GrantedAuthority> roleList);
}
