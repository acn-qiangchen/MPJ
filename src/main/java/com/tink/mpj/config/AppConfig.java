/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tink.mpj.config;

/**
 *
 * @author shearer
 */
public class AppConfig {

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    /**
     * Theme of Application of a Session
     */
    private String theme = "A";

    /**
     * when click ok button
     */
    public void ok(){
        // do nothing
    }
    /**
     * Creates a new instance of AppConfig
     */
    public AppConfig() {
    }
    
}
