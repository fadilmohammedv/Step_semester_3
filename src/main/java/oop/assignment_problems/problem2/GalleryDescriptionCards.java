package oop.assignment_problems.problem2;

public class GalleryDescriptionCards {
    public static void main(String[] args) {
        Painting painting = new Painting("Sunset Fields");
        Sculpture sculpture = new Sculpture("The Thinker II");
        System.out.println(painting.describe());
        System.out.println(sculpture.describe());
        System.out.println(painting.getPieceId());
        System.out.println(sculpture.getPieceId());
    }
}

abstract class ArtPiece {
    private static int nextPieceNumber = 1000;
    private final String pieceId;
    protected final String title;

    public ArtPiece(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.title = title;
        this.pieceId = "ART-" + (++nextPieceNumber);
    }

    public abstract String describe();

    public String getPieceId() {
        return pieceId;
    }
}

class Painting extends ArtPiece {
    public Painting(String title) {
        super(title);
    }

    @Override
    public String describe() {
        return "Painting: " + title + ", framed on canvas";
    }
}

class Sculpture extends ArtPiece {
    public Sculpture(String title) {
        super(title);
    }

    @Override
    public String describe() {
        return "Sculpture: " + title + ", carved from stone";
    }
}
