package com.utn.frcu.service;

import com.utn.frcu.entity.SheetsAndJava;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;

@Service
public class PuertoService {

    @Autowired
    SheetsAndJava sheetsAndJava;

    public void guardarNivelesPuertos() throws GeneralSecurityException, IOException, ParseException {
        sheetsAndJava.guardarExcel();
    }

}
