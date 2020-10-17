package robam.rtest;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import temp.Person;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Vector v = new Vector();
        Map m = new Hashtable();

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        Iterator<String> it = list.iterator();
        while(it.hasNext()){
          String obj=it.next();
          System.out.print(obj);
        }





        assertEquals(4, 2 + 2);
    }


    @Test
    public void test() throws Exception {
        //序列化
        Person person = new Person("张三", "男", 23);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("cache.txt"));
        out.writeObject(person);
        out.close();

        //反序列化过程
        ObjectInputStream in=new ObjectInputStream(new FileInputStream("cache.txt"));
        Person newPerson=(Person)in.readObject();
        in.close();

    }
}