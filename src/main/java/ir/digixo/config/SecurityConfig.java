package ir.digixo.config;

//import ir.digixo.filter.C01BeforeFilter;
//import ir.digixo.filter.C02AfterFilter;
import ir.digixo.filter.C03CustomKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Autowired
//    private C01BeforeFilter c01BeforeFilter;
//    @Autowired
//    private C02AfterFilter c02AfterFilter;
    @Autowired
    private C03CustomKey c03CustomKey;

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder()
                .username("admin")
                .password("1234")
                .roles("ADMIN")
                .build();
        UserDetails user2 = User.builder()
                .username("nima")
                .password("1234")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        http.addFilterBefore(c01BeforeFilter, BasicAuthenticationFilter.class);
//        http.addFilterAfter(c02AfterFilter, BasicAuthenticationFilter.class);
        http.addFilterAt(c03CustomKey, BasicAuthenticationFilter.class);

        http.authorizeHttpRequests(
                authorizationManagerRequestMatcherRegistry -> {
                    authorizationManagerRequestMatcherRegistry.anyRequest().permitAll();
                });

        return http.build();
    }
}
