package me.coding.sample.dubboconsumer;

//import com.alibaba.csp.sentinel.Entry;
//import com.alibaba.csp.sentinel.SphU;
//import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import me.coding.sample.dubboapi.Address;
import me.coding.sample.dubboapi.HelloService;
import me.coding.sample.dubboapi.User;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class HelloController {

    @Reference(retries = 0)
    private HelloService helloService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Map<String, Object> hello(String name) {
        System.out.println("################# consumer ####################");
        helloService.hello(name);
        Map<String, Object> res = new HashMap<>();
//        try (Entry entry = SphU.entry("test1")) {
//            log.info("test1 looks good");
//            res.put("msg", helloService.hello(name));
//            res.put("success", true);
//        } catch (BlockException ex) {
//            log.error("test1 blocked", ex);
//            res.put("success", false);
//        }
        return res;
    }

    @GetMapping("/address")
    public Address getAddress() {
        User user = new User();
        user.setId(11L);
        user.setName("allen");
        return helloService.getAddress(user);
    }

}
