package luo.system.service.impl;

import luo.system.dao.Db;
import luo.system.entity.Urls;
import luo.system.service.UrlsManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UrlsService implements UrlsManager{
    @Override
    public List<?> getUrlsResources(List<GrantedAuthority> roleList) {
        List<Urls> urlsList = new ArrayList<>();
        for (GrantedAuthority grantedAuthority:roleList) {
            if("ROLE_ADMIN".equals(grantedAuthority.getAuthority())){
                urlsList.addAll(Db.getUrls_DBA());
            }else if("ROLE_USER".equals(grantedAuthority.getAuthority())){
                urlsList.addAll(Db.getUrls_User());
            }
        }
        return urlsList;
    }
}
