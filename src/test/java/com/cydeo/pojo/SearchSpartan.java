package com.cydeo.pojo;

import com.cydeo.pojo.Spartan;

import java.util.List;

public class SearchSpartan {

    private List<Spartan> content;
    private int totalElement;

    public List<Spartan> getContent() {
        return content;
    }

    public void setContent(List<Spartan> content) {
        this.content = content;
    }

    public int getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(int totalElement) {
        this.totalElement = totalElement;
    }

    @Override
    public String toString() {
        return "SearchSpartan{" +
                "spartans=" + content +
                ", totalElement=" + totalElement +
                '}';
    }
}
