package com.example.euromini;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        String urlText="http://api.nbp.pl/api/exchangerates/rates/a/eur?format=json"; //adres jsona
        String ZapisanyJson = downloadJsonFromURL(urlText); // wywołanie metody zapisującej jsona do StringBuildera dla tego adresu
        System.out.println(ZapisanyJson);  //wypisujemy jsona w konsoli

        Object parsowanieDanych = Configuration.defaultConfiguration().jsonProvider().parse(ZapisanyJson); //parsowanie ale chyba niepotrzebne, do następnej linijki można wrzucić bezpośrednio ZapisanyJson
        String wyciaganieCenyEuro = JsonPath.read(parsowanieDanych,"$.rates[0].mid").toString(); //wyciąganie wartości (mid)
        System.out.println(wyciaganieCenyEuro);

    }
        public static String downloadJsonFromURL(String urlText) { //metoda zapisująca jsona do StringBuildera
            try {
                URL myUrl = new URL(urlText);
                StringBuilder jsonText = new StringBuilder();
                try (InputStream myInputStream = myUrl.openStream();
                     Scanner scanner = new Scanner(myInputStream)) {
                    while (scanner.hasNextLine()) {
                        jsonText.append(scanner.nextLine());
                    }
                    return jsonText.toString();
                }
            } catch (IOException e) {
                System.err.println("Nie można pobrać z tego adresu " + urlText + " z powodu wyjątku: " + e.getMessage());
                return " ";
            }
        }



}
