package st.example.spring.beans;

import org.springframework.beans.factory.annotation.Lookup;

public class SingletonLookupBean {
    @Lookup
    public PrototypeBean getPrototypeBean() {
        return null;
    }
}
