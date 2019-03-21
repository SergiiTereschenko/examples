package com.st.loginEvent;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LoginEventTest {

    //task onedome
    @Test
    public void test() {
        List<LoginEvent> loginEvents = new ArrayList<>();

//        LocalDateTime nowLDT = LocalDateTime.now();
//        OffsetDateTime offsetDateTime = OffsetDateTime.now();
//        ZonedDateTime toZonedDateTime = offsetDateTime.toZonedDateTime();
//        Instant nowInstant = Instant.now();
//        System.out.println("nowLDT: " + nowLDT + " ; nowIstant: " + nowInstant);
//        System.out.println("nowLDT: " + nowLDT + " ; offsetDateTime: " + offsetDateTime);
//        System.out.println("nowLDT: " + nowLDT + " ; toZonedDateTie: " + toZonedDateTime);

        loginEvents.add(new LoginEvent(LocalDateTime.of(2019, Month.JANUARY, 1, 0, 0), "a"));
        loginEvents.add(new LoginEvent(LocalDateTime.of(2019, Month.JANUARY, 1, 10, 0), "a"));
        loginEvents.add(new LoginEvent(LocalDateTime.of(2019, Month.JANUARY, 2, 10, 0), "a"));
        loginEvents.add(new LoginEvent(LocalDateTime.of(2019, Month.JANUARY, 3, 9, 0), "a"));
        loginEvents.add(new LoginEvent(LocalDateTime.of(2019, Month.JANUARY, 3, 20, 0), "a"));
        loginEvents.add(new LoginEvent(LocalDateTime.of(2019, Month.JANUARY, 3, 8, 0), "b"));
        loginEvents.add(new LoginEvent(LocalDateTime.of(2019, Month.JANUARY, 3, 19, 0), "b"));
        loginEvents.add(new LoginEvent(LocalDateTime.of(2019, Month.JANUARY, 4, 10, 0), "b"));
        loginEvents.add(new LoginEvent(LocalDateTime.of(2019, Month.JANUARY, 20, 20, 0), "c"));
        loginEvents.add(new LoginEvent(LocalDateTime.of(2019, Month.JANUARY, 21, 21, 0), "c"));
        loginEvents.add(new LoginEvent(LocalDateTime.of(2019, Month.JANUARY, 21, 22, 0), "b"));
        loginEvents.add(new LoginEvent(LocalDateTime.of(2019, Month.JANUARY, 22, 22, 0), "b"));
        loginEvents.add(new LoginEvent(LocalDateTime.of(2019, Month.JANUARY, 22, 23, 0), "b"));
        loginEvents.add(new LoginEvent(LocalDateTime.of(2019, Month.JANUARY, 23, 21, 0), "b"));

//        loginEvents.sort((a, b) -> Long.compare(a.loginDate, b.loginDate));

        List<String> usersToDays = findUserLoggedIn3DaysInRow(loginEvents);
        System.out.println(usersToDays);
    }

    private List<String> findUserLoggedIn3DaysInRow(List<LoginEvent> events) {
        Map<String, EventsInfo> usersToDays = new HashMap<>();

        for (LoginEvent event : events) {
            if (!usersToDays.containsKey(event.email)) {
                usersToDays.putIfAbsent(event.email, new EventsInfo(event.loginDate));
            } else {
                usersToDays.get(event.email).setLastVisit(event.loginDate);
            }
        }

        List<String> collect = usersToDays.entrySet().stream()
                .filter(e -> e.getValue().is3DaysInRow())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return collect;
    }
}

class EventsInfo {
    LocalDateTime lastVisit;
    int days = 1;

    public EventsInfo(LocalDateTime lastDate) {
        this.lastVisit = lastDate;
    }

    public void setLastVisit(LocalDateTime nextVisit) {
//        long between = ChronoUnit.HOURS.between(lastVisit, nextVisit);
        long between = lastVisit.until(nextVisit, ChronoUnit.HOURS);


        if (between > 24) {
            days = 1;
        } else if (lastVisit.toLocalDate().until(nextVisit.toLocalDate(), ChronoUnit.DAYS) == 1) {
            days++;
        }
        this.lastVisit = nextVisit;
    }

    public boolean is3DaysInRow() {
        return days >= 3;
    }
}

class LoginEvent {
    LocalDateTime loginDate;
    String email;

    public LoginEvent(LocalDateTime loginDate, String email) {
        this.loginDate = loginDate;
        this.email = email;
    }
}