package com.dxy.collection;

import com.google.common.collect.Sets;

import java.util.*;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {

        SortObj[] sortObjs = new SortObj[] {
                new SortObj(1, 1),
                new SortObj(2, 2),
                new SortObj(3, 3),
                new SortObj(1, 3),
                new SortObj(1, 4),
        };

        Iterable<SortObj> iterSortObjs = Arrays.asList(sortObjs);

        Set<SortObj> hashsets;

        hashsets = Sets.newHashSet(iterSortObjs);
        hashsets.stream().forEach(sortObj -> System.out.println(sortObj));

        System.out.println("-----------");

        hashsets = Sets.newLinkedHashSet(iterSortObjs);
        hashsets.stream().forEach(sortObj -> System.out.println(sortObj));

        System.out.println("-----------");

        // note: 坑
        // TreeSet 基于 TreeMap.put(k, v) 实现了 Set.add(Object) 操作。
        // TreeHashMap 基于 Red-Blank Tree 实现, 通过 compareTo(Object) 的方法来判断元素是否重复, 与 equals 关联并不是很大。
        // 在执行 add(Object) 时, 会通过 cmp = thisEntry.key.compareTo(otherEntry.key), 判断新的节点应该如何存放。 如果节点（Entry.key）不相等 (cmp > 0 || cmp < 0), 则直接放入二叉树结构中；
        // 如果节点（Entry.key）已存在 (cmp == 0)，则不更新节点 key，只更新节点对应的 value。
        // 因为是 TreeSet，所有的 key 为 Set 集合内的元素, value 统一默认为 new Object()。
        hashsets = Sets.newTreeSet(iterSortObjs);
        hashsets.stream().forEach(sortObj -> System.out.println(sortObj));

        // SortObj{id=1, sort=4}
        // SortObj{id=3, sort=3}
        // SortObj{id=2, sort=2}
        // SortObj{id=1, sort=1}
    }

}


class SortObj implements Comparable<SortObj> {
    private int id;
    private int sort;

    public SortObj(int id, int sort) {
        this.id = id;
        this.sort = sort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ( o == null || getClass() != o.getClass()) return false;

        SortObj so = (SortObj) o;
        return id == so.id;
    }

    @Override
    public int hashCode() {
        // note: 坑
        // 未实现 hashCode， 对 TreeSet 影响不大，但是对于 HashSet、LinkedHashSet 会有完全不同的影响
        return 1;
    }

    @Override
    public int compareTo(SortObj o) {
        if (!this.equals(o)) {
            // note: 坑
            // 倒序排列
            return Integer.valueOf(o.sort).compareTo(this.sort);
        }
        return 0;
    }

    @Override
    public String toString() {
        return "SortObj{" +
                "id=" + id +
                ", sort=" + sort +
                '}';
    }
}