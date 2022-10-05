package generic.list;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleUniqueListTest {

    @Test
    public void simpleUniqueListConstructorTestWhenEmpty() {
        ListContainer<String> expectedSimpleUniqueList = new SimpleUniqueList<>();

        assertEquals(0, expectedSimpleUniqueList.size());
    }

    @Test
    public void simpleUniqueListConstructorTestWhenNotEmpty() {
        ListContainer<String> expectedSimpleUniqueList = new SimpleUniqueList<>(2);
        expectedSimpleUniqueList.add("param-1");
        expectedSimpleUniqueList.add("param-2");

        assertEquals(2, expectedSimpleUniqueList.size());
    }

    @Test
    public void simpleUniqueListGrowTest() {
        ListContainer<String> expectedSimpleUniqueList = new SimpleUniqueList<>(2);
        expectedSimpleUniqueList.add("param-1");
        expectedSimpleUniqueList.add("param-2");
        expectedSimpleUniqueList.add("param-3");
        expectedSimpleUniqueList.add("param-4");

        assertEquals(4, expectedSimpleUniqueList.size());
    }

    @Test
    public void simpleUniqueListGetTest() {
        ListContainer<String> expectedSimpleUniqueList = new SimpleUniqueList<>();
        expectedSimpleUniqueList.add("param-1");
        expectedSimpleUniqueList.add("param-2");
        expectedSimpleUniqueList.add("param-3");

        assertEquals(3, expectedSimpleUniqueList.size());

        assertEquals("param-1", expectedSimpleUniqueList.get(0));
        assertEquals("param-2", expectedSimpleUniqueList.get(1));
        assertEquals("param-3", expectedSimpleUniqueList.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void simpleUniqueListGetOutOfRangeFirstTest() {
        ListContainer<String> expectedSimpleUniqueList = new SimpleUniqueList<>(1);
        expectedSimpleUniqueList.add("param-1");

        expectedSimpleUniqueList.get(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void simpleUniqueListGetOutOfRangeSecondTest() {
        ListContainer<String> expectedSimpleUniqueList = new SimpleUniqueList<>();
        expectedSimpleUniqueList.add("param-1");
        expectedSimpleUniqueList.add("param-2");

        expectedSimpleUniqueList.get(2);
    }

    @Test
    public void simpleUniqueListRemoveTest() {
        ListContainer<String> expectedSimpleUniqueList = new SimpleUniqueList<>();
        expectedSimpleUniqueList.add("param-1");
        expectedSimpleUniqueList.add("param-2");
        expectedSimpleUniqueList.add("param-3");

        String expectedRemovalResult = expectedSimpleUniqueList.remove(1);

        assertEquals(expectedRemovalResult, "param-2");

        assertEquals(2, expectedSimpleUniqueList.size());

        assertEquals("param-1", expectedSimpleUniqueList.get(0));
        assertEquals("param-3", expectedSimpleUniqueList.get(1));
    }

    @Test
    public void simpleUniqueListSetTestWhenNewValue() {
        ListContainer<String> expectedSimpleUniqueList = new SimpleUniqueList<>();
        expectedSimpleUniqueList.add("param-1");
        expectedSimpleUniqueList.add("param-2");
        expectedSimpleUniqueList.add("param-3");

        expectedSimpleUniqueList.set(1, "new-param-2");

        assertEquals(3, expectedSimpleUniqueList.size());

        assertEquals("param-1", expectedSimpleUniqueList.get(0));
        assertEquals("new-param-2", expectedSimpleUniqueList.get(1));
        assertEquals("param-3", expectedSimpleUniqueList.get(2));
    }

    @Test
    public void simpleUniqueListAddTestWhenDuplicateValue() {
        ListContainer<String> expectedSimpleUniqueList = new SimpleUniqueList<>(2);
        expectedSimpleUniqueList.add("param-1");
        expectedSimpleUniqueList.add("param-2");
        expectedSimpleUniqueList.add("param-2");

        assertEquals(2, expectedSimpleUniqueList.size());

        assertEquals("param-1", expectedSimpleUniqueList.get(0));
        assertEquals("param-2", expectedSimpleUniqueList.get(1));
    }

    @Test
    public void simpleUniqueListSetTestWhenDuplicateValue() {
        ListContainer<String> expectedSimpleUniqueList = new SimpleUniqueList<>();
        expectedSimpleUniqueList.add("param-1");
        expectedSimpleUniqueList.add("param-2");
        expectedSimpleUniqueList.add("param-3");

        expectedSimpleUniqueList.set(2, "param-2");

        assertEquals(3, expectedSimpleUniqueList.size());

        assertEquals("param-1", expectedSimpleUniqueList.get(0));
        assertEquals("param-2", expectedSimpleUniqueList.get(1));
        assertEquals("param-3", expectedSimpleUniqueList.get(2));
    }

    @Test
    public void simpleListIteratorTest() {
        ListContainer<String> expectedSimpleUniqueList = new SimpleUniqueList<>();
        expectedSimpleUniqueList.add("param-1");
        expectedSimpleUniqueList.add("param-2");
        expectedSimpleUniqueList.add("param-3");

        for (int i = 1; i <= expectedSimpleUniqueList.size(); i++) {
            assertEquals("param-" + i, expectedSimpleUniqueList.get(i - 1));
        }
    }
}