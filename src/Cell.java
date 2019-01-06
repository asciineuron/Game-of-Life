public class Cell {

    private boolean isAlive;
    private boolean markToDie; //will allow rules to be applied simultaneously
    private boolean markToLive;

    public Cell(boolean isAlive) {
        setIsAlive(isAlive);
        setMarkToDie(false);
        setMarkToLive(false);
    }

    public Cell() {
        //by default dead
        setIsAlive(false);
        setMarkToDie(false);
        setMarkToLive(false);
    }

    public boolean getIsAlive() {
        return this.isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean getMarkToDie() {
        return this.markToDie;
    }

    public void setMarkToDie(boolean markToDie) {
        this.markToDie = markToDie;
    }

    public boolean getMarkToLive() {
        return this.markToLive;
    }

    public void setMarkToLive(boolean markToLive) {
        this.markToLive = markToLive;
    }
}
