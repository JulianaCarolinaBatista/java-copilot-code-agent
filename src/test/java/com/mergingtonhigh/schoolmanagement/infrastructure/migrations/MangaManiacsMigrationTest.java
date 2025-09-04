package com.mergingtonhigh.schoolmanagement.infrastructure.migrations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.mergingtonhigh.schoolmanagement.domain.entities.Activity;
import com.mergingtonhigh.schoolmanagement.domain.valueobjects.ActivityType;

/**
 * Integration test to verify the database migration correctly seeds the Manga Maniacs activity.
 */
@SpringBootTest
@Testcontainers
class MangaManiacsMigrationTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest")
            .withExposedPorts(27017);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void shouldSeedMangaManiacsActivity() {
        // Arrange & Act - Migration runs automatically during Spring Boot startup

        // Assert - Verify Manga Maniacs activity exists
        Query query = new Query(Criteria.where("name").is("Manga Maniacs"));
        Activity mangaActivity = mongoTemplate.findOne(query, Activity.class);

        assertNotNull(mangaActivity, "Manga Maniacs activity should be seeded");
        assertEquals("Manga Maniacs", mangaActivity.getName());
        assertEquals("Explore as histórias fantásticas dos personagens mais interessantes dos Mangás japoneses (romances gráficos)", 
                mangaActivity.getDescription());
        assertEquals(15, mangaActivity.getMaxParticipants());
        assertEquals(ActivityType.ARTS, mangaActivity.getType());
        assertEquals("Terças-feiras, 19:00 - 20:30", mangaActivity.getSchedule());
        assertTrue(mangaActivity.getParticipants().isEmpty(), "Should start with no participants");
    }

    @Test 
    void shouldSeedCorrectTotalNumberOfActivities() {
        // Act - Count all activities
        long totalActivities = mongoTemplate.count(new Query(), Activity.class);

        // Assert - Should have 14 activities (13 original + 1 Manga Maniacs)
        assertEquals(14, totalActivities, "Should have 14 total activities including Manga Maniacs");
    }

    static {
        // Configure Spring to use the test container's MongoDB connection
        mongoDBContainer.start();
        System.setProperty("spring.data.mongodb.host", mongoDBContainer.getHost());
        System.setProperty("spring.data.mongodb.port", mongoDBContainer.getFirstMappedPort().toString());
    }
}