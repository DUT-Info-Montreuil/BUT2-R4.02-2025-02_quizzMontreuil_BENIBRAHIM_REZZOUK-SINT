package org.univ_paris8.iut.montreuil.qdev.tp2025.gr02.jeuQuizz;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr02.jeuQuizz.entities.dto.ElementsDispoDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr02.jeuQuizz.entities.dto.JoueursDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr02.jeuQuizz.entities.dto.QuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr02.jeuQuizz.services.impl.ElementsDispoServicesImpl;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr02.jeuQuizz.services.impl.JoueursServicesImpl;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr02.jeuQuizz.services.impl.QuestionnaireServicesImpl;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr02.jeuQuizz.utils.exceptions.ChargementFichierException;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr02.jeuQuizz.utils.exceptions.ElementsNonDispoException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ElementsDispoServicesImplTest {

    @Mock
    private QuestionnaireServicesImpl questionnaireService;

    @Mock
    private JoueursServicesImpl joueurService;

    @InjectMocks
    private ElementsDispoServicesImpl elementsDispoService;

    @Test
    public void testDeterminerElementsDispo_reussi() throws Exception, ChargementFichierException {
        // Arrange
        when(questionnaireService.fournirListeQuestionnaires(anyString()))
                .thenReturn(new ArrayList<>(List.of(
                        new QuestionnaireDTO(1, "Sport", "fr", new ArrayList<>()),
                        new QuestionnaireDTO(2, "Science", "fr", new ArrayList<>())
                )));

        when(joueurService.getListeJoueur())
                .thenReturn(new ArrayList<>(List.of(
                        new JoueursDTO("Jean", "jean123", new HashSet<>(), 1990, "fr"),
                        new JoueursDTO("John", "john456", new HashSet<>(), 2000, "fr")
                )));

        // Act
        List<ElementsDispoDTO> result = elementsDispoService.determinerElementsDispoPourUnePartie();

        // Assert
        assertEquals(1, result.size()); // Une seule langue commune : "fr"
        assertEquals("fr", result.get(0).getQuestionnaires().get(0).getLangue());
    }

    @Test
    public void testDeterminerElementsDispo_nonreussi() throws ChargementFichierException {
        // Arrange
        when(questionnaireService.fournirListeQuestionnaires(anyString()))
                .thenThrow(new ChargementFichierException());

        // Act & Assert
        assertThrows(ElementsNonDispoException.class, () -> {
            elementsDispoService.determinerElementsDispoPourUnePartie();
        });
    }


}
