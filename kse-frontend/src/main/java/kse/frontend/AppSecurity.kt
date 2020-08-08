package kse.frontend

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories

@Configuration
@EnableWebSecurity
open class AppSecurity : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {

        http.rememberMe()
                .disable()

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.httpBasic().init(http)

        // @formatter:off
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/actuator", "/actuator/prometheus").permitAll()
                .antMatchers(HttpMethod.GET,"/actuator/**").hasAnyRole("ACTUATOR")
                .anyRequest().permitAll()
        // @formatter:on
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        // @formatter:off
        auth.inMemoryAuthentication()
                .withUser("actuator").password("{noop}actuator").roles("ACTUATOR")
                .and()
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder())
        // @formatter:on
    }

}
