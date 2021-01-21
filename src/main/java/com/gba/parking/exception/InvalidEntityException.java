package com.gba.parking.exception;

/**
 * Retorna exceção de Entidade inválida para ser utilizada nas validações
 */
public final class InvalidEntityException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidEntityException(String message) {
        super(message);
    }

    public InvalidEntityException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
