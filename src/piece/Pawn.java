package piece;

import chess_board.Board;
import chess_board.Cell;
import enumeration.Color;
import enumeration.PieceType;

public class Pawn extends Piece{
    protected Pawn(Color pieceColor, PieceType pieceType) {
        super(pieceColor, pieceType);
    }
    @Override
    public boolean isValidMove(String[] location ) {
        int fromCol = location[0].charAt(0) - 'a' + 1;
        int fromRow = location[0].charAt(1) - '0';
        int toCol = location[1].charAt(0) - 'a' + 1;
        int toRow = location[1].charAt(1) - '0';

        Cell[][] cells = Board.getInstance().getCells();

        if(getPieceColor() == Color.WHITE && fromRow < toRow){
            int column = Math.abs(toCol - fromCol);
            int row = Math.abs(fromRow - toRow);
            if( column == 1 && row == 1){
                return cells[toRow][toCol].getPiece() != null && cells[toRow][toCol].getPiece().getPieceColor() == Color.BLACK;
            }
        }
        if(getPieceColor() == Color.BLACK && fromRow > toRow){
            int column = Math.abs(toCol - fromCol);
            int row = Math.abs(fromRow - toRow);
            if( column == 1 && row == 1){
                return cells[toRow][toCol].getPiece() != null && cells[toRow][toCol].getPiece().getPieceColor() == Color.WHITE;
            }
        }
        if(cells[toRow][toCol].getPiece() != null)
            return false;

        if(fromCol != toCol)
            return false;

        if (fromRow > toRow && getPieceColor() == Color.WHITE)
            return false;

        if (fromRow < toRow && getPieceColor() == Color.BLACK)
                return false;

        int absoluteRow = Math.abs(toRow - fromRow);
        if(absoluteRow > 1){
            if(getPieceColor() == Color.WHITE && (fromRow != 2  || absoluteRow > 2))
                return false;
            return getPieceColor() != Color.BLACK || (fromRow == 7 && absoluteRow == 2);
        }

        return true;
    }
}
