package com.utn.frcu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;

@Configuration
@EnableScheduling
public class ScheduleService {

    @Autowired
    private PuertoService puertoService;

    //@Scheduled(cron = "0 0 0 * * ?") */5 * * * *
    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void schedulePuertosService() throws GeneralSecurityException, IOException, ParseException {
        puertoService.guardarNivelesPuertos();
    }
}
