import org.junit.Test;
import cn.itcast.commons.CommonUtils;

import java.util.HashMap;
import java.util.Map;

public class CommonUnitTest {

    /*
     *测试uuid
     * 激活码
     * id
     */
    @Test
    public void testUuid(){
        String s=CommonUtils.uuid();
        System.out.println(s);
    }


    /*
     *把map封装到javabean中
     * 要求map中的key名称与javabean的属性名称相同
     */
    @Test
    public void testToBean(){
        /*
         *map
         */
        Map<String, Object> map=new HashMap<String,Object>();
        map.put("pid",CommonUtils.uuid());
        map.put("name","zhangsan");
        map.put("age",22);

        //通过map的数据创建person类型的javabean对象
        person p=CommonUtils.toBean(map,person.class);
        System.out.println(p);
    }
}
