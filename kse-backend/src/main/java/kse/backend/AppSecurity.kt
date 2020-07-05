package kse.backend

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
open class AppSecurity : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        // @formatter:off
        http.authorizeRequests()
                .antMatchers("/actuator/**")
                    .permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                    .httpBasic()
        // @formatter:on
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        // @formatter:off
        auth.inMemoryAuthentication()
                    .withUser("admin")
                    .password("{noop}admin")
                    .roles("ADMIN")
                .and()
                    .withUser("yingzhuo")
                    .password("{noop}yingzhuo")
                    .roles("USER")
        // @formatter:on
    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder =
            PasswordEncoderFactories.createDelegatingPasswordEncoder()

}
