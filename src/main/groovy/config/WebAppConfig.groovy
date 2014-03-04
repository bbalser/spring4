package config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.servlet.config.annotation.EnableWebMvc

import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableWebMvc
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(basePackages = "spring4")
class WebAppConfig {

    @Bean
    DataSource dataSource() {
        new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build()
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean()
        bean.setDataSource(dataSource)
        bean.setJpaVendorAdapter(jpaVendorAdapter)
        bean.setPackagesToScan("spring4.domain")
        bean
    }

    @Bean
    JpaVendorAdapter jpaVendorAdapter() {
        new HibernateJpaVendorAdapter(showSql: true, generateDdl: true, database: Database.H2)
    }

    @Bean
    PlatformTransactionManager transactionManager(DataSource dataSource, EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setDataSource(dataSource)
        txManager.setEntityManagerFactory(entityManagerFactory)
        txManager
    }

}
