package oop.assignment_problems.problem3;

public class BackyardToolshedRoutine {
    public static void main(String[] args) {
        CuttingTool cuttingTool = new CuttingTool();
        Pruner pruner = new Pruner();
        System.out.println(cuttingTool.use());
        System.out.println(pruner.use());
    }
}

abstract class GardenTool {
    public String use() {
        return "";
    }
}

class CuttingTool extends GardenTool {
    public CuttingTool() {
        super();
    }

    @Override
    public String use() {
        super.use();
        return "Using the tool in the garden, blade sharpened first";
    }
}

class Pruner extends CuttingTool {
    public Pruner() {
        super();
    }

    @Override
    public String use() {
        String baseUse = super.use();
        return baseUse + ", then trimming branches precisely";
    }
}
