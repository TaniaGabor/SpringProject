package com.solid.o.bad;



public class Main {
    public static void main(String[] args) {
        BadClient badClient= new BadClient();
        BadServer badServer=new BadServer();
        AnotherBadClient anotherClient=new AnotherBadClient();
        badServer.reactToClient(badClient);



    }
}