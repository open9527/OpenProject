package com.android.custom.pkg.layout.grid;

/**
 * @author open_9527
 * Create at 2021/6/3
 **/
public class Place {

    private int left;
    private int top;
    private int right;
    private int bottom;

    public Place(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public int getLeft() {
        return left;
    }

    public int getTop() {
        return top;
    }

    public int getRight() {
        return right;
    }

    public int getBottom() {
        return bottom;
    }
}
