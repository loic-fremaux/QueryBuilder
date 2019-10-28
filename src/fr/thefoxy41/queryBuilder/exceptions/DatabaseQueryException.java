package fr.thefoxy41.queryBuilder.exceptions;

import java.sql.SQLException;

public class DatabaseQueryException extends SQLException {

    public DatabaseQueryException(String message) {
        super(message);
    }

    public DatabaseQueryException(String message, String query) {
        super(message);
    }
}
