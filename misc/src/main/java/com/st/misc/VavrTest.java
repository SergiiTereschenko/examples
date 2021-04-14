package com.st.misc;

import java.util.function.Supplier;

import io.vavr.Function4;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.isIn;
import static io.vavr.control.Try.run;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VavrTest {

    @Test
    public void option_try() {
        Option<Object> noneOption = Option.of(null);
        Option<Object> someOption = Option.of("val");

        assertEquals("None", noneOption.toString());
        assertEquals("Some(val)", someOption.toString());

        Try<Integer> result = Try.of(() -> 1 / 0);
        assertTrue(result.isFailure());

        Try<Integer> res2 = Try.of(() -> 1 / 0);
        int orElse = res2.getOrElse(-1);

        assertEquals(-1, orElse);
    }

    @Test(expected = ArithmeticException.class)
    public void givenBadCode_whenTryHandles() {
        Try<Integer> result = Try.of(() -> 1 / 0);
        result.getOrElseThrow((Supplier<ArithmeticException>) ArithmeticException::new);
    }

    @Test
    public void whenCreatesFunction() {
        Function4<String, String, String, String, String> concat =
            (a, b, c, d) -> a + b + c + d;

        String finalString = concat.apply(
            "Hello ", "world", "! ", "Learn");

        assertEquals("Hello world! Learn", finalString);
    }

    @Test
    public void whenCreatesVavrList() {
        List<Integer> intList = List.of(2, 1, 3).sorted();

        assertEquals(6, intList.sum());
        assertEquals(new Integer(1), intList.get(0));
        assertEquals(new Integer(2), intList.get(1));
        assertEquals(new Integer(3), intList.get(2));
    }

    @Test
    public void match_case() {
        int input = 2;
        String output = Match(input).of(
            Case($(1), "one"),
            Case($(2), "two"),
            Case($(3), "three"),
            Case($(), "?"));

        assertEquals("two", output);

        
        String arg = "--version";
        Match(arg).of(
            Case($(isIn("-h", "--help")), o -> run(() -> System.out.println("Help"))),
            Case($(isIn("-v", "--version")), o -> run(() -> System.out.println("Version"))),
            Case($(), o -> run(() -> {
                throw new IllegalArgumentException(arg);
            }))
        );
    }

    @Test
    public void whenValidation() {
        PersonValidator personValidator = new PersonValidator();
        Validation<Seq<String>, Person> valid = personValidator.validatePerson("John Doe", 30);
        Validation<Seq<String>, Person> invalid = personValidator.validatePerson("John? Doe!4", -1);

        assertEquals("Valid(Person(name=John Doe, age=30))", valid.toString());

        assertEquals("Invalid(List(Invalid characters in name: ?!4, Age must be at least 0))", invalid.toString());
    }


    class PersonValidator {
        String NAME_ERR = "Invalid characters in name: ";
        String AGE_ERR = "Age must be at least 0";

        public Validation<Seq<String>, Person> validatePerson(String name, int age) {
            return Validation.combine(validateName(name), validateAge(age)).ap(Person::new);
        }

        private Validation<String, String> validateName(String name) {
            String invalidChars = name.replaceAll("[a-zA-Z ]", "");
            return invalidChars.isEmpty() ? Validation.valid(name) : Validation.invalid(NAME_ERR + invalidChars);
        }

        private Validation<String, Integer> validateAge(int age) {
            return age < 0 ? Validation.invalid(AGE_ERR) : Validation.valid(age);
        }
    }


    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    class Person {
        private String name;
        private int age;
    }

}
