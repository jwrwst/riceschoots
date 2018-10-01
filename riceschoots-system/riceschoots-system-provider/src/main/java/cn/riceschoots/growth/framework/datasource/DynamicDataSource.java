package cn.riceschoots.growth.framework.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源实现读写分离
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 写数据源
     */
    private Object semWriteDataSource;
    /**
     * 读数据源
     */
    private Object semReadDataSource;

    /**
     * 配置数据源
     */
    private Object growthDataSource;

    @Override
    public void afterPropertiesSet() {
        if (this.semWriteDataSource == null) {
            throw new IllegalArgumentException("Property 'writeDataSource' is required");
        }
        setDefaultTargetDataSource(semWriteDataSource);
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DynamicDataSourceGlobal.write.name(), semWriteDataSource);
        if (semReadDataSource != null) {
            targetDataSources.put(DynamicDataSourceGlobal.read.name(), semReadDataSource);
        }
        if(growthDataSource != null){
            targetDataSources.put(DynamicDataSourceGlobal.growth.name(), growthDataSource);
        }
        setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String dynamicDataSourceGlobal = DynamicDataSourceHolder.getDataSource();

        if(StringUtils.hasText(dynamicDataSourceGlobal)){
            if(DynamicDataSourceGlobal.read.name().equals(dynamicDataSourceGlobal)){
                return DynamicDataSourceGlobal.read.name();
            }else{
                return DynamicDataSourceGlobal.growth.name();
            }
        }else{
            return DynamicDataSourceGlobal.write.name();
        }
    }

    public Object getSemWriteDataSource() {
        return semWriteDataSource;
    }

    public void setSemWriteDataSource(Object semWriteDataSource) {
        this.semWriteDataSource = semWriteDataSource;
    }

    public Object getSemReadDataSource() {
        return semReadDataSource;
    }

    public void setSemReadDataSource(Object semReadDataSource) {
        this.semReadDataSource = semReadDataSource;
    }

    public Object getGrowthDataSource() {
        return growthDataSource;
    }

    public void setGrowthDataSource(Object growthDataSource) {
        this.growthDataSource = growthDataSource;
    }
}