package com.st.interviewtasks.xpservice.playtika;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class SubscriberListener {
    long userId;
    int userXp;
//    List<String> levelsFired = new ArrayList<>();

//    public SubscriberListener(String id) {
//        this.userId = Long.parseLong(id);
//    }

//    public SubscriberListener(long id) {
//        this.userId = id;
//    }

}
