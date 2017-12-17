package luo.system.entity;

import javax.persistence.Column;

public class Roles_Urls {

    @Column
    private Long roleId;
    @Column
    private Long urlId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUrlId() {
        return urlId;
    }

    public void setUrlId(Long urlId) {
        this.urlId = urlId;
    }
}
