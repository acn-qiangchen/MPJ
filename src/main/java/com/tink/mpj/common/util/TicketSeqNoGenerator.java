/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tink.mpj.common.util;

import java.text.DecimalFormat;

/**
 *
 * @author shearer
 */
public class TicketSeqNoGenerator implements IFSeqNoGenerator{

    private static final String PREFIX = "TC-";
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
