package cn.riceschoots.api.framework.datasource;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Component
@Configuration
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceRegister.class);

    private Map<String, DruidDataSource> customDataSources = new HashMap<>();

    @Primary
    @Bean(name = "semWriteDataSource")
    @ConfigurationProperties("spring.datasource.write")
    public DruidDataSource defaultDataSource() {
        return DruidDataSourceBuilder.create().build();
    }


    /**
     * Read data source druid data source.
     *
     * @return the druid data source
     */
    @Bean(name = "semReadDataSource")
    @ConfigurationProperties("spring.datasource.read")
    public DruidDataSource readDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * growth数据源
     * @return
     */
    @Bean(name = "growthDataSource")
    @ConfigurationProperties("spring.datasource.growth")
    public DruidDataSource growthDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * Dynamic data source data source.
     *
     * @return the data source
     */
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setSemWriteDataSource(defaultDataSource());
        dynamicDataSource.setSemReadDataSource(readDataSource());
        dynamicDataSource.setGrowthDataSource(growthDataSource());
        return dynamicDataSource;
    }



    /**
     * 加载多数据源配置
     */
    @Override
    public void setEnvironment(Environment env) {
        initCustomDataSources(env);
    }

    /**
     * 初始化更多数据源
     */
    private void initCustomDataSources(Environment env) {
        // 读取配置文件获取更多数据源，也可以通过defaultDataSource读取数据库获取更多数据源
        //Spring Boot 2.x
        String dsPrefixs = env.getProperty("spring.datasource.names");

        //Spring Boot 1.x
        //RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
        //String dsPrefixs = propertyResolver.getProperty("names");

        // 多个数据源
        for (String dsPrefix : dsPrefixs.split(",")) {
            DruidDataSource ds = null;
            dsPrefix = dsPrefix.replaceAll("-", "_");
            if(DynamicDataSourceGlobal.read.name().equals(dsPrefix)){
                ds = readDataSource();
            }else if(DynamicDataSourceGlobal.growth.equals(dsPrefix)){
                ds = growthDataSource();
            }
            customDataSources.put(dsPrefix, ds);
        }
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        // 将主数据源添加到更多数据源中
        DynamicDataSourceHolder.dataSourceIds.add(DynamicDataSourceGlobal.write.name());
        // 添加更多数据源
        for (String key : customDataSources.keySet()) {
            DynamicDataSourceHolder.dataSourceIds.add(key);
        }
        logger.info("Dynamic DataSource Registry");
    }
//
//    @Bean
//    @ConfigurationProperties(prefix = MybatisProperties.MYBATIS_PREFIX)
//    public MybatisProperties initMybatisProperties(){
//        return new MybatisProperties();
//    }
    /**
     * Dynamic sql session factory sql session factory.
     *
     * @param dynamicDataSource the dynamic data source
     * @param properties        the properties
     * @return the sql session factory
     */
    @Bean
    @ConfigurationProperties(prefix = MybatisProperties.MYBATIS_PREFIX)
    public SqlSessionFactory dynamicSqlSessionFactory(
            @Qualifier("dynamicDataSource") DataSource dynamicDataSource,
            MybatisProperties properties) {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dynamicDataSource);
        sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(properties.getConfigLocation()));
        sessionFactory.setMapperLocations(properties.resolveMapperLocations());
        try {
            return sessionFactory.getObject();
        } catch (Exception e) {
            throw new SysException(e);
        }
    }

    /**
     * 配置事务管理器
     */
    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) throws Exception {
        return new DataSourceTransactionManager(dynamicDataSource);
    }

}
