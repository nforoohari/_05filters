package ir.digixo.config;

import ir.digixo.filter.C03CustomKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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
                .username("nasim")
                .password("1234")
                .roles("ADMIN")
                .build();


        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    ///
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//before
        //  http.addFilterBefore(new C01BeforeFilter(), BasicAuthenticationFilter.class);
        //after
        //  http.addFilterAfter(new C02After(), BasicAuthenticationFilter.class);


        http.addFilterAt(c03CustomKey, BasicAuthenticationFilter.class);


        http.authorizeHttpRequests(
                        authorizationManagerRequestMatcherRegistry -> {
                            authorizationManagerRequestMatcherRegistry.anyRequest().permitAll();
                        }
                )
                .formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer
                            .loginPage("/showMyLoginform")
                            .loginProcessingUrl("/login2")
                            .permitAll();
                })
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.permitAll())

        ;

        //http.httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
