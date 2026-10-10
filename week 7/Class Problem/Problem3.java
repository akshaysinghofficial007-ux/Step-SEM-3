// Problem 3: Orchestra Warm-Up Routine
abstract class Instrument {
    public Instrument() { }
    public abstract String play();
}

class StringInstrument extends Instrument {
    public StringInstrument() { super(); }

    // Instrument.play() is abstract, so there is no parent body to call here;
    // this is the first concrete message in the hierarchy.
    @Override public String play() { return "Strumming the strings"; }
}

class Violin extends StringInstrument {
    public Violin() { super(); }

    @Override public String play() {
        return super.play() + ", with a bow drawn across four strings";
    }
}

public class Problem3 {
    public static void main(String[] args) {
        StringInstrument s = new StringInstrument();
        Violin v = new Violin();
        System.out.println(s.play());
        System.out.println(v.play());
    }
}
