package org.univ_paris8.iut.montreuil.qdev.tp2025.gr02.jeuQuizz.services.interfaces;

import org.univ_paris8.iut.montreuil.qdev.tp2025.gr02.jeuQuizz.entities.dto.ElementsDispoDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr02.jeuQuizz.utils.exceptions.ChargementFichierException;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr02.jeuQuizz.utils.exceptions.ElementsNonDispoException;

import java.util.List;

public interface IElementsDispoServices {
    List<ElementsDispoDTO> determinerElementsDispoPourUnePartie() throws ChargementFichierException, ElementsNonDispoException;
}
