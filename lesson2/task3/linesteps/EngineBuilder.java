package task3.linesteps;

import task3.interfaces.ILineStep;
import task3.interfaces.IProductPart;
import task3.product.parts.Engine;


public class EngineBuilder implements ILineStep{

   
    public IProductPart buildProductPart() {
        return new Engine();
    }
}