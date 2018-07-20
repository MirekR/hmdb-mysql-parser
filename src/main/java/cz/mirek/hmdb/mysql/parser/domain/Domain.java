package cz.mirek.hmdb.mysql.parser.domain;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.HashMap;
import java.util.Map;

public class Domain {
    private String tagName;
    private String parentTagName;
    private long dbId = -1;
    private long dbParentId = -1;
    private Map<String, StringBuilder> data = new HashMap<>();

    public Domain(String tagName, String parentTagName, long id, long parentId) {
        this.tagName = tagName;
        this.dbId = id;
        this.dbParentId = parentId;
        this.parentTagName = parentTagName;
    }

    public Domain() {

    }

    public void add(String columnName, String dato) {
        StringBuilder value = data.get(columnName);

        if (value == null) {
            value = new StringBuilder();
            data.put(columnName, value);
        }

        value.append(dato);
    }

    public long getDbId() {
        return this.dbId;
    }

    public String getTagName() {
        return this.tagName;
    }

    public String getParentTagName() {
        return this.parentTagName;
    }

    public String toString() {
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        columns.append("parent_id").append(", ").append("id").append(", ").append("parent_tag");
        values.append(dbParentId).append(", ").append(dbId).append(", '").append(parentTagName).append("'");

        data.forEach((key, value) -> {
            columns.append(", ");
            values.append(", ");

            columns.append(key);
            values.append("'").append(StringEscapeUtils.escapeEcmaScript(value.toString())).append("'");
        });

        return "insert into " + getTagAsTableName() + " (" + columns + ") values ("+ values +");";
    }

    private String getTagAsTableName() {
        return "t_" + tagName + "s";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Domain domain = (Domain) o;

        if (dbId != domain.dbId) return false;
        if (dbParentId != domain.dbParentId) return false;
        return tagName != null ? tagName.equals(domain.tagName) : domain.tagName == null;
    }

    @Override
    public int hashCode() {
        int result = tagName != null ? tagName.hashCode() : 0;
        result = 31 * result + (int) (dbId ^ (dbId >>> 32));
        result = 31 * result + (int) (dbParentId ^ (dbParentId >>> 32));
        return result;
    }
}
