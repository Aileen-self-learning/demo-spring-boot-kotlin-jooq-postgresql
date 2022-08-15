package com.tw.cn.example.springboot.demospringbootkotlin.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder



@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var uds: UserDetailsService

    private val bankEndpoint = "/banks/**"

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(uds)?.passwordEncoder(password())
    }

    override fun configure(http: HttpSecurity?)  {
        http
            ?.authorizeRequests()
            ?.antMatchers("/login")?.permitAll()
            ?.antMatchers(HttpMethod.POST, bankEndpoint)?.hasRole("ADMIN")
            ?.antMatchers(HttpMethod.PATCH, bankEndpoint)?.hasRole("ADMIN")
            ?.antMatchers(HttpMethod.DELETE, bankEndpoint)?.hasRole("ADMIN")
            ?.anyRequest()?.authenticated()
            ?.and()?.formLogin()
            ?.loginPage("/login")
            ?.defaultSuccessUrl("/index")
            ?.permitAll()
            ?.and()?.logout()
            ?.permitAll()
        http?.csrf()?.ignoringAntMatchers(bankEndpoint)
    }

    @Bean
    fun password() : PasswordEncoder = BCryptPasswordEncoder()
}