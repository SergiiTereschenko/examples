package com.st.uniLecs;

public class Parentheses {

    public static void main(String[] args) {
        System.out.println("UniLecs");
        generateBracketSequence("", 2, 0, 0);
    }


    private static void generateBracketSequence(String prefix, int n, int open, int close) {
        if (open + close == 2 * n) {
            System.out.println(prefix);
        } else {
            /* Открывающую скобку можно добавить всегда, если в префиксе было использовано менее n открывающих скобок. */
            if (open < n) {
                generateBracketSequence(prefix + "(", n, open + 1, close);
            }

            /* Закрывающую скобку можно добавить всегда, если в префиксе число открывающих скобок больше, чем число закрывающих. */
            if (open > close) {
                generateBracketSequence(prefix + ")", n, open, close + 1);
            }
        }
    }
}
