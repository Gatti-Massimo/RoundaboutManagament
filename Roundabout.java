package com.company;

import static java.lang.Thread.sleep;

public class Roundabout implements Runnable{



    /* Variables */
    private final Semaphore sem1;
    private final Semaphore sem2;
    private final int carCounter;
    private final int maxNCars;
    private final int aveCounter;



    /* Constructor */
    public Roundabout(Semaphore sem1, Semaphore sem2, int carCounter, int maxNCars, int aveCounter){

        this.sem1 = sem1;
        this.sem2 = sem2;
        this.carCounter=carCounter;
        this.maxNCars=maxNCars;
        this.aveCounter=aveCounter;
    }



    /* Run method */
    public void run(){

        for(int i=1; i<=carCounter; i++) {

            sem1.P();
            System.out.print("\n      [!] From the " + (aveCounter) + "° avenue has entered the " + i + "° car");

            if(i == carCounter){
                System.out.print("  --> Last of the avenue");
            }
            sem2.V(); //Change avenue

            try {
                sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int j=0; j<maxNCars; j++) { sem1.P(); sem2.V(); } //Unlock next thread
    }
}