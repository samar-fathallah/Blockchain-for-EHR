package blockchain;

import java.io.Serializable;

public class BlockHashPointer implements Serializable {

    private Block pointer;
    private byte[] hash;

    public BlockHashPointer(Block pointer, byte[] hash) {
        this.pointer = pointer;
        this.hash = hash;
    }

    public Block getPointer() {
        return this.pointer;
    }

    public byte[] getHash() {
        return this.hash;
    }

}
