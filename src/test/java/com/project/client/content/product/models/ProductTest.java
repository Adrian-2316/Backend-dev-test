package com.project.client.content.product.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Product#Product()}
     *   <li>{@link Product#setAvailability(Boolean)}
     *   <li>{@link Product#setId(String)}
     *   <li>{@link Product#setName(String)}
     *   <li>{@link Product#setPrice(Double)}
     *   <li>{@link Product#getAvailability()}
     *   <li>{@link Product#getId()}
     *   <li>{@link Product#getName()}
     *   <li>{@link Product#getPrice()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Product actualProduct = new Product();
        actualProduct.setAvailability(true);
        actualProduct.setId("42");
        actualProduct.setName("Name");
        actualProduct.setPrice(10.0d);
        assertTrue(actualProduct.getAvailability());
        assertEquals("42", actualProduct.getId());
        assertEquals("Name", actualProduct.getName());
        assertEquals(10.0d, actualProduct.getPrice().doubleValue());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Product#Product(String, String, Double, Boolean)}
     *   <li>{@link Product#setAvailability(Boolean)}
     *   <li>{@link Product#setId(String)}
     *   <li>{@link Product#setName(String)}
     *   <li>{@link Product#setPrice(Double)}
     *   <li>{@link Product#getAvailability()}
     *   <li>{@link Product#getId()}
     *   <li>{@link Product#getName()}
     *   <li>{@link Product#getPrice()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        Product actualProduct = new Product("42", "Name", 10.0d, true);
        actualProduct.setAvailability(true);
        actualProduct.setId("42");
        actualProduct.setName("Name");
        actualProduct.setPrice(10.0d);
        assertTrue(actualProduct.getAvailability());
        assertEquals("42", actualProduct.getId());
        assertEquals("Name", actualProduct.getName());
        assertEquals(10.0d, actualProduct.getPrice().doubleValue());
    }
}
