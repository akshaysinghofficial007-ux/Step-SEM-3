// Problem 2: Gallery Description Cards
abstract class ArtPiece {
    private static int counter = 1000;       // shared static counter
    private final String pieceId;            // final, assigned once
    protected final String title;

    protected ArtPiece(String title) {
        if (title == null || title.trim().isEmpty()) throw new IllegalArgumentException("title must not be blank");
        this.title = title;
        this.pieceId = "ART-" + (++counter);
    }

    public abstract String describe();

    String getPieceId() { return pieceId; }
}

class Painting extends ArtPiece {
    public Painting(String title) { super(title); }
    @Override public String describe() { return "Painting: " + title + ", framed on canvas"; }
}

class Sculpture extends ArtPiece {
    public Sculpture(String title) { super(title); }
    @Override public String describe() { return "Sculpture: " + title + ", carved from stone"; }
}

public class Problem2 {
    public static void main(String[] args) {
        // ArtPiece x = new ArtPiece("?");  // COMPILE ERROR: abstract class
        Painting p = new Painting("Sunset Fields");
        Sculpture s = new Sculpture("The Thinker II");
        System.out.println(p.describe());
        System.out.println(s.describe());
        System.out.println(p.getPieceId());
        System.out.println(s.getPieceId());
    }
}
