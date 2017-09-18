package task3.product.parts;

import task3.interfaces.IProductPart;

public class Engine implements IProductPart{
    public Engine() {
        System.out.println(getClass().getSimpleName() + " created");
    }
}