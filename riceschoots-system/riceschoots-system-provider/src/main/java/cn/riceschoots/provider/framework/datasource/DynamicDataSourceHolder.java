package cn.riceschoots.provider.framework.datasource;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态数据源持有者
 */
public final class DynamicDataSourceHolder {

    /**
     * 动态数据源存储
     */
    private static final ThreadLocal<String> DYNAMIC_DATA_SOURCE_GLOBAL_THREAD_LOCAL = new ThreadLocal<>();

    public static List<String> dataSourceIds = new ArrayList<>();

    private DynamicDataSourceHolder() {
        //
    }

    /**
     * 存放数据源
     *
     * @param dataSource 数据源
     */
    public static void putDataSource(String dataSource) {
        DYNAMIC_DATA_SOURCE_GLOBAL_THREAD_LOCAL.set(dataSource);
    }

    /**
     * 获取数据源
     *
     * @return the data source
     */
    public static String getDataSource() {
        return DYNAMIC_DATA_SOURCE_GLOBAL_THREAD_LOCAL.get();
    }

    /**
     * 清除数据源
     */
    public static void clearDataSource() {
        DYNAMIC_DATA_SOURCE_GLOBAL_THREAD_LOCAL.remove();
    }

    public static boolean containsDataSource(String dataSourceId){
        return dataSourceIds.contains(dataSourceId);
    }

}