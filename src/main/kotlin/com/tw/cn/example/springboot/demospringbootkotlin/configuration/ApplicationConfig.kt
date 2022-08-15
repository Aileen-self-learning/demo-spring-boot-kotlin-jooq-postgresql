package com.tw.cn.example.springboot.demospringbootkotlin.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.web.servlet.config.annotation.*

@Configuration
@EnableWebMvc
class ApplicationConfig: WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**").allowedMethods("GET","PUT","POST","DELETE","PATCH").exposedHeaders("Location")
    }

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/login").setViewName("login")
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE)
    }
}