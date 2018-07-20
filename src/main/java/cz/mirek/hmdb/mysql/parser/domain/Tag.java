package cz.mirek.hmdb.mysql.parser.domain;

import java.util.HashSet;
import java.util.Set;

import static java.util.stream.IntStream.range;

public class Tag {
    public String tag;
    public Set<Tag> children = new HashSet<>();

    public Tag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag1 = (Tag) o;

        return tag != null ? tag.equals(tag1.tag) : tag1.tag == null;
    }

    @Override
    public int hashCode() {
        return tag != null ? tag.hashCode() : 0;
    }

    public void printString(int start) {
        range(0, start).forEach(i -> {
            System.out.print("-");
        });

        System.out.print(tag);
        System.out.println();

        children.forEach(child -> {
            child.printString(start + 2);
        });
    }
}
