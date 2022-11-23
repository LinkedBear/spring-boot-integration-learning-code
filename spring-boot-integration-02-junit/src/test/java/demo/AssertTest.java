package demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class AssertTest {
    
    @Test
    public void testSimple() {
        // 最简单的断言，断言计算值与预期值是否相等
    	int num = 3 + 5;
        Assertions.assertEquals(num, 8);
        
        double result = 10.0 / 3;
        // 断言计算值是否在浮点数的指定范围内上下浮动
        Assertions.assertEquals(result, 3, 0.5);
        // 如果浮动空间不够，则会断言失败
        // Assertions.assertEquals(result, 3, 0.2);
        // 传入message可以自定义错误提示信息
        Assertions.assertEquals(result, 3, 0.2, "计算数值偏差较大！");
    
        // 断言两个对象是否是同一个
        Object o1 = new Object();
        Object o2 = o1;
        Object o3 = new Object();
        Assertions.assertSame(o1, o2);
        Assertions.assertSame(o1, o3);
    
        // 断言两个数组的元素是否完全相同
        String[] arr1 = {"aa", "bb"};
        String[] arr2 = {"aa", "bb"};
        String[] arr3 = {"bb", "aa"};
        Assertions.assertArrayEquals(arr1, arr2);
        Assertions.assertArrayEquals(arr1, arr3);
    }
    
    @Test
    public void testCombination() {
        Assertions.assertAll(
                () -> {
                    int num = 3 + 5;
                    Assertions.assertEquals(num, 8);
                },
                () -> {
                    String[] arr1 = {"aa", "bb"};
                    String[] arr2 = {"bb", "aa"};
                    Assertions.assertArrayEquals(arr1, arr2);
                }
        );
    }
    
    @Test
    public void testException() {
        Assertions.assertThrows(ArithmeticException.class, () -> {
            int i = 1 / 0;
        });
    }
    
    @Test
    public void testTimeout() {
        Assertions.assertTimeout(Duration.ofMillis(500), () -> {
            System.out.println("testTimeout run ......");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("testTimeout finished ......");
        });
    }
    
    @Test
    public void testFail() {
    	if (ZonedDateTime.now().getHour() > 12) {
    	    Assertions.fail();
    	}
    }
    
    @Test
    public void testAssumptions() {
    	int num = 3 + 5;
        Assumptions.assumeTrue(num < 10);
        
        Assertions.assertTrue(10 - num > 0);
    }
    
    @Test
    public void testArgumentInject(TestInfo testInfo) {
        System.out.println(testInfo.getTestClass().get().getName());
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"aa", "bb", "cc"})
    public void testSimpleParameterized(String value) throws Exception {
        System.out.println(value);
    	Assertions.assertTrue(value.length() < 3);
    }
    
    @ParameterizedTest
    @MethodSource("dataProvider")
    public void testDataStreamParameterized(Integer value) throws Exception {
        System.out.println(value);
        Assertions.assertTrue(value < 10);
    }
    
    private static Stream<Integer> dataProvider() {
        return Stream.of(1, 2, 3, 4, 5);
    }
}
