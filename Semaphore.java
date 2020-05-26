package com.company;

public class Semaphore {

    int value;

    public Semaphore(int v){
        value = v;
    }

    synchronized public void P(){
        while (value == 0){
            try{
                wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        value--;
    }

    synchronized public void V(){
        value++;
        notify();
    }
}