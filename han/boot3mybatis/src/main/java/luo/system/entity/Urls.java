package luo.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Urls {

    @Id
    Long urlId;
    @Column
    String url;
    @Column
    String urlText;
    @Column
    Long urlPid;
    List<Urls> children;

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

    public List<Urls> getChildren() {
        return children;
    }

    public void setChildren(List<Urls> children) {
        this.children = children;
    }
}
