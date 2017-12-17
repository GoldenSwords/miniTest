package luo.system.config;

import luo.system.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    SecurityService securityService;
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("USER");
//        auth.inMemoryAuthentication().withUser("admin").password("root123").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("dba").password("root123").roles("ADMIN","DBA");
        //自定义AuthenticationProvider
        auth.authenticationProvider(authenticationProvider());
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        AuthenticationProvider authenticationProvider=new SecurityProvider(securityService);
        return authenticationProvider;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests().antMatchers("/login","/static/**","/css/**").permitAll().anyRequest()
                .fullyAuthenticated().and().formLogin().loginPage("/login").permitAll()
                .failureUrl("/login?error=true").defaultSuccessUrl("/welcome").and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/error")).and()
                .exceptionHandling().accessDeniedPage("/error?error=true");
        // @formatter:on
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}

