package com.tw.cn.example.springboot.demospringbootkotlin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/banks")
class BankController {

    @GetMapping
    fun helloWorld(): String = "Hello, this is a REST endpoint."
}