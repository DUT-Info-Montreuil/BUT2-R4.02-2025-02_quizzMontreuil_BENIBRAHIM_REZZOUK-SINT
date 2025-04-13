package org.univ_paris8.iut.montreuil.qdev.tp2025.gr02.jeuQuizz.services.impl;

import org.univ_paris8.iut.montreuil.qdev.tp2025.gr02.jeuQuizz.entities.dto.ElementsDispoDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr02.jeuQuizz.entities.dto.JoueursDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr02.jeuQuizz.entities.dto.QuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr02.jeuQuizz.services.interfaces.IElementsDispoServices;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr02.jeuQuizz.utils.exceptions.ChargementFichierException;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr02.jeuQuizz.utils.exceptions.ElementsNonDispoException;

import java.util.List;
import java.util.stream.Collectors;

public class ElementsDispoServicesImpl implements IElementsDispoServices {

    @Override
    public List<ElementsDispoDTO> determinerElementsDispoPourUnePartie() throws ElementsNonDispoException {
        List<QuestionnaireDTO> questionnaires;
        List<JoueursDTO> joueurs;

        try {
            questionnaires = QuestionnaireServicesImpl.getInstance()
                    .fournirListeQuestionnaires("src/main/resources/questionsQuizz_V1.1.csv");
            joueurs = JoueursServicesImpl.listeJoueur;
        } catch (Exception | ChargementFichierException e) {
            throw new ElementsNonDispoException("Impossible de charger les données nécessaires pour la partie.");
        }

        List<ElementsDispoDTO> configurations = questionnaires.stream()
                .collect(Collectors.groupingBy(QuestionnaireDTO::getLangue))
                .values().stream()
                .map(questionnaireDTOS -> new ElementsDispoDTO(
                        questionnaireDTOS,
                        joueurs
                ))
                .filter(dto -> !dto.estVide())
                .collect(Collectors.toList());


        if (configurations.isEmpty()) {
            throw new ElementsNonDispoException("Aucune configuration disponible pour lancer une partie.");
        }

        return configurations;
    }


}
