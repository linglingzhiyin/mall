package com;

import java.io.Serializable;

public class ListenMessage {
    public void handleMessage(Serializable message){
        System.out.println(message);
    }
}
