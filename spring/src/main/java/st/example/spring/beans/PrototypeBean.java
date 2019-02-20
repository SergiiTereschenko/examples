package st.example.spring.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrototypeBean {
    private static final Logger logger = LoggerFactory.getLogger(PrototypeBean.class);

    public PrototypeBean() {
        logger.info("PrototypeBean created.");
    }
}
