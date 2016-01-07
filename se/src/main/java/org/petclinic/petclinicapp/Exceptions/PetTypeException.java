package org.petclinic.petclinicapp.Exceptions;

/**
 * Исключение, использующееся в случае некорректного типа питомца
 */
public class PetTypeException extends Exception {
    public PetTypeException(final String message) {
        super(message);
    }
}