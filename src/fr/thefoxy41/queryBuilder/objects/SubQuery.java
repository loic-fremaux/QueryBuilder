package fr.thefoxy41.queryBuilder.objects;

import java.util.Map;

public class SubQuery extends BaseQuery<SubQuery> {

    public SubQuery() {

    }

    protected Map<String, String> getArguments() {
        return this.args;
    }
}