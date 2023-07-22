package com.chensi.reflect.three;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/***********************************
 * @author chensi
 * @date 2022/4/11 13:46
 ***********************************/
public class ServiceInterceptor implements MethodInterceptor {
    private static final String INTERCEPTOR = "run";

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object result = null;
        if (!INTERCEPTOR.equals(methodInvocation.getMethod().getName())) {
            System.out.println("不能执行该方法" + methodInvocation.getMethod().toString());
            return null;
        }
        try {
            System.out.println("方法执行之前：" + methodInvocation.getMethod().toString());

            result = methodInvocation.proceed();
            System.out.println("方法正常运行结果：" + result);

            System.out.println("方法执行之后：" + methodInvocation.getMethod().toString());
            return result;
        } catch (Exception e) {
            System.out.println("方法出现异常：" + e.toString());
            System.out.println("方法运行Exception结果：" + result);
            return result;
        }
    }
}
