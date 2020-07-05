package me.coding.sample.nacosconfig;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("config")
public class ConfigController {

    @NacosValue(value = "${code:0}", autoRefreshed = true)
    private String code;

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public String get() {
        return code;
    }

}
