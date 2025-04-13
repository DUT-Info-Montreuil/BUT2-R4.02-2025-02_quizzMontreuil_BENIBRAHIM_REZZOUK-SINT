package org.univ_paris8.iut.montreuil.qdev.tp2025.gr02.jeuQuizz.utils.exceptions;

public class ProfilInvalideException extends ElementsNonDispoException {
    public ProfilInvalideException(String champ) {
        super("Profil joueur invalide - " + champ);
    }
}