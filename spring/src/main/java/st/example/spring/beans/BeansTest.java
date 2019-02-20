package st.example.spring.beans;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

public class BeansTest {


    public static void main(String args[]) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        SingletonBean firstSingleton = context.getBean(SingletonBean.class);
//        SingletonLookupBean firstSingleton = context.getBean(SingletonLookupBean.class);
//        SingletonProviderBean firstSingleton = context.getBean(SingletonProviderBean.class);
        PrototypeBean firstPrototype = firstSingleton.getPrototypeBean();
        // get singleton bean instance one more time
        SingletonBean secondSingleton = context.getBean(SingletonBean.class);
//        SingletonLookupBean secondSingleton = context.getBean(SingletonLookupBean.class);
//        SingletonProviderBean secondSingleton = context.getBean(SingletonProviderBean.class);
        PrototypeBean secondPrototype = secondSingleton.getPrototypeBean();

//        Assert.isTrue(firstPrototype.equals(secondPrototype), "The same instance should be returned");
        Assert.isTrue(!firstPrototype.equals(secondPrototype), "not The same instance should be returned");
    }

}
