package com.opsbackend.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void shouldFailWhenNameIsTooShort() {

        // Arrange
        String id = "c1";
        String name = "Gi";
        String email = "gian@mail.com";

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                new Customer(id, name, email)
        );
    }

    @Test
    void shouldFailWhenEmailIsInvalid() {

        // Arrange
        String id = "c1";
        String name = "Gian Perez";
        String email = "correo-invalido";

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                new Customer(id, name, email)
        );
    }

    @Test
    void shouldChangeName() {

        // Arrange
        Customer c = new Customer("c1", "Gian Perez", "gian@mail.com");

        // Act
        c.changeName("Gian PC");

        // Assert
        assertEquals("Gian PC", c.getName());
    }

    @Test
    void shouldChangeEmail() {

        // Arrange
        Customer c = new Customer("c1", "Gian Perez", "gian@mail.com");

        // Act
        c.changeEmail("nuevo@mail.com");

        // Assert
        assertEquals("nuevo@mail.com", c.getEmail());
    }

    @Test
    void shouldDeactivateCustomer() {

        // Arrange
        Customer c = new Customer("c1", "Gian Perez", "gian@mail.com");

        // Act
        c.deactivate();

        // Assert
        assertFalse(c.isActive());
    }
}
