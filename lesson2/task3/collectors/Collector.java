package task3.collectors;

import task3.interfaces.IAssemblyLine;
import task3.interfaces.ILineStep;
import task3.interfaces.IProduct;
import task3.interfaces.IProductPart;

public class Collector implements IAssemblyLine {

    private ILineStep firstStep;
    private ILineStep secondStep;
    private ILineStep thirdStep;

    public Collector(ILineStep firstStep, ILineStep secondStep, ILineStep thirdStep) {
        setFirstStep(firstStep);
        setSecondStep(secondStep);
        setThirdStep(thirdStep);
    }


    public IProduct assembleProduct(IProduct product) {
        IProductPart firstPart = firstStep.buildProductPart();
        IProductPart secondPart = secondStep.buildProductPart();
        IProductPart thirdPart = thirdStep.buildProductPart();

        product.installFirstPart(firstPart);
        product.installSecondPart(secondPart);
        product.installThirdPart(thirdPart);

        System.out.println(product.getClass().getSimpleName() + " collected");
        return product;
    }

    public ILineStep getFirstStep() {
        return firstStep;
    }

    public void setFirstStep(ILineStep firstStep) {
        this.firstStep = firstStep;
    }

    public ILineStep getSecondStep() {
        return secondStep;
    }

    public void setSecondStep(ILineStep secondStep) {
        this.secondStep = secondStep;
    }

    public ILineStep getThirdStep() {
        return thirdStep;
    }

    public void setThirdStep(ILineStep thirdStep) {
        this.thirdStep = thirdStep;
    }
}