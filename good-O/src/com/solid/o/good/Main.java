package com.solid.o.good;



public class Main {
    public static void main(String[] args) {
        DoSomething badClient= new BadClient();
        BadServer badServer=new BadServer();
       DoSomething anotherClient=new AnotherBadClient();
        badServer.reactToClient(badClient);
        badServer.reactToClient(anotherClient);



    }
}