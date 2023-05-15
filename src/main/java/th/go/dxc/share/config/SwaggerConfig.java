package th.go.dxc.share.config;
import static java.util.Collections.singletonList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.classmate.TypeResolver;

import io.swagger.v3.oas.models.security.SecurityScheme.In;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;



@Configuration
public class SwaggerConfig {
	  private TypeResolver typeResolver;
	  private final ApiInfo apiInfo;

	  
  @Autowired
  public SwaggerConfig(TypeResolver typeResolver, Environment env) {
		super();
		this.typeResolver = typeResolver;
		this.apiInfo = mapApiInfo(env);
	}


  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
    	.apiInfo(apiInfo)
	        .select() 
	        .apis(RequestHandlerSelectors.basePackage("th.go.dxc")) 
	        .paths(PathSelectors.any()) 
	        .build() 
	        .pathMapping("/") 
	.forCodeGeneration(true)
    .directModelSubstitute(LocalDate.class, String.class) 
    .genericModelSubstitutes(ResponseEntity.class)
    .alternateTypeRules(
        newRule(typeResolver.resolve(DeferredResult.class,
            typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
            typeResolver.resolve(WildcardType.class))) 
    .useDefaultResponseMessages(false) 
    .globalResponses(HttpMethod.GET, 
        singletonList(
        	new ResponseBuilder()
            .code("500")
            .description("500 message")
            .build()
        ))
    .securitySchemes(singletonList(apiKey())) 
    .securityContexts(singletonList(securityContext())) 
    .enableUrlTemplating(false); 
//    .globalRequestParameters(
//        singletonList(new springfox.documentation.builders.RequestParameterBuilder()
//            .name("someGlobalParameter")
//            .description("Description of someGlobalParameter")
//                .in(ParameterType.QUERY)
//                .required(true)
//                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
//                .build())); 
  }


	  private ApiInfo mapApiInfo(Environment env)
	  {
		  return new ApiInfoBuilder()
				  .contact(mapContact(env.getProperty("info.contact.name"),env.getProperty("info.contact.email"),env.getProperty("info.contact.url")))
				  .description(env.getProperty("info.app.description"))
				  .license(env.getProperty("info.license.name"))
				  .licenseUrl(env.getProperty("info.license.url"))
				  .termsOfServiceUrl(env.getProperty("info.term-of-service.url"))
				  .title(env.getProperty("info.app.name"))
				  .version(env.getProperty("info.build.version"))
				  .build();
	  }
	  
	  private Contact mapContact(String name,String email,String url) {
		  Contact contact = new Contact(name, url, email);
		  return contact;
	  }
	  
	  private ApiKey apiKey() {
	    return new ApiKey("JWT", HttpHeaders.AUTHORIZATION, In.HEADER.name());
	  }

	  
	  @SuppressWarnings("deprecation")
	private SecurityContext securityContext() {
	    return SecurityContext.builder()
	        .securityReferences(defaultAuth())
	        .forPaths(PathSelectors.any()) 
	        .build();
	  }
	  private List<SecurityReference> defaultAuth() { 
		    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
		    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
		    authorizationScopes[0] = authorizationScope; 
		    return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
		}
//	  List<SecurityReference> defaultAuth() {
//	    AuthorizationScope authorizationScope
//	        = new AuthorizationScope("global", "accessEverything");
//	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//	    authorizationScopes[0] = authorizationScope;
//	    return singletonList(
//	        new SecurityReference("mykey", authorizationScopes)); 
//	  }

	  @Bean
	  SecurityConfiguration security() {
	    return SecurityConfigurationBuilder.builder() 
	        .clientId("test-app-client-id")
	        .clientSecret("test-app-client-secret")
	        .realm("test-app-realm")
	        .appName("test-app")
	        .scopeSeparator(",")
	        .additionalQueryStringParams(null)
	        .useBasicAuthenticationWithAccessCodeGrant(false)
	        .enableCsrfSupport(false)
	        .build();
	  }

	  @Bean
	  UiConfiguration uiConfig() {
	    return UiConfigurationBuilder.builder() 
	        .deepLinking(true)
	        .displayOperationId(true)
	        .defaultModelsExpandDepth(1)
	        .defaultModelExpandDepth(1)
	        .defaultModelRendering(ModelRendering.MODEL)
	        .displayRequestDuration(false)
	        .docExpansion(DocExpansion.NONE)
	        .filter(true)
	        .maxDisplayedTags(null)
	        .operationsSorter(OperationsSorter.ALPHA)
	        .showExtensions(true)
	        .showCommonExtensions(true)
	        .tagsSorter(TagsSorter.ALPHA)
	        .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
	        .validatorUrl(null)
	        .build();
	  }
}
