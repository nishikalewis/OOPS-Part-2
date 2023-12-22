package edu.umb.cs681.hw17;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable<StockEvent> {
    private ConcurrentLinkedQueue<Observer<StockEvent>> observers = new ConcurrentLinkedQueue<>();
    private ReentrantLock lockObs=new ReentrantLock();

    public void addObserver(Observer<StockEvent> o) {

            observers.add(o);

    }

    public void clearObservers() {


            observers.clear();


    }
    public ConcurrentLinkedQueue<Observer<StockEvent>> getObservers(){


            return observers;

    }

    public int countObservers() {


            return observers.size();


    }
    public void removeObserver(Observer<StockEvent> o) {


            observers.remove(o);
    }

    public void notifyObservers(StockEvent event) {

            observers.forEach(
                    (observer)->{
                        observer.update(this, event);});


    }

}
