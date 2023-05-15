package th.go.dxc.infra.datasource.primary.config;

import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(
    basePackages = PrimaryDatasourceConfig.BASE_PACKAGE, 
    entityManagerFactoryRef = PrimaryDatasourceConfig.DATASOURCE_BEAN_NAME+"EntityManagerFactory", 
    transactionManagerRef = PrimaryDatasourceConfig.DATASOURCE_BEAN_NAME+"TransactionManager"
)
public class PrimaryDatasourceConfig {
	private final static Logger LOGGER = LoggerFactory.getLogger(PrimaryDatasourceConfig.class);
	// Configuration Required
	public final static String APP_ROOT_PACKAGE= "th.go.dxc";
	public final static String DATASOURCE_PACKAGE_NAME = "primary";
	public final static String DATASOURCE_BEAN_NAME = "primary";
	public final static String DATASOURCE_PROPERTIES_NAME = "primary";
	// Derived values
	public final static String BASE_PACKAGE=APP_ROOT_PACKAGE+".infra.datasource."+DATASOURCE_PACKAGE_NAME;
	public final static String DATASOURCE_PROPERTIES = "infra.datasource."+DATASOURCE_PROPERTIES_NAME+".datasource";
	public final static String JPA_PROPERTIES = "infra.datasource."+DATASOURCE_PROPERTIES_NAME+".jpa";
	
    @Primary
    @Bean(name=DATASOURCE_BEAN_NAME+"DataSourceProperties")
    @ConfigurationProperties(DATASOURCE_PROPERTIES)
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name=DATASOURCE_BEAN_NAME+"HikariDataSource")
    @ConfigurationProperties(DATASOURCE_PROPERTIES+".hikari")
    public HikariDataSource hikariDataSource() {
        return dataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name=DATASOURCE_BEAN_NAME+"JpaProperties")
    @ConfigurationProperties(JPA_PROPERTIES)
    public JpaProperties jpaProperties() {
    	return new JpaProperties();
    }
    
    @Primary
    @Bean(name=DATASOURCE_BEAN_NAME+"EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
    		EntityManagerFactoryBuilder builder,
            @Qualifier(DATASOURCE_BEAN_NAME+"HikariDataSource") HikariDataSource dataSource,
            @Qualifier(DATASOURCE_BEAN_NAME+"JpaProperties")  JpaProperties jpaProperties) {
    	LOGGER.info("Initi EntityManagerFactory");
    	LOGGER.info("HikariDataSource "+dataSource);
    	LOGGER.info("JPA Properties: "+(jpaProperties==null?null:jpaProperties.getProperties()));
    	LocalContainerEntityManagerFactoryBean emf = builder
                .dataSource(dataSource)
                .packages(BASE_PACKAGE)
                .persistenceUnit(DATASOURCE_BEAN_NAME+"PersistenceUnit")
                .properties(jpaProperties.getProperties())
                .build();
      return emf;
    }
    
    @Primary
    @Bean(name=DATASOURCE_BEAN_NAME+"TransactionManager")
    public PlatformTransactionManager transactionManager(
			ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers
			,  @Qualifier(DATASOURCE_BEAN_NAME+"EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory);
		transactionManagerCustomizers.ifAvailable((customizers) -> customizers.customize(transactionManager));
		return transactionManager;
	}
    
    @Primary
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
