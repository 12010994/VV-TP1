package system;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Observable;

import static org.junit.Assert.*;

public class PhonyListTest {

    private PhonyList<Integer> list;

    private PhonyList<Integer> createIntegerPhonyList(int size){
        PhonyList<Integer> list = new PhonyList<Integer>();
        for(int i=0 ; i< size ; i++){
            Integer entier = new Integer(i);
            list.add(entier);
        }
        return list;
    }
    @Before
    public void setUpNewIntegerPhonyList(){
        list = new PhonyList<Integer>();
    }

//tested:

    //clear
    //indexOf

//to test:
    //get
    //set
    //add
    //fastRemove


    /**
     * Tests the "get" method with an one element list.
     *
     * @see PhonyList#get(int)
     * @type Functional
     * @input [0] o=0
     * @oracle The obtained list must return one element of type Integer, value 0, at position 0
     * @passed Yes
     */
    @Test
    public void get_OneElementTest(){
        Integer entier = new Integer(0);
        list.add(entier);
        assertEquals("0", list.get(0).toString());
        assertEquals(Integer.class, list.get(0).getClass());
    }

    /**
     * Tests the "get" method with a 3 elements list.
     *
     * @see PhonyList#get(int)
     * @type Functional
     * @input [0,1,2] o=0,1,2
     * @oracle The obtained list must return 3 elements of type Integer, value 0,1,2, at position 0,1,2.
     * @passed Yes
     */
    @Test
    public void get_ThreeElementTest(){
        list = createIntegerPhonyList(3);
        assertEquals( "0" ,list.get(0).toString());
        assertEquals( "1" ,list.get(1).toString());
        assertEquals( "2" ,list.get(2).toString());
    }

    /**
     * Tests the "get" method with an empty list.
     *
     * @see PhonyList#get(int)
     * @type Functional
     * @input [] o=0
     * @oracle Must rise an exception of type IndexOutOfBoundsException
     * @passed Yes
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void get_EmptyListTest(){
        assertEquals( null ,list.get(0).toString());
    }

}
