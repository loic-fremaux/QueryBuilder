package fr.lfremaux.queryBuilder.enums;

public enum SQLJoin {

    INNER(),
    CROSS(),
    LEFT(),
    RIGHT(),
    FULL(),
    SELF(),
    NATURAL(),
    UNION();

}