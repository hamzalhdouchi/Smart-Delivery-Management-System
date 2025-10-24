package com.smartlogi.util;

import com.smartlogi.dto.CreateColisRequest;

import java.util.regex.Pattern;

public class ColisValidPattern {

    private static final Pattern PATTERN_STRING_ADDRESS = Pattern.compile("^[a-zA-Z0-9À-ÿ\\s.,'\\-]{3,255}$");




    public static boolean estAdresseValide(String adresse) {
        if (adresse == null || adresse.trim().isEmpty()) return false;
        return PATTERN_STRING_ADDRESS.matcher(adresse.trim()).matches();
    }


    public static String validerColisRequest(CreateColisRequest request) {

        // Validations du destinataire
        if (!estAdresseValide(request.getAdresse()))
            return "L'adresse destinataire est invalide (3-255 caractères, lettres, chiffres et signes courants)";
        return null;
    }
}