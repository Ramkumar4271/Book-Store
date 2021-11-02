package com.ram.configuration;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomWebPaginationConfig implements WebMvcConfigurer{

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {

		PageableHandlerMethodArgumentResolver pageResolver = new PageableHandlerMethodArgumentResolver();
		pageResolver.setPageParameterName("page-number");
		pageResolver.setSizeParameterName("page-size");
		pageResolver.setOneIndexedParameters(true);
		pageResolver.setFallbackPageable(PageRequest.of(0, 5));
		resolvers.add(pageResolver);
	}
	
}
