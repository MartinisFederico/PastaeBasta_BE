package com.fandeni.pastaandbastaBE.conf;

import com.fandeni.pastaandbastaBE.model.Ruolo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.fandeni.pastaandbastaBE.model")
public class RuoloConf {
    @Bean
    public Ruolo ruoloAdmin(){
        return new Ruolo("ADMIN");
    }
    @Bean
    public Ruolo ruoloUser(){
        return new Ruolo("USER");
    }
}
