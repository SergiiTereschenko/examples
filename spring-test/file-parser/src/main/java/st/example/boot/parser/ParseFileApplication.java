package st.example.boot.parser;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import st.example.boot.parser.storage.StorageProperties;

@Slf4j
@EnableConfigurationProperties(StorageProperties.class)
@SpringBootApplication
public class ParseFileApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(ParseFileApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Application started " + ParseFileApplication.class.getCanonicalName());
    }

//    @Bean
//    public MultipartResolver multipartResolver() {
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//        multipartResolver.setMaxUploadSize(-1);
//        return multipartResolver;
//    }
//
//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        return new MultipartConfigElement("");
//    }


//    Useful info
//    To get the working directory (project root) path you can use this code:
//    File directory = new File("./").getCanonicalPath();
}
