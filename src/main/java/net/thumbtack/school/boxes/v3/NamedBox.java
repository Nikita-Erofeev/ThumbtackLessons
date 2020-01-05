package net.thumbtack.school.boxes.v3;

import net.thumbtack.school.figures.v3.ClosedFigure;

class NamedBox<R extends ClosedFigure> extends Box<R> {
    private String name;

    public NamedBox(R content,String name){
        super(content);
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
