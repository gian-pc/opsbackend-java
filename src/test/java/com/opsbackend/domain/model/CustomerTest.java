package com.opsbackend.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerTest {

    @Test
    void shouldCreateCustomerWithValidData(){
        // Arrange - preparamos los datos
        String id = "c1";
        String name = "Gian Paucar";
        String email = "gpaucarcortez@gmail.com";

        // Act - ejecutamos la acción
        Customer gian = new Customer(id, name, email); // Creamos el objeto gian

        // Assert - verificamos el resultado
        assertEquals(id, gian.getId());
        assertEquals(name, gian.getName());
        assertEquals(email, gian.getEmail());
        assertEquals(gian.isActive(), true);
    }

    @Test
    void shouldFailWhenIdIsBlank() {

        // Arrange
        String id = "   ";
        String name = "Gian Perez";
        String email = "gian@mail.com";

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                new Customer(id, name, email)
        );
    }
}
