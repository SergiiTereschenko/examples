package st.example.boot.parser.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import st.example.boot.parser.model.Record;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class Mapper {
    private final String del = ",";
    private final AtomicInteger conter = new AtomicInteger(0);

    public Record toRecord(String line) {
        String[] split = line.split(del);
        int age = StringUtils.isNumeric(split[1]) ? Integer.parseInt(split[1]) : 0;
        Record record = Record.builder()
                .id(conter.incrementAndGet())
                .name(split[0]).age(age)
                .build();
        return record;
    }
}
