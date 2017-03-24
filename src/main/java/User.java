import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by raster on 18.03.17.
 */
public class User {

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    public User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Override
    public String toString() {
        return "User[" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ']';
    }
}
