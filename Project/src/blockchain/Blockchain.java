package blockchain;

public class Blockchain {

    private BlockHashPointer head;

    public Blockchain(BlockHashPointer head) {
        this.head = head;
    }

    public BlockHashPointer getHead() {
        return this.head;
    }

    public void setHead(BlockHashPointer head) {
        this.head = head;
    }

}
