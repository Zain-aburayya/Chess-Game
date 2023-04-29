package piece;

import enumeration.Color;
import enumeration.PieceType;

public class King extends Piece {

    protected King(Color pieceColor, PieceType pieceType) {
        super(pieceColor, pieceType);
    }

    @Override
    public boolean isValidMove(String[] location ) {
        int fromCol = location[0].charAt(0) - 'a' + 1;
        int fromRow = location[0].charAt(1) - '0';
        int toCol = location[1].charAt(0) - 'a' + 1;
        int toRow = location[1].charAt(1) - '0';
        int absoluteRow = Math.abs(toRow - fromRow);
        int absoluteCol = Math.abs(toCol - fromCol);
        return absoluteCol <= 1 && absoluteRow <= 1;
    }

}
