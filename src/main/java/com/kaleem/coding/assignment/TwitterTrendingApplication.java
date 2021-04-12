package com.kaleem.coding.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableScheduling
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableJpaRepositories(basePackages = "com.kaleem.coding.assignment")
@EntityScan("com.kaleem.coding.assignment.entity")
@ComponentScan({ "com.kaleem.coding.assignment" })
public class TwitterTrendingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterTrendingApplication.class, args);
	}

	@Bean
	public Docket swaggerApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.kaleem.coding.assignment")).paths(PathSelectors.any())
				.build().apiInfo(new ApiInfoBuilder().version("1.0").title("Twitter Trending Hashtags")
						.description("Documentation Twitter Trending Hashtags API v1.0").build());
	}

}
