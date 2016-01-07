package org.petclinic.petclinicapp.Exceptions;

/**
 * Исключение, использующееся в случае некорректно введенной строки
 */
public class WrongInputException extends Exception {
    public WrongInputException(final String message) {
        super(message);
    }
}
