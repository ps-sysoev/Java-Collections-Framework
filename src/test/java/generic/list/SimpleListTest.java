package generic.list;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleListTest {

    @Test
    public void simpleListConstructorTestWhenEmpty() {
        ListContainer<String> expectedSimpleList = new SimpleList<>();

        assertEquals(0, expectedSimpleList.size());
    }

    @Test
    public void simpleListConstructorTestWhenNotEmpty() {
        ListContainer<String> expectedSimpleList = new SimpleList<>(2);
        expectedSimpleList.add("param-1");
        expectedSimpleList.add("param-2");

        assertEquals(2, expectedSimpleList.size());
    }

    @Test
    public void simpleListGrowTest() {
        ListContainer<String> expectedSimpleList = new SimpleList<>(2);
        expectedSimpleList.add("param-1");
        expectedSimpleList.add("param-2");
        expectedSimpleList.add("param-3");
        expectedSimpleList.add("param-4");

        assertEquals(4, expectedSimpleList.size());
    }

    @Test
    public void simpleListGetTest() {
        ListContainer<String> expectedSimpleList = new SimpleList<>();
        expectedSimpleList.add("param-1");
        expectedSimpleList.add("param-2");
        expectedSimpleList.add("param-3");

        assertEquals(3, expectedSimpleList.size());

        assertEquals("param-1", expectedSimpleList.get(0));
        assertEquals("param-2", expectedSimpleList.get(1));
        assertEquals("param-3", expectedSimpleList.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void simpleListGetOutOfRangeFirstTest() {
        ListContainer<String> expectedSimpleList = new SimpleList<>(1);
        expectedSimpleList.add("param-1");

        expectedSimpleList.get(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void simpleListGetOutOfRangeSecondTest() {
        ListContainer<String> expectedSimpleList = new SimpleList<>();
        expectedSimpleList.add("param-1");
        expectedSimpleList.add("param-2");

        expectedSimpleList.get(2);
    }

    @Test
    public void simpleListRemoveTest() {
        ListContainer<String> expectedSimpleList = new SimpleList<>();
        expectedSimpleList.add("param-1");
        expectedSimpleList.add("param-2");
        expectedSimpleList.add("param-3");

        String expectedRemovalResult = expectedSimpleList.remove(1);

        assertEquals(expectedRemovalResult, "param-2");

        assertEquals(2, expectedSimpleList.size());

        assertEquals("param-1", expectedSimpleList.get(0));
        assertEquals("param-3", expectedSimpleList.get(1));
    }

    @Test
    public void simpleListSetTest() {
        ListContainer<String> expectedSimpleList = new SimpleList<>();
        expectedSimpleList.add("param-1");
        expectedSimpleList.add("param-2");
        expectedSimpleList.add("param-3");

        expectedSimpleList.set(1, "new-param-2");

        assertEquals(3, expectedSimpleList.size());

        assertEquals("param-1", expectedSimpleList.get(0));
        assertEquals("new-param-2", expectedSimpleList.get(1));
        assertEquals("param-3", expectedSimpleList.get(2));
    }

    @Test
    public void simpleListIteratorTest() {
        ListContainer<String> expectedSimpleList = new SimpleList<>();
        expectedSimpleList.add("param-1");
        expectedSimpleList.add("param-2");
        expectedSimpleList.add("param-3");

        for (int i = 1; i <= expectedSimpleList.size(); i++) {
            assertEquals("param-" + i, expectedSimpleList.get(i - 1));
        }
    }
}