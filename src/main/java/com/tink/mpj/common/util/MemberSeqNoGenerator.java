/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tink.mpj.common.util;

import com.tink.mpj.common.qualifier.MemberNo;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;

/**
 *
 * @author shearer
 */
@MemberNo
public class MemberSeqNoGenerator implements IFSeqNoGenerator{

    private static final String PREFIX = "ME-";
    private static int counter = 0;

//    public MemberSeqNoGenerator(){
//        // counter = DBから最新番号取得 
//    }
    
    public String getNo() {
        counter ++;
        DecimalFormat df = new DecimalFormat("000000");
        return PREFIX + df.format(counter);
    }
}
