package com.tw.cn.example.springboot.demospringbootkotlin.service

import com.tw.cn.example.springboot.demospringbootkotlin.datasource.UserRepository
import org.jooq.generated.tables.pojos.Users
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service("uds")
class MyUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails? {

        //1. 查询：数据库/三方/内存/读取配置/jdbc
        val userDetails: Users = userRepository.findUserByName(username)

        //2. 权限转化
        val auths : List<SimpleGrantedAuthority> = userDetails.auth.split(",")
            .stream().map{ SimpleGrantedAuthority(it) }
            .collect(Collectors.toList())

        //3. 将查询的信息封装到到UserDetails并返回
        return User
            .withUsername(userDetails.name)
            .password(BCryptPasswordEncoder().encode(userDetails.password))
            .authorities(auths)
            .build()
    }
}