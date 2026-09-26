package oop.class_problems.problem3;

public class OrchestraWarmUpRoutine {
    public static void main(String[] args) {
        StringInstrument stringInstrument = new StringInstrument();
        Violin violin = new Violin();
        System.out.println(stringInstrument.play());
        System.out.println(violin.play());
    }
}

abstract class Instrument {
    public String play() {
        return "";
    }
}

class StringInstrument extends Instrument {
    public StringInstrument() {
        super();
    }

    @Override
    public String play() {
        super.play();
        return "Strumming the strings";
    }
}

class Violin extends StringInstrument {
    public Violin() {
        super();
    }

    @Override
    public String play() {
        String baseSound = super.play();
        return baseSound + ", with a bow drawn across four strings";
    }
}
