package task3;

import task3.collectors.Collector;
import task3.interfaces.IProduct;
import task3.linesteps.BodyBuilder;
import task3.linesteps.TowerBuilder;
import task3.linesteps.EngineBuilder;
import task3.products.Tank;

public class Checking {

    public static void main(String[] args) {
        Collector collectorTank = new Collector(new BodyBuilder(), new EngineBuilder(), new TowerBuilder());
        IProduct tank = collectorTank.assembleProduct(new Tank());
    }

}