package org.petclinic.petclinicapp.Exceptions;

/**
 * Исключение, использущееся в случаях отсутствия ID в списке клиентов
 */
public class IDException extends Exception {
    public IDException(final String message) {
        super(message);
    }
}