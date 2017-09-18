package task3.linesteps;

import task3.interfaces.ILineStep;
import task3.interfaces.IProductPart;
import task3.product.parts.Body;


public class BodyBuilder implements ILineStep{

    public IProductPart buildProductPart() {
        return new Body();
    }
}