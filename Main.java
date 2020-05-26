package com.company;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {



        /* Input N entrances, 0 < X < 10 */

        int nEntr;
        int entCicle = 0;
        System.out.println("\n");
        Scanner iSc = new Scanner(System.in);


        do {
            if(entCicle > 0) {
                System.out.print("\n  [>] Try with a number lower than 10 and higher than 0");
            }

            System.out.print("\n  [?] How many avenues are connected to the roundabout? --> ");
            nEntr = iSc.nextInt();
            entCicle++;

        }while(nEntr < 1 || nEntr > 9);

        int[] entArray = new int[nEntr];
        System.out.println();



        /* Input N cars, X < 10 */

        int carCicle;
        int maxNCars = 0;

        for (int i = 0; i < nEntr; i++) {

            carCicle = 0;

            do {
                if(carCicle > 0) {
                    System.out.print("\n      [>] Try with a number lower than 10");
                }

                System.out.print("      [?] How many cars are there in the " + (i + 1) + " avenue? --> ");
                entArray[i] = iSc.nextInt();
                carCicle++;

            }while(entArray[i] < 0 || entArray[i] > 9);

            if(maxNCars < entArray[i]) {
                maxNCars = entArray[i];
            }
        }



        /* Entrance of the cars from the avenues */

        int i;

        if(maxNCars > 0) {

            System.out.print("\n\n\n  [>] Entry phase of the cars:\n");

            //Instantiate an array of semaphore, sem[0] <- value(1), fill it with N sems
            Semaphore[] sem = new Semaphore[nEntr];
            sem[0] = new Semaphore(1);
            for(int j=1; j < nEntr; j++) { sem[j] = new Semaphore(0); }

            //Create and start N threads
            for(i=0; i < (nEntr-1); i++) {
                Thread th = new Thread(new Roundabout(sem[i], sem[i+1], entArray[i], maxNCars, i+1));
                th.start();
            }

            //Create the last thread outside the for, if inside it won't work (outOfBonds, index problem)
            Thread th = new Thread(new Roundabout(sem[i], sem[0], entArray[i], maxNCars, i+1));
            th.start();
        }
    }
}