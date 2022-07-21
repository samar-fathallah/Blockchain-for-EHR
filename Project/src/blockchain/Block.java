package blockchain;

import java.io.Serializable;

public class Block implements Serializable {

    private BlockHashPointer previousBlockHashPointer;
    private BlockBody blockBody;
    private byte[] blockSignature;

    public Block(BlockHashPointer previousBlockHashPointer, BlockBody blockBody, byte[] blockSignature) {
        this.previousBlockHashPointer = previousBlockHashPointer;
        this.blockBody = blockBody;
        this.blockSignature = blockSignature;
    }

    public BlockHashPointer getPreviousBlockHashPointer() {
        return this.previousBlockHashPointer;
    }

    public BlockBody getBlockBody() {
        return this.blockBody;
    }

    public byte[] getBlockSignature() {
        return this.blockSignature;
    }

}
