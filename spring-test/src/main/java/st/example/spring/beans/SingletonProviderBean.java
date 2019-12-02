package st.example.spring.beans;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SingletonProviderBean {

    @Autowired
//    private Provider<PrototypeBean> myPrototypeBeanProvider;
    private ObjectFactory<PrototypeBean> prototypeFactory;

    public PrototypeBean getPrototypeBean() {
//        return myPrototypeBeanProvider.get();
        return prototypeFactory.getObject();
    }
}