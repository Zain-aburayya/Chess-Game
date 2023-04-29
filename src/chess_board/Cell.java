package chess_board;

import enumeration.Color;
import piece.Piece;

public class Cell {
    private final Color color;

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    private Piece piece;

    public Piece getPiece() {
        return piece;
    }
    private static final String TEXT_BLACK  = "\u001B[30m";
    private static final String TEXT_WHITE  = "\u001B[97m";
    private static final String TEXT_BOLD  = "\033[1;95m";

    public Color getColor() {
        return color;
    }

    public Cell(Piece piece, Color color) {
        this.piece = piece;
        this.color = color;
    }

    @Override
    public String toString() {
        if(piece == null) return "\t";
        String textColor= TEXT_WHITE ;
        if(piece.getPieceColor() == Color.BLACK)
            textColor = TEXT_BLACK;
        return (TEXT_BOLD + textColor + piece.getPieceType().name());
    }
}
