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

    @Test
    public void get_OneElementTest(){
        Integer entier = new Integer(0);
        list.add(entier);
        assertEquals("0", list.get(0).toString());
    }

    @Test
    public void get_ThreeElementTest(){
        list = createIntegerPhonyList(3);
        assertEquals( "2" ,list.get(2).toString());
    }

}
