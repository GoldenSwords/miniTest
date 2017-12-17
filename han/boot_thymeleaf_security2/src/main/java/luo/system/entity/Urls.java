package luo.system.entity;

import java.util.List;

public class Urls {
    Long urlId;
    String url;
    String urlText;
    Long urlPid;
    List<Urls> children;

    public List<Urls> getChildren() {
        return children;
    }

    public void setChildren(List<Urls> children) {
        this.children = children;
    }

    public Urls(UrlEnums urlEnums) {
        this.urlId = urlEnums.getId();
        this.url = urlEnums.getUrl();
        this.urlText = urlEnums.getText();
        this.urlPid = urlEnums.getPid();
    }

    public Long getUrlId() {
        return urlId;
    }

    public void setUrlId(Long urlId) {
        this.urlId = urlId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlText() {
        return urlText;
    }

    public void setUrlText(String urlText) {
        this.urlText = urlText;
    }

    public Long getUrlPid() {
        return urlPid;
    }

    public void setUrlPid(Long urlPid) {
        this.urlPid = urlPid;
    }
}
