package com.example.sendmessage.sendmessagebutterknife.pojo;

import java.io.Serializable;

/**
 * @author Miguel Rodriguez Jimenez 29/09/2017
 *         code > generate >
 */

public class Message implements Serializable{
    private String message;
    private String user;

    public Message(String message, String user) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", user='" + user + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        boolean result;
        // this == o posicion de memoria
        if (this == o) return true;
        // comprueba nulo y comprueba si son la misma clase!!!
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        /* Refactorizado
        if (message != null ? !message.equals(message1.message) : message1.message != null)
            return false;
        return user != null ? user.equals(message1.user) : message1.user == null;*/

        result = ((message.equals(message1.message)) && (user.equals(message1.user)));
        return result;
    }

    /*
    No lo usaremos aun
    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
    */

}
