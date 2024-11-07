package org.openapitools.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DocumentValidationTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidDocument() {
        Document document = new Document();
        document.setId(UUID.randomUUID());
        document.setUsername("test_user");
        document.setDescription("This is a valid description.");
        document.setUploadedDate("2024-11-04");  // correct date format
        document.setFile(new org.springframework.core.io.ByteArrayResource("file content".getBytes()));

        Set<ConstraintViolation<Document>> violations = validator.validate(document);
        assertTrue(violations.isEmpty(), "There should be no validation errors for a valid document.");
    }

    @Test
    public void testUsernameNotNull() {
        Document document = new Document();
        document.setId(UUID.randomUUID());
        document.setDescription("This is a valid description.");
        document.setUploadedDate("2024-11-04");
        document.setFile(new org.springframework.core.io.ByteArrayResource("file content".getBytes()));

        Set<ConstraintViolation<Document>> violations = validator.validate(document);
        assertEquals(1, violations.size());
        assertEquals("Username cannot be null", violations.iterator().next().getMessage());
    }

    @Test
    public void testDescriptionLength() {
        Document document = new Document();
        document.setId(UUID.randomUUID());
        document.setUsername("test_user");
        document.setDescription(new String(new char[300]).replace('\0', 'A'));  // Exceeding 255 characters
        document.setUploadedDate("2024-11-04");
        document.setFile(new org.springframework.core.io.ByteArrayResource("file content".getBytes()));

        Set<ConstraintViolation<Document>> violations = validator.validate(document);
        assertEquals(1, violations.size());
        assertEquals("Description must be less than 255 characters", violations.iterator().next().getMessage());
    }

    @Test
    public void testUploadedDatePattern() {
        Document document = new Document();
        document.setId(UUID.randomUUID());
        document.setUsername("test_user");
        document.setDescription("This is a valid description.");
        document.setUploadedDate("04-11-2024");  // Incorrect date format
        document.setFile(new org.springframework.core.io.ByteArrayResource("file content".getBytes()));

        Set<ConstraintViolation<Document>> violations = validator.validate(document);
        assertEquals(1, violations.size());
        assertEquals("Uploaded date must be in the format YYYY-MM-DD", violations.iterator().next().getMessage());
    }

    @Test
    public void testFileNotNull() {
        Document document = new Document();
        document.setId(UUID.randomUUID());
        document.setUsername("test_user");
        document.setDescription("This is a valid description.");
        document.setUploadedDate("2024-11-04");

        Set<ConstraintViolation<Document>> violations = validator.validate(document);
        assertEquals(1, violations.size());
        assertEquals("File must not be null", violations.iterator().next().getMessage());
    }

}
