package luo.system.service.impl;

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
        List<Urls> list = new ArrayList<>();
        Urls urls = new Urls();
        urls.setUrl("http://www.baidu.com");
        urls.setUrlId(1l);
        urls.setUrlPid(0l);
        urls.setUrlText("百度");
        list.add(urls);
        for (GrantedAuthority grantedAuthority:roleList) {
            if("ROLE_ADMIN".equals(grantedAuthority.getAuthority())){
                urlsList.addAll(list);
            }else if("ROLE_USER".equals(grantedAuthority.getAuthority())){
                urlsList.addAll(list);
            }
        }
        return urlsList;
    }
}
