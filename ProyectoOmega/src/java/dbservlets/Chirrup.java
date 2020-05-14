/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbservlets;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author Mike
 */
public class Chirrup implements Serializable{
    private String message;
    private String author;
    private LocalDateTime date;

    public Chirrup(String author, String msg) {
        this.message = msg;
        this.author = author;
        this.date = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getDate() {
        return date;
    }
    
    
}
