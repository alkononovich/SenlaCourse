package task3.product.parts;

import task3.interfaces.IProductPart;

public class Tower implements IProductPart {
    public Tower() {
        System.out.println(getClass().getSimpleName() + " created");
    }
}