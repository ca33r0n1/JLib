package com.j0ach1mmall3.jlib.methods;

import java.util.ArrayList;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 9/11/2015
 */
public final class PaginatedList {
    private List<String> list;
    private int perPage;

    public PaginatedList(List<String> list, int perPage) {
        this.list = list;
        this.perPage = perPage;
    }

    public List<String> getPage(int page) {
        int maxPageNumber = (int) Math.ceil(((double) list.size())/((double) perPage));
        int size = perPage * page;
        List<String> pageList = new ArrayList<>(perPage);
        if (list.size() % perPage == 0 || page == maxPageNumber) {
            for (int i = size - perPage; i < list.size(); i++) {
                pageList.add(list.get(i));
            }
        } else {
            for (int i = size - perPage; i < size; i++) {
                pageList.add(list.get(i));
            }
        }
        return pageList;
    }
}
