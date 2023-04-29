package piece;

import enumeration.Color;
import enumeration.PieceType;

public class Knight extends Piece{
    protected Knight(Color pieceColor, PieceType pieceType) {
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
        if(absoluteRow == 1 && absoluteCol == 2)
            return true;

        return absoluteRow == 2 && absoluteCol == 1;
    }

}
