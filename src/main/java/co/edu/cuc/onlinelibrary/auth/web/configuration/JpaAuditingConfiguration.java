package co.edu.cuc.onlinelibrary.auth.web.configuration;

import co.edu.cuc.onlinelibrary.auth.domain.service.impl.AuditorAwareService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {

    @Bean
    public AuditorAware<Integer> auditorProvider() {
        return new AuditorAwareService();
    }
}
