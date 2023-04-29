package piece;

import chess_board.Board;
import chess_board.Cell;
import enumeration.Color;
import enumeration.PieceType;

public class Bishop extends Piece {
    protected Bishop(Color pieceColor, PieceType pieceType) {
        super(pieceColor, pieceType);
    }

    @Override
    public boolean isValidMove(String[] location ) {
        int fromCol = location[0].charAt(0) - 'a' + 1;
        int fromRow = location[0].charAt(1) - '0';
        int toCol = location[1].charAt(0) - 'a' + 1;
        int toRow = location[1].charAt(1) - '0';
        int absoluteRow = Math.abs(fromRow - toRow);
        int absoluteCol = Math.abs(fromCol - toCol);
        if(absoluteRow != absoluteCol)
            return false;
        Cell[][] cells = Board.getInstance().getCells();
        int isPositiveRow = (toRow - fromRow) / absoluteRow;
        int isPositiveCol = (toCol - fromCol) / absoluteCol;
        while(fromRow != toRow && fromCol != toCol){
            fromRow += isPositiveRow;
            fromCol += isPositiveCol;
            if(fromRow == toRow && fromCol == toCol){
                break;
            }
            if(cells[fromRow][fromCol].getPiece() != null){
                return false;
            }
        }
        return true;
    }
}
