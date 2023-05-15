package th.go.dxc.infra.datasource.data_db.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Configuration
@EnableJpaRepositories(
    basePackages = DataDbDatasourceConfig.BASE_PACKAGE, 
    entityManagerFactoryRef = DataDbDatasourceConfig.DATASOURCE_BEAN_NAME+"EntityManagerFactory", 
    transactionManagerRef = DataDbDatasourceConfig.DATASOURCE_BEAN_NAME+"TransactionManager"
)
public class DataDbDatasourceConfig {
	// Configuration Required
	public final static String APP_ROOT_PACKAGE="th.go.dxc";
	public final static String DATASOURCE_PACKAGE_NAME = "data_db";
	public final static String DATASOURCE_BEAN_NAME = "dataDb55";
	public final static String DATASOURCE_PROPERTIES_NAME = "data-db";
	// Derived values
	public final static String BASE_PACKAGE=APP_ROOT_PACKAGE+".infra.datasource."+DATASOURCE_PACKAGE_NAME;
	public final static String DATASOURCE_PROPERTIES = "infra.datasource."+DATASOURCE_PROPERTIES_NAME+".datasource";
	public final static String JPA_PROPERTIES = "infra.datasource."+DATASOURCE_PROPERTIES_NAME+".jpa";
	
    public DataDbDatasourceConfig() {
		super();
		log.debug(this.getClass().getName());
		log.debug("APP_ROOT_PACKAGE: "+APP_ROOT_PACKAGE);
		log.debug("DATASOURCE_PACKAGE_NAME: "+DATASOURCE_PACKAGE_NAME);
		log.debug("DATASOURCE_BEAN_NAME: "+DATASOURCE_BEAN_NAME);
		log.debug("DATASOURCE_PROPERTIES_NAME: "+DATASOURCE_PROPERTIES_NAME);
		log.debug("BASE_PACKAGE: "+BASE_PACKAGE);
		log.debug("DATASOURCE_PROPERTIES: "+DATASOURCE_PROPERTIES);
		log.debug("JPA_PROPERTIES: "+JPA_PROPERTIES);
	}

	@Bean(name=DATASOURCE_BEAN_NAME+"DataSourceProperties")
    @ConfigurationProperties(DATASOURCE_PROPERTIES)
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name=DATASOURCE_BEAN_NAME+"HikariDataSource")
    @ConfigurationProperties(DATASOURCE_PROPERTIES+".hikari")
    public HikariDataSource hikariDataSource() {
        return dataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name=DATASOURCE_BEAN_NAME+"JpaProperties")
    @ConfigurationProperties(JPA_PROPERTIES)
    public JpaProperties jpaProperties() {
    	return new JpaProperties();
    }
    
    @Bean(name=DATASOURCE_BEAN_NAME+"EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
    		EntityManagerFactoryBuilder builder,
            @Qualifier(DATASOURCE_BEAN_NAME+"HikariDataSource") HikariDataSource dataSource,
            @Qualifier(DATASOURCE_BEAN_NAME+"JpaProperties")  JpaProperties jpaProperties) {
    	log.info("Initi EntityManagerFactory");
    	log.info("HikariDataSource "+dataSource);
    	log.info("JPA DatabasePlatform: "+(jpaProperties==null?null:jpaProperties.getDatabasePlatform()));
    	log.info("JPA Properties: "+(jpaProperties==null?null:jpaProperties.getProperties()));
    	LocalContainerEntityManagerFactoryBean emf = builder
                .dataSource(dataSource)
                .packages(BASE_PACKAGE)
                .persistenceUnit(DATASOURCE_BEAN_NAME+"PersistenceUnit")
                .properties(jpaProperties.getProperties())
                .build();
      return emf;
    }
    
    @Bean(name=DATASOURCE_BEAN_NAME+"TransactionManager")
    public PlatformTransactionManager transactionManager(
			ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers
			,  @Qualifier(DATASOURCE_BEAN_NAME+"EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory);
		transactionManagerCustomizers.ifAvailable((customizers) -> customizers.customize(transactionManager));
		return transactionManager;
	}
    
    @Bean(name=DATASOURCE_BEAN_NAME+"JpaVendorAdapter")
    public JpaVendorAdapter jpaVendorAdapter(
            @Qualifier(DATASOURCE_BEAN_NAME+"JpaProperties")  JpaProperties jpaProperties    		
    		) {
		AbstractJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setShowSql(jpaProperties.isShowSql());
		if (jpaProperties.getDatabase() != null) {
			adapter.setDatabase(jpaProperties.getDatabase());
		}
		if (jpaProperties.getDatabasePlatform() != null) {
			adapter.setDatabasePlatform(jpaProperties.getDatabasePlatform());
		}
		adapter.setGenerateDdl(jpaProperties.isGenerateDdl());
		return adapter;
	}
}
