package th.go.dxc.share.config;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.extern.slf4j.Slf4j;
import th.go.dxc.infra.datasource.dxc_sam.repository.ViewGroupDataAclRepository;
import th.go.dxc.infra.datasource.dxc_sam.repository.ViewUserDataAclRepository;
import th.go.dxc.share.security.service.SecurityService;
import th.go.dxc.share.security.service.SecurityServiceDxcDbImpl;
import th.go.dxc.share.security.service.SecurityServiceMockImpl;

//@Profile({"!dev"})
@Configuration
@EnableWebSecurity(debug=false)
public class SecurityConfig {
	public static final String HEADER_USER_NIN = "X-User-Nin";
	
	@Slf4j
	@Configuration
	@Order(1)
	public static class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
		
		@Override
		public void configure(WebSecurity web) throws Exception {
//			web.ignoring().antMatchers("/services/**/v2/api-docs");
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			log.debug("configure ApiSecurity");
			http = http.cors().and().csrf().disable();
			http = http.requestMatchers().antMatchers("/services/**","/api/**").and();
			http = http.authorizeRequests().antMatchers("/services/**/v2/api-docs").permitAll().and();
			String apiPath = "/api/**";
//			String access = "hasAuthority('SCOPE_nin)";
//			log.debug("apiPath: "+apiPath+", access: "+access);
//			http = http.authorizeRequests().antMatchers(apiPath).access(access).and();
			log.debug("apiPath: "+apiPath+" permitAll");
			http = http.authorizeRequests().antMatchers(apiPath).permitAll().and();
			http
			.oauth2ResourceServer()
					.jwt();
		}
	}
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        //https://www.icode9.com/content-4-906426.html
        configuration.setAllowCredentials(false);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

	@Bean
	@ConditionalOnProperty(name="app.security.mock", havingValue="false")
	public SecurityService securityService(ViewGroupDataAclRepository groupDataAclRepository,
			ViewUserDataAclRepository userDataAclRepository) {
		return new SecurityServiceDxcDbImpl(groupDataAclRepository, userDataAclRepository);
	}
	@Bean
	@ConditionalOnProperty(name="app.security.mock", havingValue="true")
	public SecurityService mockSecurityService() {
		return new SecurityServiceMockImpl();
	}
}
