package com.mergingtonhigh.schoolmanagement.domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for ActivityType value object.
 */
class ActivityTypeTest {

    @Test
    void shouldClassifyMangaActivityAsArts() {
        // Act
        ActivityType result = ActivityType.determineFromContent(
                "Manga Maniacs",
                "Explore as histórias fantásticas dos personagens mais interessantes dos Mangás japoneses (romances gráficos)"
        );

        // Assert
        assertEquals(ActivityType.ARTS, result);
    }

    @Test
    void shouldClassifyGenericArtsActivity() {
        // Act
        ActivityType result = ActivityType.determineFromContent(
                "Clube de Arte",
                "Explore diversas técnicas artísticas e crie obras-primas"
        );

        // Assert
        assertEquals(ActivityType.ARTS, result);
    }

    @Test
    void shouldClassifySportsActivity() {
        // Act
        ActivityType result = ActivityType.determineFromContent(
                "Time de Futebol",
                "Junte-se ao time de futebol da escola"
        );

        // Assert
        assertEquals(ActivityType.SPORTS, result);
    }

    @Test
    void shouldClassifyTechnologyActivity() {
        // Act
        ActivityType result = ActivityType.determineFromContent(
                "Oficina de Robótica",
                "Construa e programe robôs"
        );

        // Assert
        assertEquals(ActivityType.TECHNOLOGY, result);
    }

    @Test
    void shouldDefaultToAcademicForUnknownActivity() {
        // Act
        ActivityType result = ActivityType.determineFromContent(
                "Atividade Desconhecida",
                "Uma atividade que não se encaixa em nenhuma categoria específica"
        );

        // Assert
        assertEquals(ActivityType.ACADEMIC, result);
    }
}