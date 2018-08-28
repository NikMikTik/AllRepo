package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket productApi() {

		return new Docket(DocumentationType.SWAGGER_2)
				.select().apis(RequestHandlerSelectors.basePackage("com"))
				.paths((path()))
				.build()
				.apiInfo(metaInfo());
	}
 
	
	Predicate<String> path() {
		return or(regex("/api/.*"), regex("/Blog/.*"), regex("/Dashboard/.*"), regex("/Login"), regex("/Logout"), regex("/SignUp/.*"), regex("/Welcome"));
	}

	private ApiInfo metaInfo() {

		ApiInfo apiInf = new ApiInfo("BlogSite by Nikita Jain", "Spring Boot - Proof of Concept", "1.0",
				"https://spring.io/projects/spring-boot",
				new Contact("Nikita Jain", "https://github.com/NikMikTik/nikku", "nikita.jain@infogain.com"),
				"Infogain Trainee License 1.0", "https://www.infogain.com");

		return apiInf;

	}

}
