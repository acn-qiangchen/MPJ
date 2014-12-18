/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tink.sandbox.java.core.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shearer
 */
public class LearnNIO {
    void learnChannel(){
        try {
            Files.newByteChannel(null);
        } catch (IOException ex) {
            Logger.getLogger(LearnNIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
