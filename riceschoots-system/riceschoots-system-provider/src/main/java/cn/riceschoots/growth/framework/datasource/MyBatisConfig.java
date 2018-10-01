//package cn.riceschoots.growth.framework.datasource;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.github.pagehelper.PageHelper;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
///**
// * MyBatis数据源集成入口
// */
//@Configuration
//@MapperScan(basePackages = {"cn.riceschoots.growth.*.mapper"})
//@EnableTransactionManagement
//public class MyBatisConfig {
//    /**
//     * 数据源
//     *
//     * @return
//     * @throws Exception
//     */
//    @ConfigurationProperties(prefix = "spring.datasource")
//    @Bean
//    public DataSource dataSource() throws Exception {
//        return new DruidDataSource();
//    }
//
//    /**
//     * 创建SqlSessionFactory
//     *
//     * @param dataSource
//     * @param pageHelper
//     * @throws Exception
//     */
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, PageHelper pageHelper) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        // 分页插件
//        Properties props = new Properties();
//        props.setProperty("dialect", "mysql");
//        props.setProperty("rowBoundsWithCount", "true");
//        props.setProperty("reasonable", "false");
//        props.setProperty("supportMethodsArguments", "true");
//        props.setProperty("returnPageInfo", "check");
//        props.setProperty("params", "count=countSql");
//        pageHelper.setProperties(props);
//        // 添加插件
//        bean.setPlugins(new Interceptor[] {pageHelper});
//        // 数据源
//        bean.setDataSource(dataSource);
//        return bean.getObject();
//    }
//
//    /**
//     * 配置事务管理器
//     *
//     * @param dataSource
//     * @throws Exception
//     */
//    @Bean
//    public DataSourceTransactionManager transectionManager(DataSource dataSource) throws Exception {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    /**
//     * 分页插件配置
//     *
//     * @return
//     */
//    @Bean
//    public PageHelper pageHelper() {
//        PageHelper pageHelper = new PageHelper();
//        Properties p = new Properties();
//        p.setProperty("offsetAsPageNum", "true");
//        p.setProperty("rowBoundsWithCount", "true");
//        p.setProperty("reasonable", "true");
//        p.setProperty("dialect", "mysql");
//        pageHelper.setProperties(p);
//        return pageHelper;
//    }
//
//}
