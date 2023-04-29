package piece;

import chess_board.Board;
import chess_board.Cell;
import enumeration.Color;
import enumeration.PieceType;

public class Rook extends Piece{
    protected Rook(Color pieceColor, PieceType pieceType) {
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
        Cell[][] cells = Board.getInstance().getCells();
        if(absoluteCol != 0 && absoluteRow != 0){
            return false;
        }
        if(absoluteCol == 0){
            int isPositiveRow = absoluteRow / (toRow - fromRow);
            while(true){
                fromRow += isPositiveRow;
                if(fromRow == toRow)
                    return true;
                if(cells[fromRow][fromCol].getPiece() != null)
                    return false;
            }
        }
        int isPositiveCol= absoluteCol / (toCol - fromCol);
        while(true){
            fromCol += isPositiveCol;
            if(fromCol == toCol)
                return true;
            if(cells[fromRow][fromCol].getPiece() != null)
                return false;
        }
    }
}
