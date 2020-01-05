package net.thumbtack.school.boxes.v3;

import net.thumbtack.school.figures.v3.ClosedFigure;

class ArrayBox<T> {
    private static final double EPS = 1E-6;
    private T[] content;

    public ArrayBox(T[] content) {
        super();
        this.content = content;
    }

    public T[] getContent() {
        return content;
    }

    public void setContent(T[] content) {
        this.content = content;
    }

    public T getElement(int i) {
        return content[i];
    }

    public void setElement(int i, T content) {
        this.content[i] = content;
    }

    public boolean isSameSize(ArrayBox<?> another) {
        return (content.length - another.getContent().length) == 0;
    }

}
