package luo.system.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;

public class Urls {

    @GeneratedValue
    @Column
    private Long id;
    @Column
    private Long pid;
    @Column
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
