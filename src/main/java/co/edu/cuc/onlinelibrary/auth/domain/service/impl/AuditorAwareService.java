package co.edu.cuc.onlinelibrary.auth.domain.service.impl;

import co.edu.cuc.onlinelibrary.auth.domain.dto.AuthDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareService implements AuditorAware<Integer> {

    @Override
    public Optional<Integer> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthDetails authDetails = (AuthDetails) authentication.getDetails();

        return Optional.of(authDetails.getUserId());
    }
}
