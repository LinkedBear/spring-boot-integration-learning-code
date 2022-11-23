package demo;

import com.linkedbear.boot.junit.SpringBootJUnitApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringBootJUnitApplication.class)
public class OuterTest {
    
    @Test
    public void test4() throws Exception {
        System.out.println("Outer test run ......");
    }
}
