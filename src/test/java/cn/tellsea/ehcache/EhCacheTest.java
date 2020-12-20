package cn.tellsea.ehcache;

import cn.tellsea.SpringbootEhcacheApplicationTests;
import cn.tellsea.bean.User;
import cn.tellsea.service.UserService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EhCacheTest extends SpringbootEhcacheApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void insert1() {
        Cache cache = cacheManager.getCache("users");
        Element e = new Element("kobe", "0824");
        cache.put(e);
        Element kobe = cache.get("kobe");
        System.out.println(kobe);
    }

    /**
     * 新增
     */
    @Test
    public void insert() {
        User user = new User();
        user.setName("kobe");
        user.setPassword("123456");
        userService.insert(user);
    }

    /**
     * 更新
     */
    @Test
    public void update() {
        User user = new User();
        user.setId(1L);
        user.setName("tellsea");
        user.setPassword("654321");
        userService.update(user);
    }

    /**
     * 删除
     */
    @Test
    public void delete() {
        userService.delete(2L);
    }

    /**
     * 查询单个
     */
    @Test
    public void get() {
        User user = userService.get(1L);
        User user1 = userService.get(1L);
        System.out.println("第一次：" + user);
        System.out.println("第二次：" + user1);
    }

    /**
     * 查询所有
     */
    @Test
    public void list() {
        List<User> list = userService.list();
        list.forEach(user -> System.out.println(user));
        System.out.println("第二遍");
        List<User> list2 = userService.list();
        list2.forEach(user -> System.out.println(user));
    }
}
