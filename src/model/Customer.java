package model;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Customer {
    private String firstName;
    private String lastName;
    private String email;

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        validateEmail(email);
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public void validateEmail(String email) {
        Matcher matcher = Pattern.compile("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}(.[a-z]{2,3})+$|^$",
                Pattern.CASE_INSENSITIVE).matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid Email");
        }
    }



    @Override
    public String toString() {
        return String.format("Customer\nfirstName=%s\nlastName=%s\nemail=%s\n", firstName, lastName, email);
    }

}
