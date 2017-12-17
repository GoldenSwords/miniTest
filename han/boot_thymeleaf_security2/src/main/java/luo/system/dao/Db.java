package luo.system.dao;

import luo.system.entity.UrlEnums;
import luo.system.entity.Urls;

import java.util.ArrayList;
import java.util.List;

public class Db {
    public static List<Urls> getUrls_DBA(){
        List<Urls> result = new ArrayList<>();
        Urls url = new Urls(UrlEnums.Root);
        Urls ua = new Urls(UrlEnums.Child_A);
        Urls ub = new Urls(UrlEnums.Child_B);
        Urls uc = new Urls(UrlEnums.Child_C);
        Urls uca = new Urls(UrlEnums.Children_A);
        Urls ucb = new Urls(UrlEnums.Children_B);
        Urls ucc = new Urls(UrlEnums.Children_C);
        Urls ucd = new Urls(UrlEnums.Children_D);
        Urls uce = new Urls(UrlEnums.Children_E);
        Urls ucf = new Urls(UrlEnums.Children_F);
        Urls ucg = new Urls(UrlEnums.Children_G);
        List<Urls> la = new ArrayList<>();
        la.add(uca);
        la.add(ucb);
        ua.setChildren(la);
        List<Urls> lb = new ArrayList<>();
        lb.add(ucc);
        lb.add(ucd);
        ub.setChildren(lb);
        List<Urls> lc = new ArrayList<>();
        lc.add(uce);
        lc.add(ucf);
        lc.add(ucg);
        uc.setChildren(lc);
        List<Urls> urls = new ArrayList<>();
        urls.add(ua);
        urls.add(ub);
        urls.add(uc);
        url.setChildren(urls);
        result.add(url);
        return result;
    }
    public static List<Urls> getUrls_User(){
        List<Urls> result = new ArrayList<>();
        Urls url = new Urls(UrlEnums.Root);
        Urls ua = new Urls(UrlEnums.Child_A);
        Urls uca = new Urls(UrlEnums.Children_A);
        Urls ucb = new Urls(UrlEnums.Children_B);
        List<Urls> la = new ArrayList<>();
        la.add(uca);
        la.add(ucb);
        ua.setChildren(la);
        List<Urls> urls = new ArrayList<>();
        urls.add(ua);
        url.setChildren(urls);
        result.add(url);
        return result;
    }
}
