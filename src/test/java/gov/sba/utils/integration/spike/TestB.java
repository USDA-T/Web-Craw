package gov.sba.utils.integration.spike;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TestB {
    @Test
    public void c() {
        org.junit.Assert.assertEquals("2", "2");
        System.out.println("hello, c");
    }
    
    // Can be flagged with more than one category
    // @Category({gov.sba.utils.integration.StableTests.class, gov.sba.utils.integration.UnstableTests.class})
    @Test
    public void d() {
        org.junit.Assert.assertEquals("a", "a");
        System.out.println("hello, d");
    }
}
