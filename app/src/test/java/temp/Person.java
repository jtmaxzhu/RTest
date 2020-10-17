package temp;

import java.io.Serializable;

/**
 * author : liuxiaohu
 * date   : 2020/7/18 13:17
 * desc   :
 * version: 1.0
 */
public class Person implements Serializable {
    private static final  long serialVersionUID=1L;
    public String name;
    public String sex;
    public int age;

    public Person(String name, String sex, int age){
        this.name=name;
        this.sex=sex;
        this.age=age;
    }
}
