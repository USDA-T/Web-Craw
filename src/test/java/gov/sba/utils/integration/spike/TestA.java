package gov.sba.utils.integration.spike;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TestA {
    @Test
    public void a() {
        System.out.println("hello, a");
    }
    
    //@Category(gov.sba.utils.integration.UnstableTests.class)
    @Test
    public void b() {
        // Note: example of tests that is not stable!
        org.junit.Assert.assertEquals("2", "3");
        System.out.println("hello, b");
    }
}
