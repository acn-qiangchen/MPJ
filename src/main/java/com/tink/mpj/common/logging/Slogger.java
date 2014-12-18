/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tink.mpj.common.logging;

import java.io.Serializable;
import javax.enterprise.inject.Vetoed;
import org.apache.log4j.Logger;

/**
 *
 * @author shearer
 */
@Vetoed
public class Slogger implements Serializable{
    private Logger logger;
    
    private Slogger(){
        // hide default constructor
    }
    
    public Slogger(String s){
        logger = Logger.getLogger(s);
    }

    public void info(Object s){
        logger.info(s);
    }
    
    public void warn(Object s){
        logger.warn(s);
    }
    
    public void debug(Object s){
        logger.debug(s);
    }
}
