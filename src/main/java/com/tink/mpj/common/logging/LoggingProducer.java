package com.tink.mpj.common.logging;

import com.tink.mpj.common.qualifier.MonitoringLog;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class LoggingProducer {

    @Produces @Default
    public Slogger produceLogger(InjectionPoint injectionPoint) {
        //return Logger.getLogger("[trace]" + injectionPoint.getMember().getDeclaringClass().getName());
        return new Slogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
    
    @Produces @MonitoringLog
    public Slogger produceMonitoringLogger(InjectionPoint injectionPoint) {
        //return Logger.getLogger("[Monitoring]" + injectionPoint.getMember().getDeclaringClass().getName());
        return new Slogger("Monitoring" + injectionPoint.getMember().getDeclaringClass().getName());
    }

}
