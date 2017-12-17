package luo.system.entity;

public enum UrlEnums {
    Root(0l,1l,"http://www.baidu.com","根节点"),
    Child_A(1l,0l,"http://www.baidu.com","子A节点"),
    Child_B(2l,0l,"http://www.baidu.com","子B节点"),
    Child_C(3l,0l,"http://www.baidu.com","子C节点"),
    Children_A(4l,1l,"http://www.baidu.com","A节点"),
    Children_B(5l,1l,"http://www.baidu.com","B节点"),
    Children_C(6l,2l,"http://www.baidu.com","C节点"),
    Children_D(7l,2l,"http://www.baidu.com","D节点"),
    Children_E(8l,3l,"http://www.baidu.com","E节点"),
    Children_F(9l,3l,"http://www.baidu.com","F节点"),
    Children_G(10l,3l,"http://www.baidu.com","G节点");
    Long id;
    Long pid;
    String url;
    String text;
    UrlEnums(Long id,Long pid,String url,String text){
        this.id=id;
        this.pid=pid;
        this.url=url;
        this.text=text;
    }

    public Long getId() {
        return id;
    }

    public Long getPid() {
        return pid;
    }

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }
}
