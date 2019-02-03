package st.examples.XP;

import org.junit.Test;

public class XPServiceTest {

    XPService xpService = new XPServiceImpl();

    @Test
    public void testStoreXp() {

        String id = "123";
        int xp  = 1;
        xpService.storeXp(id, xp);
    }

}
