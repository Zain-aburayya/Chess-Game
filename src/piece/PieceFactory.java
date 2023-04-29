package piece;

import enumeration.Color;
import enumeration.PieceType;
public class PieceFactory {
    public Piece createPiece(PieceType pieceType , Color color ){
        return switch (pieceType) {
            case KING -> new King(color, pieceType);
            case QUEEN -> new Queen(color, pieceType);
            case KNIGHT -> new Knight(color, pieceType);
            case BISHOP -> new Bishop(color, pieceType);
            case PAWN -> new Pawn(color, pieceType);
            case ROOK -> new Rook(color, pieceType);
        };
    }
}
