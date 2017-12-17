package luo.system.datasource;

public class DataSourceHolder {
    //线程本地环境
    private static final ThreadLocal<DatabaseType> dataSourcesHolder = new ThreadLocal<>();
    //设置数据源
    public static void setDataSource(DatabaseType customerType) {
        dataSourcesHolder.set(customerType);
    }
    //获取数据源
    public static DatabaseType getDataSource() {
        return dataSourcesHolder.get();
    }
    //清除数据源
    public static void clearDataSource() {
        dataSourcesHolder.remove();
    }
}
