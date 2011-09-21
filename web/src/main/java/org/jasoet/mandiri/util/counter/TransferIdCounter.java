package org.jasoet.mandiri.util.counter;

/**
 * User: Deny Prasetyo, S.T.
 * Email : jasoet87@gmail.com
 * Date: 5/1/11
 * Time: 1:35 PM
 */
public class TransferIdCounter {
    private int counter = 500;


    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void increment() {
        this.counter++;
    }

    public void reset() {
        this.counter = 500;
    }
}
