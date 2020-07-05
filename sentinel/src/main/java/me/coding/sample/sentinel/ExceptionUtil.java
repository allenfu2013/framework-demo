package me.coding.sample.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public final class ExceptionUtil {

    public static Map<String, Object> handleTest2(BlockException ex) {
        log.warn("block rule: {}", ex.getRule().getResource(), ex);
        Map<String, Object> res = new HashMap<>();
        res.put("test1", false);
        return res;
    }

    public static void main(String[] args) {
        String reqId = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(reqId);
        String sign = "accountkeshang_api_testreqTid" + reqId + "92de2ee2a44fb92e";
        System.out.println(org.apache.commons.codec.digest.DigestUtils.md5Hex(sign).toUpperCase());
    }

}
