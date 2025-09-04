package com.mergingtonhigh.schoolmanagement.domain.valueobjects;

/**
 * Enum representing the difficulty levels for activities.
 * Activities without a specified difficulty level are considered suitable for all levels.
 */
public enum DifficultyLevel {
    INICIANTE("Iniciante"),
    INTERMEDIARIO("Intermediário"),
    AVANCADO("Avançado");

    private final String label;

    DifficultyLevel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}