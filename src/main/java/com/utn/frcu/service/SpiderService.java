package com.utn.frcu.service;

import com.utn.frcu.entity.Clave;
import com.utn.frcu.entity.PuertoNivel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class SpiderService {

    public List<PuertoNivel> getInfo(String content) throws ParseException {

        List<PuertoNivel> puertosList = new ArrayList<>();

        List<Clave> claves = new ArrayList<>();
        claves.add(new Clave("Puerto:\">", "</th>"));
        claves.add(new Clave("Río:\">", "</td>"));
        claves.add(new Clave("\"warning\">", "</td>"));
        claves.add(new Clave("\"Fecha Hora:\"><b>", "</b></td>"));
        claves.add(new Clave("\"Alerta:\">", "</td>"));
        claves.add(new Clave("\"Evacuación:\">", "</td>"));

        String[] valoresIniciales = content.split(claves.get(0).getValorInicio());
        Arrays.stream(valoresIniciales)
                .forEach(valorInicial -> {
                    if(!valorInicial.contains("<!doctype html>")) {
                        PuertoNivel puertoNivel = new PuertoNivel();
                        puertoNivel.setPuerto(valorInicial.split(claves.get(0).getValorCierre())[0]);
                        puertosList.add(puertoNivel);
                    }
                });

        String[] valoresIniciales1 = content.split(claves.get(1).getValorInicio());

        int i = 0;
        for (String valorInicial : valoresIniciales1) {
            if (!valorInicial.contains("<!doctype html>")) {
                puertosList.get(i).setRio(valorInicial.split(claves.get(1).getValorCierre())[0]);
                i++;
            }
        }

        String[] valoresIniciales2 = content.split(claves.get(2).getValorInicio());
        int  j = 0;
        for (String valorInicial : valoresIniciales2) {
            if (!valorInicial.contains("<!doctype html>")) {
                puertosList.get(j).setNivel(valorInicial.split(claves.get(2).getValorCierre())[0]);
                j++;
            }
        }

        String[] valoresIniciales3 = content.split(claves.get(3).getValorInicio());
        int  k = 0;
        String newFormat = "dd/MM/yy hh:mm";
        for (String valorInicial : valoresIniciales3) {
            if (!valorInicial.contains("<!doctype html>")) {
                String fecha =valorInicial.split(claves.get(3).getValorCierre())[0];
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yy - hhmm", Locale.ENGLISH);

                Date date = sdf.parse(fecha);
                sdf.applyPattern(newFormat);
                String fechaNueva = sdf.format(date);
                puertosList.get(k).setFechaHora(fechaNueva);
                k++;
            }
        }

        String[] valoresIniciales4 = content.split(claves.get(4).getValorInicio());
        int  l = 0;
        for (String valorInicial : valoresIniciales4) {
            if (!valorInicial.contains("<!doctype html>")) {
                puertosList.get(l).setAlerta(valorInicial.split(claves.get(4).getValorCierre())[0]);
                l++;
            }
        }

        String[] valoresIniciales5 = content.split(claves.get(5).getValorInicio());
        int  m = 0;
        for (String valorInicial : valoresIniciales5) {
            if (!valorInicial.contains("<!doctype html>")) {
                puertosList.get(m).setEvacuacion(valorInicial.split(claves.get(5).getValorCierre())[0]);
                m++;
            }
        }

        return puertosList;
    }

    public List<String> getLinks(String domain, String content) {
        List<String> links = new ArrayList<>();

        String[] splitHref = content.split("href=\"");

        List<String> hrefList = new ArrayList<String>(Arrays.asList(splitHref));
        hrefList.forEach(strHref -> {
            String[] aux2 = strHref.split("\"");
            links.add(aux2[0]);
        });
        return cleanLinks(links, domain);
    }

    private List<String> cleanLinks(List<String> links, String domain) {
        String[] excludedExtensions = new String[]{"css", "js", "json", "jpg", "png"};

        List<String> finalLinks = links.stream()
                .filter(link -> Arrays.stream(excludedExtensions).noneMatch(link::endsWith))
                .map(link -> link.startsWith("/") ? domain + link : link)
                .collect(Collectors.toList());

        List<String> uniqueLink = new ArrayList<>();
        uniqueLink.addAll(new HashSet<>(finalLinks));

        return uniqueLink;
    }

    public String getWebContent(String link) {
        System.out.println("INIT");
        System.out.println(link);
        //Download webs
        try {
            URL url = new URL(link);

            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String encoding = conn.getContentEncoding();

            InputStream input = null;
            try {
                input = conn.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Stream<String> streamString = new BufferedReader(new InputStreamReader(input))
                    .lines();
            System.out.println("END");
            return streamString.collect(Collectors.joining());
        } catch (Exception e) {

        }
        return null;
    }
}

