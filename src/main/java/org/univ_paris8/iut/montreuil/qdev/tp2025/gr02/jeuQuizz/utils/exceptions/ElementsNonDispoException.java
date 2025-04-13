package org.univ_paris8.iut.montreuil.qdev.tp2025.gr02.jeuQuizz.utils.exceptions;

public class ElementsNonDispoException extends Exception {
    public ElementsNonDispoException(String message) {
        super("Configuration impossible : " + message);
    }
}