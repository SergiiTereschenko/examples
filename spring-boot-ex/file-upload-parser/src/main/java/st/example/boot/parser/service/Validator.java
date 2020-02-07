package st.example.boot.parser.service;

import org.springframework.stereotype.Service;

@Service
public class Validator {

    public boolean validate(String line) {
         return line.trim().length() < 50;
    }
}
