package system;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

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
    add : OK
    addAll : OK pas à 100%
    remove : OK
    fastRemove : OK
    contains : OK
    size : OK
    removeRange
    rangeCheck
    rangeCheckForAdd
    removeAll : Doing - Bug: not fixed

    */
    /**
     * Test method for {@link system.PhonyList#clear()}.
     */
    @Test
    public void clear_Test() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.clear();
        assertEquals(0, list.size());
    }

    /**
     * Tests lastIndexOf(). First test, add an string (the target) after 3 others and search its place (it must be 3).
     * Add a clone, and check its place is the same. And remove 2 entries (the first and the original) and check the position is 2
     * @see system.PhonyList#indexOf(Object)
     * @type Functional
     * @input String
     * @oracle Must return "true"
     * @passed yes
     */
    @Test
    public void testIndexOf() {
        PhonyList<Object> phonyList = new PhonyList<>();
        String str1 = new String("Helloworld");
        phonyList.add("0");
        phonyList.add("1");
        phonyList.add("2");
        phonyList.add(str1);
        assertEquals(phonyList.indexOf(str1)==3, true);

        String str2 = str1;
        phonyList.add(str2);
        assertEquals(phonyList.indexOf(str2)==3, true);

        phonyList.remove(str1);
        phonyList.remove("0");
        assertEquals(phonyList.indexOf(str2)==2, true);
    }

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
     * @passed Yes
     * @see PhonyList#set(int, Object)
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void set_attemptToChangeNonExistentElementTest() {
        list.set(0, new Integer(0));
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
     * Tests the "add" method with an add of a null element
     *
     * @see PhonyList#add(Object)
     * @type Functional
     * @input [Null]
     * @oracle the obtained list must be of size 1
     * @passed Yes
     **/
    @Test
    public void add_NullElementTest(){
        list.add(null);
        assertEquals(1, list.size());
    }

    /**
     * Tests the "add" method with a simple example
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
     * Tests the "addAll" method, with an insertion of a empty list in a list
     *
     * @type Functional
     * @input [] o=0
     * @oracle the obtained list must be of size 0
     * @passed Yes
     * @see PhonyList#addAll(int, java.util.Collection)
     */
    @Test
    public void addAll_putEmptyListInListTest() {
        PhonyList<Integer> listToAdd = new PhonyList<>();
        list.addAll(0, listToAdd);
        assertEquals(listToAdd.size(), list.size());
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
     * Test "remove" method to observe the list size
     * @see system.PhonyList#remove(int)
     * @type Functional
     * @input [1,2,3,4]
     * @oracle The length of the new list must be decremented by one
     * @passed Yes
     **/
    @Test
    public void Remove_SizeTest() {

        Integer i = new Integer(4);
        int oracleSize = 3;

        list = list(1,2,3);
        list.add(i);
        int oldSize = list.size();
        list.remove(i);

        assertEquals(oracleSize, list.size());
        assertEquals(oracleSize, oldSize-1);

        assertEquals(list.get(0).toString(), "1");
        list.remove(null);
        assertEquals(list.size(),3);
        list.add(null);
        int index = list.size();
        list.remove(null);
        assertEquals(index-1, list.size());

    }

    /**
     * Test "remove" method to observe the list size
     * @see system.PhonyList#remove(Object)
     * @type Functional
     * @input [1,2,3,4]
     * @oracle The length of the new list must be decremented by one
     * @passed Yes
     **/
    @Test
    public void Remove_ObjectTest() {
        PhonyList<Object> phonyList = new PhonyList<>();

        phonyList.add("1");
        phonyList.add("2");
        int oldSize = phonyList.size();
        phonyList.remove("1");

        assertEquals(oldSize-1, phonyList.size());

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
     * Test the method "size" on a list before and after adds
     * @see system.PhonyList#size()
     * @type Functional
     * @input [1,2,3]
     * @oracle Size of the list must be equals to zero before adds, and 3 after
     * @passed Yes
     */
    @Test
    public void testSize() {
        assertEquals(list.size(), 0);
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(list.size(), 3);

    }

    /**
     * Tests the "removeAll" method
     *
     * @type Functional
     * @input
     * @oracle
     * @passed No
     * @see PhonyList#removeAll(java.util.Collection)
     */
    @Test
    public void removeAll_SimpleTest() {
        PhonyList<Integer> listToRemove = list();
        list = list(1,2,3);
        System.out.println(list.size());
        PhonyList<Integer> listOracle = list(1,2,3);
        System.out.println(listOracle.size());
        System.out.println(listToRemove.size());

        list.removeAll(listToRemove);
        System.out.println(list.size());
        for(Object elemList: list){
            for(Integer elemDeleted: listToRemove){
                if (elemList!=null && elemDeleted!=null) {
                    assertNotEquals(elemList.toString(), elemDeleted.toString());
                }
            }
        }
        assertFalse(list.contains(listToRemove));
        assertEquals(list.size(),listOracle.size());
    }

    /**
     * Tests the "removeAll" method
     *
     * @type Functional
     * @input [54, 93, 16, null, 17, null, 89, 22, 36, 30] & [1,93,22,30]
     * @oracle the obtained list must be empty
     * @passed No
     * @see PhonyList#removeAll(java.util.Collection)
     */
    @Test
    public void removeAll_AdvancedTest() {
        PhonyList<Integer> listToRemove = list(1, 93, 22, 30);
        list = list(54, 93, 16, null, 17, null, 89, 22, 36, 30);
        PhonyList<Integer> listOracle = list(54, 16, null, 17, null, 89, 36);

        list.removeAll(listToRemove);

        for(Object elemList: list){
            for(Integer elemDeleted: listToRemove){
                if (elemList!=null && elemDeleted!=null) {
                    assertNotEquals(elemList.toString(), elemDeleted.toString());
                }
            }
        }
        assertFalse(list.contains(listOracle));
        assertEquals(listOracle.size(), list.size());
    }

   /**
     * Tests the "removeRange" method, Elements that removes between 2 indexes
     *
     * @type Functional
     * @input [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]
     * @oracle The new list
     * @passed Yes
     * @see PhonyList#removeRange(int, int)
     * */
    @Test
    public void removeRange_Test(){
        list = list(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
        assertEquals(list.size(), 15);
        list.removeRange(5, 10);
        assertEquals(list.size(), 10);
        assertEquals(list.get(5).toString(), "11");

    }

 






}
