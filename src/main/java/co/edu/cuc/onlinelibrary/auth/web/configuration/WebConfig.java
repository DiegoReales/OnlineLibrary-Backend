package co.edu.cuc.onlinelibrary.auth.web.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	private static final String DIVIDER = "\\|";
	
	@Value("${auth.cors.allowed-origins}")
    private String origins;
	
	@Value("${auth.cors.allowed-headers}")
    private String headers;
	
	@Value("${auth.cors.allowed-methods}")
    private String methods;
	

	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
				.allowedOriginPatterns(this.origins.split(DIVIDER))
				.allowedHeaders(this.headers.split(DIVIDER))
				.allowedMethods(this.methods.split(DIVIDER))
				.allowCredentials(true);
    }

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer contentNegotiationConfigurer) {
		contentNegotiationConfigurer.defaultContentType(MediaType.APPLICATION_JSON);
	}
}
