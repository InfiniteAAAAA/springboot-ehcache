package cn.tellsea.controller;

import cn.tellsea.bean.User;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private CacheManager cacheManager;

    @RequestMapping(value = "insert",method = RequestMethod.POST)
    public void insert(@RequestBody User user) {
        Cache cache = cacheManager.getCache("users");
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        System.out.println(uuidStr);
        Element element = new Element(uuidStr,user);
        cache.put(element);
    }
    

    @RequestMapping(value = "get",method = RequestMethod.GET)
    public void getUser(String uuid) {
        Cache cache = cacheManager.getCache("users");
        Element element = cache.get(uuid);
        User user = (User) element.getObjectValue();
        System.out.println(user);
    }
}
