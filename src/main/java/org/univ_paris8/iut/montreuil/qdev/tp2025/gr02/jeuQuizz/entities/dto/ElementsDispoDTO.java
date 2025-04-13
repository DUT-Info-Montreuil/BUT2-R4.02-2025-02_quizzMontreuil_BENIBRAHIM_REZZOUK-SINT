package org.univ_paris8.iut.montreuil.qdev.tp2025.gr02.jeuQuizz.entities.dto;

import java.util.Collections;
import java.util.List;

public class ElementsDispoDTO {
    private final List<QuestionnaireDTO> questionnaires;
    private final List<JoueursDTO> joueurs;

    public ElementsDispoDTO(List<QuestionnaireDTO> questionnaires, List<JoueursDTO> joueurs) {
        this.questionnaires = Collections.unmodifiableList(questionnaires);
        this.joueurs = Collections.unmodifiableList(joueurs);
    }

    public List<QuestionnaireDTO> getQuestionnaires() {
        return questionnaires;
    }

    public List<JoueursDTO> getJoueurs() {
        return joueurs;
    }

    public boolean estVide() {
        return questionnaires.isEmpty() || joueurs.isEmpty();
    }

    @Override
    public String toString() {
        return "Disponibilités : " + questionnaires.size() + "questionnaires, " + joueurs.size() + "joueurs";
    }
}