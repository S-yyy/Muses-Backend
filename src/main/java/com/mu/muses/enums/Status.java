package com.mu.muses.enums;

public enum Status {
    Opening(1,"开题立项"),
    Collection(2,"数据收集"),
    Marking(3,"数据标注"),
    Analysis(4,"数据分析"),
    Writing(5,"文章撰写"),
    Ending(6,"文章录用"),
    Over(7,"课题终止");

    private int index;
    private String name;

    Status(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
