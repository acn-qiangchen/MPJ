package com.tink.mpj.common.logging;

import java.io.Serializable;
import java.lang.reflect.Method;
//import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.apache.log4j.Logger;

/**
 * ログ出力用interceptor
 *
 * @author shearer
 */
@Interceptor
@Loggable
public class LoggingInterceptor implements Serializable{

    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
        Method m = ic.getMethod();
        
        // ExcludeClassInterceptorsそのまま書くだけで聞きません。。。
        if (!m.isAnnotationPresent(ExcludeClassInterceptors.class)) {
            Logger.getLogger(ic.getTarget().getClass().getName()).info("#" + ic.getMethod().getName() + "()  start.");
        }

        try {
            return ic.proceed();
        } finally {
            if (!m.isAnnotationPresent(ExcludeClassInterceptors.class)) {
                Logger.getLogger(ic.getTarget().getClass().getName()).info("#" + ic.getMethod().getName() + "()  end.");
            }
        }
    }
}
