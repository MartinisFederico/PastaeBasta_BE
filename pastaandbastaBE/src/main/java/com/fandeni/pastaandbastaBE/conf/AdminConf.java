package com.fandeni.pastaandbastaBE.conf;

import com.fandeni.pastaandbastaBE.model.Ruolo;
import com.fandeni.pastaandbastaBE.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.fandeni.pastaandbastaBE.model")
@Import(RuoloConf.class)
public class AdminConf {
    @Autowired
    @Qualifier("ruoloAdmin")
    private Ruolo r;
    @Bean
    public Utente admin(){
        return new Utente(null, null, "admin", null, null,
                PasswordUtils.encrypt("administrator"), null, r);
    }
}
