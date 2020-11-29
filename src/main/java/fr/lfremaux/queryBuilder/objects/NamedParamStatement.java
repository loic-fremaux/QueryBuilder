package fr.lfremaux.queryBuilder.objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author https://gist.github.com/ruseel/e10bd3fee3c2b165044317f5378c7446
 */
public class NamedParamStatement {
    private final PreparedStatement prepStmt;
    private final List<String> fields = new ArrayList<>();

    private static final Pattern findParametersPattern = Pattern.compile("(?<!')(:[\\w]*)(?!')");

    public NamedParamStatement(Connection conn, String statementWithNames) throws SQLException {
        final Matcher matcher = findParametersPattern.matcher(statementWithNames);
        while (matcher.find()) {
            fields.add(matcher.group().substring(1));
        }
        prepStmt = conn.prepareStatement(statementWithNames.replaceAll(findParametersPattern.pattern(), "?"));
    }

    public NamedParamStatement(Connection conn, String statementWithNames, int keys) throws SQLException {
        final Matcher matcher = findParametersPattern.matcher(statementWithNames);
        while (matcher.find()) {
            fields.add(matcher.group().substring(1));
        }
        prepStmt = conn.prepareStatement(statementWithNames.replaceAll(findParametersPattern.pattern(), "?"), keys);
    }

    public NamedParamStatement(Connection conn, String statementWithNames, int typeScroll, int updatable) throws SQLException {
        final Matcher matcher = findParametersPattern.matcher(statementWithNames);
        while (matcher.find()) {
            fields.add(matcher.group().substring(1));
        }

        prepStmt = conn.prepareStatement(statementWithNames.replaceAll(findParametersPattern.pattern(), "?"), typeScroll, updatable);
    }

    public void execute() throws SQLException {
        prepStmt.execute();
    }

    public ResultSet executeQuery() throws SQLException {
        return prepStmt.executeQuery();
    }

    public int executeUpdate() throws SQLException {
        return prepStmt.executeUpdate();
    }

    public ResultSet getGeneratedKeys() throws SQLException {
        return prepStmt.getGeneratedKeys();
    }

    public void close() throws SQLException {
        prepStmt.close();
    }

    public void bind(String name, String value) throws SQLException {
        if (name.startsWith(":")) name = name.substring(1);

        for (int index = 0; index < fields.size(); index++) {
            if (!fields.get(index).equals(name)) continue;
            prepStmt.setString(index + 1, value);
        }
    }
}