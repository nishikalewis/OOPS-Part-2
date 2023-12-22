package edu.umb.cs681.hw14;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable {
    protected HashMap<String, Double> m = new HashMap<String, Double>();
    private ReentrantLock lockTQ = new ReentrantLock();

    public void changeQuote(String t, double q) {

        lockTQ.lock();
        try {
            m.put(t, q);
        }finally {
            lockTQ.unlock();
        }

        notifyObservers(new StockEvent(t, q));
    }

    public HashMap<String, Double> getmap() {
        lockTQ.lock();
        try {
            return m;
        }finally {
            lockTQ.unlock();
        }


    }

    public static void main(String args[]) {
        StockQuoteObservable st = new StockQuoteObservable();
        LineChartObserver obs = new LineChartObserver();
        TableObserver tbs = new TableObserver();
        ThreeDObserver threeD = new ThreeDObserver();

        st.addObserver(obs);
        st.addObserver(tbs);
        st.addObserver(threeD);


        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> st.changeQuote("Ticker" + finalI, finalI * 10.0)).start();
        }

    }
}