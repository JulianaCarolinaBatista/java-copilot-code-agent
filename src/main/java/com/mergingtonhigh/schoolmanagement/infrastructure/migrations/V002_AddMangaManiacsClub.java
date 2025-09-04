package com.mergingtonhigh.schoolmanagement.infrastructure.migrations;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mergingtonhigh.schoolmanagement.domain.entities.Activity;
import com.mergingtonhigh.schoolmanagement.domain.valueobjects.ActivityType;
import com.mergingtonhigh.schoolmanagement.domain.valueobjects.ScheduleDetails;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;

/**
 * Migration to add the Manga Maniacs club to the school's extracurricular activities.
 * This club focuses on Japanese manga culture and graphic novel appreciation.
 */
@ChangeUnit(id = "add-manga-maniacs-club", order = "002", author = "Andre Fontoura")
public class V002_AddMangaManiacsClub {

    private final MongoTemplate mongoTemplate;

    public V002_AddMangaManiacsClub(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Execution
    public void migrate() {
        // Check if Manga Maniacs activity already exists
        Query existingActivityQuery = new Query(Criteria.where("name").is("Manga Maniacs"));
        Activity existingActivity = mongoTemplate.findOne(existingActivityQuery, Activity.class);
        
        if (existingActivity == null) {
            addMangaManiacsClub();
        }
    }

    private void addMangaManiacsClub() {
        // Manga Maniacs - A club for manga and anime enthusiasts
        Activity mangaManiacs = new Activity(
                "Manga Maniacs",
                "Mergulhe no universo épico dos mangás japoneses! Descubra mundos fantásticos repletos de ninjas corajosos, guerreiros lendários, histórias de amor emocionantes e aventuras que vão além da imaginação. Junte-se aos outros otakus para discutir seus personagens favoritos, explorar diferentes gêneros como shonen, shoujo, seinen e josei, e compartilhar recomendações dos melhores títulos. Venha fazer parte dessa jornada incredible através das páginas dos romances gráficos mais fascinantes do Japão!",
                "Terças-feiras, 19:00 - 20:30",
                new ScheduleDetails(List.of("Tuesday"), LocalTime.of(19, 0), LocalTime.of(20, 30)),
                15,
                ActivityType.ARTS);
        mangaManiacs.setParticipants(List.of());
        mongoTemplate.save(mangaManiacs);
    }

    @RollbackExecution
    public void rollback() {
        // Remove the Manga Maniacs activity
        Query query = new Query(Criteria.where("name").is("Manga Maniacs"));
        mongoTemplate.remove(query, Activity.class);
    }
}