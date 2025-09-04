package com.mergingtonhigh.schoolmanagement.domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for DifficultyLevel enum.
 */
class DifficultyLevelTest {

    @Test
    void shouldReturnCorrectLabels() {
        // Assert
        assertEquals("Iniciante", DifficultyLevel.INICIANTE.getLabel());
        assertEquals("Intermediário", DifficultyLevel.INTERMEDIARIO.getLabel());
        assertEquals("Avançado", DifficultyLevel.AVANCADO.getLabel());
    }

    @Test
    void shouldHaveCorrectNumberOfLevels() {
        // Assert
        assertEquals(3, DifficultyLevel.values().length);
    }
}