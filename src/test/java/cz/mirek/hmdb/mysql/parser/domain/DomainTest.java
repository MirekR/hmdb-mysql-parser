package cz.mirek.hmdb.mysql.parser.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DomainTest {

    @Test
    public void shouldGenerateInsertStatementWithIdsOnly() {
      Domain domain = new Domain("TEST", "XXX", 1, 2);

      assertThat(domain.toString(), is("insert into TESTs (parent_id, id, parent_tag) values (2, 1, 'XXX');"));
    }

    @Test
    public void shouldGenerateInsertStatementWithData() {
        Domain domain = new Domain("TEST", "XXX", 1, 2);
        domain.add("colName", "colValue");

        assertThat(domain.toString(), is("insert into TESTs (parent_id, id, parent_tag, colName) values (2, 1, 'XXX', 'colValue');"));
    }

}