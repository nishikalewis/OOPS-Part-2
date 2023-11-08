package edu.umb.cs681.hw03;

public class CarPriceResultHolder {
    private int numCarExamined;
    private double totalPrice;

    public CarPriceResultHolder() {
        this.numCarExamined = 0;
        this.totalPrice = 0.0;
    }

    public void accumulate(double price) {
        this.numCarExamined = this.numCarExamined + 1;
        this.totalPrice = this.totalPrice + price;

    }

    public void combine(CarPriceResultHolder next) {
        this.numCarExamined =this.numCarExamined+next.numCarExamined;
        this.totalPrice=this.totalPrice+next.totalPrice;
    }

    public double getAverage() {
        if(numCarExamined==0){
            return 0;
        }
        else{
            double i=totalPrice/numCarExamined;
            return i;
        }
    }

}
