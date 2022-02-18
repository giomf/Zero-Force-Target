package net;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class Block {
    private final String name;
    private Pos2D position;
    private List<Block> connectedBlocks = new LinkedList<>();

    public Block(String name) {
        this.name = name;
    }

    public Block(String name, int x, int y) {
        this.name = name;
        this.position = new Pos2D(x, y);
    }

    public double calcForceToBlocksFrom(Pos2D currentPos) {
        double force = 0;
        Iterator<Block> it = this.getConnectedBlocks().iterator();
        while (it.hasNext()) {
            Pos2D nextPos = it.next().getPosition();
            force += Block.calculateForce(currentPos, nextPos);
        }

        return force;
    }

    // REVIEW K?
    private static double calculateForce(Pos2D posA, Pos2D posB) {
        return Math.sqrt(Math.pow(posA.getX() - posB.getX(), 2) + Math.pow(posA.getY() - posB.getY(), 2));
    }

    public String getName() {
        return name;
    }

    public Pos2D getPosition() {
        return this.position;
    }

    public void setPosition(Pos2D coord) {
        this.position = coord;
    }

    public List<Block> getConnectedBlocks() {
        return connectedBlocks;
    }

    public void setConnectedBlocks(List<Block> connectedBlocks) {
        this.connectedBlocks = connectedBlocks;
    }

    public void addConnectedBlock(Block block) {
        this.connectedBlocks.add(block);
    }

    public abstract BlockType getBlockType();

    @Override
    public String toString() {
        return this.name + "\t\t\t" + this.position.getX() + "\t" + this.position.getY() + "\t0" + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Block && this.name.equals(((Block) obj).getName());
    }

}
