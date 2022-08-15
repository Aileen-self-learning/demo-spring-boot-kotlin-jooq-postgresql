package com.tw.cn.example.springboot.demospringbootkotlin.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
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

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(uds)?.passwordEncoder(password())
    }

    override fun configure(http: HttpSecurity?)  {
        http
            ?.authorizeRequests()
            ?.antMatchers("/**","/login","/resources/**")?.permitAll()
            ?.anyRequest()?.authenticated()
            ?.and()?.formLogin()
            ?.loginPage("/login")
            ?.defaultSuccessUrl("/index")
            ?.permitAll()
            ?.and()?.logout()
            ?.permitAll()
        http?.csrf()?.ignoringAntMatchers("/banks/**")
    }

    @Bean
    fun password() : PasswordEncoder = BCryptPasswordEncoder()
}