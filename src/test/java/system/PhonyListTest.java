package system;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PhonyListTest {

    private PhonyList<Integer> list;

    private PhonyList<Integer> list(Integer... content) {
        PhonyList<Integer> list = new PhonyList<>();
        for (Integer i : content)
            list.add(i);
        return list;
    }

    private PhonyList<Integer> createIntegerPhonyList(int size) {
        PhonyList<Integer> list = new PhonyList<Integer>();
        for (int i = 0; i < size; i++) {
            Integer entier = new Integer(i);
            list.add(entier);
        }
        return list;
    }

    @Before
    public void setUpNewIntegerPhonyList() {
        list = new PhonyList<Integer>();
    }

    /*

    clear :OK
    indexOf :OK
    get :OK
    set :OK - Bug: corrected
    isEmpty : OK
    add : doing didi
    addAll : OK
    remove : doing
    fastRemove : doing
    contains : OK
    removeRange
    rangeCheck
    rangeCheckForAdd
    removeAll

    */

    /**
     * Tests the "get" method with a one element list.
     *
     * @type Functional
     * @input [0] o=0
     * @oracle The obtained list must return one element of type Integer of value 0, at position 0
     * @passed Yes
     * @see PhonyList#get(int)
     */
    @Test
    public void get_OneElementTest() {
        Integer entier = new Integer(0);
        list.add(entier);
        assertEquals("0", list.get(0).toString());
        assertEquals(Integer.class, list.get(0).getClass());
    }


    /**
     * Tests the "get" method with a 3 elements list.
     *
     * @type Functional
     * @input [0, 1, 2] o=0,1,2
     * @oracle The obtained list must return 3 elements of type Integer, value 0,1,2, at position 0,1,2.
     * @passed Yes
     * @see PhonyList#get(int)
     */
    @Test
    public void get_ThreeElementTest() {
        list = createIntegerPhonyList(3);
        assertEquals("0", list.get(0).toString());
        assertEquals("1", list.get(1).toString());
        assertEquals("2", list.get(2).toString());
    }

    /**
     * Tests the "get" method with an empty list.
     *
     * @type Functional
     * @input [] o=0
     * @oracle Must rise an exception of type IndexOutOfBoundsException
     * @passed Yes
     * @see PhonyList#get(int)
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void get_EmptyListTest() {
        assertEquals(null, list.get(0).toString());
    }

    /**
     * Tests the "set" method with a one element list.
     *
     * @type Functional
     * @input [0] o=0
     * @oracle The obtained list must have one Integer of value 1
     * @passed Yes
     * @see PhonyList#set(int, Object)
     */
    @Test
    public void set_OneElementListTest() {
        Integer entier = new Integer(0);
        list.add(entier);
        assertEquals("0", list.get(0).toString());
        list.set(0, new Integer(1));
        assertEquals("1", list.get(0).toString());
    }

    /**
     * Tests the "set" method, changing all the elements of a list.
     *
     * @type Functional
     * @input [0..50] o=0,25,50
     * @oracle The obtained list must be the same list, with all values incremented by one
     * @passed Yes
     * @see PhonyList#set(int, Object)
     */
    @Test
    public void set_IncrementAllElementsOfListTest() {
        int size = 50;
        list = createIntegerPhonyList(size);
        for (int i = 0; i < size; i++) {
            list.set(i, new Integer(i + 1));
        }
        assertEquals("1", list.get(0).toString());
        assertEquals("25", list.get(24).toString());
        assertEquals("50", list.get(49).toString());
        assertEquals(50, list.size());
    }

    /**
     * Tests the "set" method, attempt to change a nonexistent value.
     *
     * @type Functional
     * @input [0] o=0
     * @oracle Must raise an IndexOutOfBoundsException
     * @passed True
     * @see PhonyList#set(int, Object)
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void set_attemptToChangeNonExistentElementTest() {
        list.set(0, new Integer(0));
    }

    /**
     * Tests the "addAll" method, with an insertion of a list in an empty list
     *
     * @type Functional
     * @input [1,2,3] o=0,1,2
     * @oracle the obtained list must be the included list
     * @passed Yes
     * @see PhonyList#addAll(int, java.util.Collection)
     */
    @Test
    public void addAll_putElementInEmptyListTest() {
        PhonyList<Integer> listToAdd = list(1, 2, 3);
        list.addAll(0, listToAdd);
        assertEquals("1", list.get(0).toString());
        assertEquals("2", list.get(1).toString());
        assertEquals("3", list.get(2).toString());
        assertEquals(listToAdd.size(),list.size());
    }

    /**
     * Tests the "addAll" method, with an insertion of a list in a list
     *
     * @type Functional
     * @input [54,93,16,null,17,null,89,22,36,30] & [1,2,3]
     * @oracle the obtained new list must be the same than the "listOracle"
     * @passed Yes
     * @see PhonyList#addAll(int, java.util.Collection)
     */
    @Test
    public void addAll_putElementListTest() {
        PhonyList<Integer> listToAdd = list(1, 2, 3);
        list = list(54, 93, 16, null, 17, null, 89, 22, 36, 30);
        PhonyList<Integer> listOracle = list(54, 93, 16, 1, 2, 3, null, 17, null, 89, 22, 36, 30);
        int size = list.size();
        list.addAll(3, listToAdd);
        int i=0;
        for(Object o: list){
            if (o!=null) {
                assertEquals(o.toString(), listOracle.get(i).toString());
            }
            i++;
        }
        assertEquals("1", list.get(3).toString());
        assertEquals("2", list.get(4).toString());
        assertEquals("3", list.get(5).toString());
        assertEquals(size+listToAdd.size(),list.size());
    }


    /**
     * Tests the "isEmpty" method with an empty list.
     *
     * @see PhonyList#isEmpty()
     * @type Functional
     * @input []
     * @oracle The list is empty, it must return true.
     * @passed Yes
     */
    @Test
    public void isEmpty_TrueTest(){
        assertTrue(list.isEmpty());
    }

    /**
     * Tests the "isEmpty" method with a non-empty list.
     *
     * @see PhonyList#isEmpty()
     * @type Functional
     * @input [1]
     * @oracle The list contains one element, it must return false.
     * @passed Yes
     */
    @Test
    public void isEmpty_FalseTest(){
        list.add(1);
        assertFalse(list.isEmpty());
    }

    /**
     * Test the "contains" method
     * @see system.PhonyList#contains(Object)
     * @type Functional
     * @oracle The list contains one element, it must return false.
     * @passed Yes
     */
    @Test
    public void contains_ObjectTest() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertTrue(list.contains(2));
        assertFalse(list.contains(17));
    }

    /**
     * Tests the "add" method with a size list.
     *
     * @see PhonyList#add(Object)
     * @type Functional
     * @input [1..5]
     * @oracle the obtained list size must be incremented by one after one add
     * @passed Yes
     **/
    @Test
    public void add_SizeIncrementTest(){
        list = list(18, 62, 3, 84, 54);
        int size = list.size();
        list.add(85);
        assertEquals(6, list.size());
    }
    /**
     * Tests the "add" method with .
     *
     * @see PhonyList#add(Object)
     * @type Functional
     * @input [1..4]
     * @oracle find object who add in the list
     * @passed Yes
     **/
    @Test
    public void add_FindObjectTest(){
        list = list(1,3,5,8);
        int index = list.size();
        list.add(50);
        assertEquals("50", list.get(4).toString());
        assertEquals("1", list.get(0).toString());
        assertEquals("3", list.get(1).toString());

    }



}
