package com.st.misc;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

public class ShuffleTheDeck {

    @Test
    public void test() {
        List<Integer> deck = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
            20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36);
        System.out.println("Init    : " + deck);
        shuffleDeck(deck);
        System.out.println("Shuffled: " + deck);
    }

    public void shuffleDeck(List<Integer> deck) {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < deck.size(); i++) {
            Integer card = deck.get(i);
            int newPosition = random.nextInt(deck.size());
            Integer movedCard = deck.get(newPosition);
            deck.set(newPosition, card);
            deck.set(i, movedCard);
        }
    }
}
