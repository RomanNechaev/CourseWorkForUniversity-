package org.example;

public class Coordinate {
    private final int row;
    private final int column;

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Coordinate)) return false;
        Coordinate other = (Coordinate) obj;
        if (other == this) return true;
        return other.row == row && other.column == column;
    }

    @Override
    public int hashCode() {
        return row^column;
    }

    @Override
    public String toString() {
        return getRow() + " " + getColumn();
    }
}
