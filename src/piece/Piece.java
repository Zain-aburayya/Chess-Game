package piece;

import enumeration.Color;
import enumeration.PieceType;

public abstract class Piece {
    private Color pieceColor;
    private PieceType pieceType;

    protected Piece(Color pieceColor, PieceType pieceType) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
    }

    public Color getPieceColor() {
        return pieceColor;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public abstract boolean isValidMove(String[] location );

}
