package com.example.crudmn.config;

import com.example.crudmn.entity.Userpoll;
import com.example.crudmn.repository.UserPollRepository;
import com.example.crudmn.security.services.UserDetailsImpl;
import com.example.crudmn.security.services.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class AuditingConfig {

    @Bean
    public AuditorAware<Long> auditorProvider() {
        return new SpringSecurityAuditAwareImpl();
    }
}

class SpringSecurityAuditAwareImpl implements AuditorAware<Long> {



    @Autowired
    UserPollRepository userPollRepository;
    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }


      //  UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        UserDetailsImpl me = (UserDetailsImpl)  authentication.getPrincipal();


        Userpoll lme = userPollRepository.findByUsername(me.getUsername()).get();

        return Optional.ofNullable(lme.getId());
    }
}
