package com.wojiushiwo.dal.config;

import com.wojiushiwo.dal.plugins.CustomPlugin2;
import com.wojiushiwo.dal.plugins.CustomPlugin3;
import com.wojiushiwo.dal.plugins.CustomPlugin4;
import com.wojiushiwo.dal.plugins.CustomPlugins;
import com.wojiushiwo.dal.typeHandler.CustomTypeHandler;
import org.apache.ibatis.executor.loader.cglib.CglibProxyFactory;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = "com.wojiushiwo.dal.dao")
@EnableTransactionManagement(proxyTargetClass = true)
public class MyBatisConfig {
    @Autowired
    private DataSource dataSource;

    @Lazy(value = false)
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory localSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //将自定义的typeHandler设置到mybatis配置文件中
        sqlSessionFactoryBean.setTypeHandlers(new TypeHandler[]{new CustomTypeHandler()});
        //将自定义的插件设置到mybatis配置中
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{new CustomPlugin4(),new CustomPlugin3(),new CustomPlugin2(), plugin()});
        SqlSessionFactory factory = sqlSessionFactoryBean.getObject();
        factory.getConfiguration().setLazyLoadingEnabled(true);
        factory.getConfiguration().setAggressiveLazyLoading(false);
        factory.getConfiguration().setProxyFactory(new CglibProxyFactory());
        return factory;
    }

    private Interceptor plugin() {
        CustomPlugins customPlugins = new CustomPlugins();
        Properties properties = new Properties();
        properties.setProperty("name", "wojiushiwo");
        customPlugins.setProperties(properties);
        return customPlugins;
    }

    @Primary
    @Lazy(false)
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(localSessionFactoryBean(), ExecutorType.SIMPLE);
    }

    @Lazy(false)
    @Bean(name = "batchSst")
    public SqlSessionTemplate batchSst() throws Exception {
        return new SqlSessionTemplate(localSessionFactoryBean(), ExecutorType.BATCH);
    }

    @Bean(name = "txManager")
    public DataSourceTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
