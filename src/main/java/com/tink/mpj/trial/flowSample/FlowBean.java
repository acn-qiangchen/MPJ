/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tink.mpj.trial.flowSample;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.flow.FlowScoped;

/**
 *
 * @author shearer
 */
@Named
@FlowScoped("flowSample")
public class FlowBean implements Serializable{

    private String param1;
    private String param2;
    private String param3;

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }
    
    /**
     * Creates a new instance of FlowBean
     */
    public FlowBean() {
    }
    
}
