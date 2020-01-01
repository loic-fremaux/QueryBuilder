package fr.thefoxy41.queryBuilder.objects.result;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryResultSet {
    private int position = -1;
    private final List<Map<String, Object>> rows;

    public QueryResultSet(ResultSet result) throws SQLException {
        final ResultSetMetaData metaData = result.getMetaData();
        final int columns = metaData.getColumnCount();
        final List<Map<String, Object>> rows = new ArrayList<>();
        while (result.next()) {
            final Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= columns; i++) {
                row.put(metaData.getColumnLabel(i), result.getObject(i));
            }
            rows.add(row);
        }
        result.close();
        this.rows = rows;
    }

    public Map<String, Object> getValues() {
        return rows.get(position);
    }

    public int getSize() {
        return rows.size();
    }

    public boolean getBoolean(String name) {
        return (boolean) rows.get(position).get(name);
    }

    public byte getByte(String name) {
        return (byte) rows.get(position).get(name);
    }

    public short getShort(String name) {
        return (short) rows.get(position).get(name);
    }

    public int getInt(String name) {
        return (int) rows.get(position).get(name);
    }

    public long getLong(String name) {
        return (long) rows.get(position).get(name);
    }

    public double getDouble(String name) {
        return (double) rows.get(position).get(name);
    }

    public double getFloat(String name) {
        return (float) rows.get(position).get(name);
    }

    public String getString(String name) {
        return (String) rows.get(position).get(name);
    }

    public boolean hasNext() {
        return position + 1 < rows.size();
    }

    public boolean next() {
        if (this.hasNext()) {
            position++;
            return true;
        } else {
            return false;
        }
    }
}
