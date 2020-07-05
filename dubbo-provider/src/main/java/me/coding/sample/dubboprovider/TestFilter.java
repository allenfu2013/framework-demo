package me.coding.sample.dubboprovider;

import com.alibaba.fastjson.JSON;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

import java.lang.reflect.Method;

@Activate(
        group = {CommonConstants.PROVIDER}
)
public class TestFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("@@@@@@@@@@@@@@@@@");

        String api = invocation.getAttachment("interface");
        String methodName = invocation.getMethodName();
        System.out.println(api + " " + methodName);

        AsyncRpcResult response = new AsyncRpcResult(invocation);

        try {
            Class<?> clazz = Class.forName(api);
            Method method = clazz.getDeclaredMethod(methodName, invocation.getParameterTypes());
            Class<?> returnType = method.getReturnType();
            Object obj = JSON.parseObject("{\"id\":1,\"home\":\"shanghai\"}", returnType);
            response.setValue(obj);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }

//        Result res = invoker.invoke(invocation);
//        System.out.println(res.getValue());
        return response;
    }
}
