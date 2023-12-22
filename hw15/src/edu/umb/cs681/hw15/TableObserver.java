package edu.umb.cs681.hw15;

public class TableObserver implements Observer<StockEvent>{

    @Override
    public void update(Observable<StockEvent> sender, StockEvent event) {
        System.out.println(Thread.currentThread().threadId()+" Table Observer printing "+"(quote,ticker )"+event.quote()+" ,"+event.ticker());
    }
}

