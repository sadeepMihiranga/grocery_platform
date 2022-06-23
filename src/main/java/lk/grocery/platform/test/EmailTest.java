package lk.grocery.platform.test;

import lk.grocery.platform.GroceryPlatformApplication;
import lk.grocery.platform.util.EmailSenderService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GroceryPlatformApplication.class)
public class EmailTest {

    @Autowired
    private EmailSenderService emailSenderService;

    @Ignore
    @Test
    public void testMail() {
        //emailSenderService.sendEmail();
    }
}
