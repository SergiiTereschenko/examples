package com.st.java8;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class StreamsTest {

    static class User {
        private String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    @Test
    public void getUserNameTest() {
        Assert.assertEquals("there is null value", getUserName(null));
        Assert.assertEquals("there is null value", getUserName(new User(null)));
        Assert.assertEquals("name", getUserName(new User("name")));
    }

    public String getUserName(User user) {
        Optional<User> user1 = Optional.ofNullable(user);
        Optional<String> name = user1.map(User::getName);
        String val = name.orElseGet(() -> "there is null value");
        return val;
    }
}
