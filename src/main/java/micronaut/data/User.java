package micronaut.data;


import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

import javax.annotation.Nullable;

@MappedEntity
public class User {

    @Id
    private final String id;
    private final String email;
    private final String firstName;
    @Nullable
    private final String lastName;
    @Nullable
    private final String password;
    private final Status status;

    public User(String id, String email, String firstName, @Nullable String lastName, @Nullable String password) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.status = Status.ACTIVE;
    }

    public User(String id, String email, String firstName, @Nullable String lastName, @Nullable String password, Status status) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public Status getStatus() {
        return status;
    }

    enum Status{
        ACTIVE, EXPIRED
    }
}
