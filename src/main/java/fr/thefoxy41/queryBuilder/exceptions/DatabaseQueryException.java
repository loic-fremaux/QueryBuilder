package fr.thefoxy41.queryBuilder.exceptions;

import java.sql.SQLException;

public class DatabaseQueryException extends SQLException {

    public DatabaseQueryException(String message) {
        super(message);
    }

    public DatabaseQueryException(String message, String query) {
        super(message, query);
    }

    public DatabaseQueryException(SQLException e, String query) {
        super(e.getMessage(), query, e);
    }
}
