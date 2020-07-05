package kse.backend

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.factory.PasswordEncoderFactories

@Configuration
@EnableWebSecurity
open class AppSecurity : WebSecurityConfigurerAdapter() {

    @Value("\${management.endpoints.web.base-path:/actuator}")
    lateinit var actuatorPrefix: String

    override fun configure(http: HttpSecurity) {
        // @formatter:off
        http.authorizeRequests()
                .antMatchers("$actuatorPrefix/**")
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
                    .password("{MD5}21232f297a57a5a743894a0e4a801fc3")
                    .roles("ADMIN")
                .and()
                    .withUser("yingzhuo")
                    .password("{MD5}1102ea8656d577d8f73fcc9e8cf9b182\n")
                    .roles("USER")
                .and()
                    .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder())
        // @formatter:on
    }

}
