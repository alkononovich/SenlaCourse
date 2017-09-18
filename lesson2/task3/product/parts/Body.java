package task3.product.parts;

import task3.interfaces.IProductPart;

public class Body implements IProductPart{
    public Body() {
        System.out.println(getClass().getSimpleName() + " created");
    }
}