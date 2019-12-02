package st.example.boot.parser.service;

import lombok.AllArgsConstructor;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import st.example.boot.parser.model.Record;
import st.example.boot.parser.repositiry.RecordRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class FileParser {
    private RecordRepository recordRepository;
    private Validator validator;
    private Mapper mapper;
    private final String fileName = "data_s.csv";


    public void parse() throws IOException {
        readFileViaFiles();
    }

    public void parseInSeparateThread(FileItemIterator iter) throws Exception {
        System.out.println("Thread: " + Thread.currentThread().getName() + "; Started");
        Callable<String> callableTask = () -> {
            readFileViaInputStream(iter);
            return "Finished";
        };
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(callableTask);
        System.out.println("Thread: " + Thread.currentThread().getName() + "; " + future.get());
        executorService.shutdown();
    }

    public void parse3(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        parseInputStream(inputStream);
    }

    private void parseInputStream(InputStream inputStream) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            Stream<String> lines = br.lines();
            lines.filter(validator::validate)
                    .map(mapper::toRecord)
                    .forEach(this::save);
        }
    }

    private void readFileViaInputStream(FileItemIterator iter) throws IOException, FileUploadException {
        System.out.println("Thread: " + Thread.currentThread().getName() + "; IN readFileViaInputStream()");
        if (!iter.hasNext()) {
            return;
//            throw new IllegalArgumentException("FileItemIterator was empty");
        }
        FileItemStream item = iter.next();
        InputStream in = item.openStream();
        parseInputStream(in);
    }

    private void readFileViaFiles() throws IOException {
        File file = ResourceUtils.getFile("classpath:" + fileName);
//        BufferedReader bufferedReader = Files.newBufferedReader(file.toPath());
//        bufferedReader.lines();

        Stream<String> lines = Files.lines(file.toPath());
        lines.filter(validator::validate)
                .map(mapper::toRecord)
                .forEach(this::save);
    }

    private void save(Record record) {
        recordRepository.save(record);
    }
}
