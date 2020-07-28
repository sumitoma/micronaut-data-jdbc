package micronaut.data;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import javax.validation.constraints.NotBlank;

@JdbcRepository(dialect = Dialect.H2)
public interface UserRepository extends CrudRepository<User, String> {
    //User findByFirstName(@NotBlank String name);

    AttributesDTO findByFirstName(@NotBlank String name);
}
