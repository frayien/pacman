package model.entity;

import model.Grid;

public class Ghost extends Entity {

    private int id;

    public Ghost(Grid g, int id) {
        super(g);
        this.id = id;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    public int getId() {
        return id;
    }

}
