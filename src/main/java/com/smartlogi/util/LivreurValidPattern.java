package com.smartlogi.util;

import com.smartlogi.entity.Livreur;

import java.util.regex.Pattern;

public class LivreurValidPattern {


    private static final Pattern PATTERN_NOM = Pattern.compile("^[a-zA-ZÀ-ÿ\\s'-]{2,50}$");
    private static final Pattern PATTERN_PRENOM = Pattern.compile("^[a-zA-ZÀ-ÿ\\s'-]{2,50}$");
    private static final Pattern PATTERN_EMAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern PATTERN_TELEPHONE = Pattern.compile("^(\\+212|0)([5-7])[0-9]{8}$");
    private static final Pattern PATTERN_PASSWORD = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@#$%^&*()_+\\-]{6,}$");
    private static final Pattern PATTERN_VEHICULE = Pattern.compile("^(?i)(Scooter|Moto|Voiture|Velo)$");


    public static boolean estNomValide(String nom) {
        if (nom == null || nom.trim().isEmpty()) return false;
        return PATTERN_NOM.matcher(nom.trim()).matches();
    }


    public static boolean estPrenomValide(String prenom) {
        if (prenom == null || prenom.trim().isEmpty()) return false;
        return PATTERN_PRENOM.matcher(prenom.trim()).matches();
    }


    public static boolean estEmailValide(String email) {
        if (email == null || email.trim().isEmpty()) return false;
        return PATTERN_EMAIL.matcher(email.trim()).matches();
    }

    public static boolean estTelephoneValide(String telephone) {
        if (telephone == null || telephone.trim().isEmpty()) return false;
        return PATTERN_TELEPHONE.matcher(telephone.trim()).matches();
    }

    public static boolean estMotDePasseValide(String motDePasse) {
        if (motDePasse == null || motDePasse.trim().isEmpty()) return false;
        return PATTERN_PASSWORD.matcher(motDePasse.trim()).matches();
    }

    public static boolean estVehiculeValide(String vehicule) {
        if (vehicule == null || vehicule.trim().isEmpty()) return false;
        return PATTERN_VEHICULE.matcher(vehicule.trim()).matches();
    }

    public static String validerUtilisateur(Livreur livreur) {

        if (!estNomValide(livreur.getNom())) return "Le nom est invalide (lettres uniquement, 2 à 50 caractères)";
        if (!estPrenomValide(livreur.getPrenom())) return "Le prénom est invalide (lettres uniquement, 2 à 50 caractères)";
        if (!estEmailValide(livreur.getEmail())) return "L'email est invalide";
        if (!estTelephoneValide(livreur.getTelephone())) return "Le numéro de téléphone est invalide (format marocain attendu)";
        if (!estVehiculeValide(livreur.getVehicule())) return "Le type de véhicule est invalide (doit être : Scooter, Moto, Voiture, ou Vélo)";
        return null;
    }
}
