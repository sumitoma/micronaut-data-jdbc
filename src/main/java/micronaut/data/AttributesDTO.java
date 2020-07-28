package micronaut.data;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class AttributesDTO {
    private final User.Status status;
    private final String firstName;

    public AttributesDTO(User.Status status, String firstName) {
        this.status = status;
        this.firstName = firstName;
    }

    public User.Status getStatus() {
        return status;
    }

    public String getFirstName() {
        return firstName;
    }
}
