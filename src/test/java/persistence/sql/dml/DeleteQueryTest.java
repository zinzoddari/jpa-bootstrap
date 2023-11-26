package persistence.sql.dml;

import static org.assertj.core.api.Assertions.assertThat;
import static persistence.sql.common.meta.MetaUtils.Columns을_생성함;
import static persistence.sql.common.meta.MetaUtils.TableName을_생성함;

import domain.Person;
import domain.SelectPerson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import persistence.entity.EntityMeta;
import persistence.sql.common.meta.Columns;
import persistence.sql.common.meta.TableName;

class DeleteQueryTest {
    private final static Query query = Query.getInstance();

    @Test
    @DisplayName("@Column 없는 @Id 값 조건으로 delete문 생성")
    void deleteQuery() {
        //given
        final Long id = 3L;
        final String expectedQuery = String.format("DELETE FROM users WHERE id = %s", id);

        Class<Person> clazz = Person.class;
        final TableName tableName = TableName을_생성함(clazz);
        final Columns columns = Columns을_생성함(clazz);

        final EntityMeta entityMeta = EntityMeta.make(tableName, columns);

        //when
        String q = query.delete(entityMeta, 3L);

        //then
        assertThat(q).isEqualTo(expectedQuery);
    }

    @Test
    @DisplayName("@Column 있는 @Id 값 조건으로 delete문 생성")
    void deleteQueryByColumn() {
        //given
        final Long id = 3L;
        final String expectedQuery = String.format("DELETE FROM selectPerson WHERE select_person_id = %s", id);

        Class<SelectPerson> clazz = SelectPerson.class;
        final TableName tableName = TableName을_생성함(clazz);
        final Columns columns = Columns을_생성함(clazz);

        final EntityMeta entityMeta = EntityMeta.make(tableName, columns);

        //when
        String q = query.delete(entityMeta, 3L);

        //then
        assertThat(q).isEqualTo(expectedQuery);
    }
}
