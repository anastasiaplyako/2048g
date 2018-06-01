public class Cell {

    private int value;
    private int row, column;

    private boolean mix;

    public Cell(int row, int column, int value) {
        mix = false;
        this.value = value;
        this.column = column;
        this.row = row;
    }

    public Cell(Cell other) {
        this(other.row, other.column, other.value);
    }

    public void setPosition(int row, int column){
        this.column = column;
        this.row = row;
    }

    public void setValue(int value){
        this.value = value;
    }

    public void setMix(boolean mix) {
        this.mix = mix;
    }

    public boolean isMixed() {
        return mix;
    }

    public int getValue() {
        return value;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    @Override
    public boolean equals(Object object){
        if (this == object) return true;
        if (object instanceof Cell){
            Cell other = (Cell) object;
            return this.value == other.value && this.column == other.column && this.row == other.row;
        }
        return false;
    }





}

