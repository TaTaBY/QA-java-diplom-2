package model;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Objects;

public class User {
    private String email;
    private String password;
    private String name;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User(String password, String name) {
        this.password = password;
        this.name = name;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, name);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public static User getRandom() {
        Faker faker = Faker.instance();
        String email = faker.internet().emailAddress();
        String password = RandomStringUtils.randomAlphabetic(10);
        String name = RandomStringUtils.randomAlphabetic(10);
        return new User(email, password, name);
    }

    public static User getRandomWithoutEmail() {
        String password = RandomStringUtils.randomAlphabetic(10);
        String name = RandomStringUtils.randomAlphabetic(10);
        return new User(password, name);
    }

    public static User getRandomWithoutPassword() {
        Faker faker = Faker.instance();
        String email = faker.internet().emailAddress();
        String name = RandomStringUtils.randomAlphabetic(10);
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        return user;
    }

    public static User getRandomWithoutName() {
        Faker faker = Faker.instance();
        String email = faker.internet().emailAddress();
        String password = RandomStringUtils.randomAlphabetic(10);
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
