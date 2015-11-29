package com.j0ach1mmall3.jlib.methods;

import java.util.ArrayList;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 9/11/2015
 */
public final class PaginatedList {
    private final List<String> list;
    private final int perPage;
    private final int maxPageNumber;

    /**
     * Constructs a new PaginatedList instance
     * @param list The list to paginate
     * @param perPage The amount of entries per page
     */
    public PaginatedList(List<String> list, int perPage) {
        this.list = list;
        this.perPage = perPage;
        this.maxPageNumber = (int) Math.ceil(((double) list.size())/((double) perPage));
    }

    /**
     * Returns the entries of a specified page
     * @param page The page number
     * @return The entries
     */
    public List<String> getPage(int page) {
        int size = this.perPage * page;
        List<String> pageList = new ArrayList<>(this.perPage);
        if (this.list.size() % this.perPage == 0 || page == this.maxPageNumber) {
            for (int i = size - this.perPage; i < this.list.size(); i++) {
                pageList.add(this.list.get(i));
            }
        } else {
            for (int i = size - this.perPage; i < size; i++) {
                pageList.add(this.list.get(i));
            }
        }
        return pageList;
    }

    /**
     * Returns the Max Page Number
     * @return The Max Page Number
     */
    public int getMaxPageNumber() {
        return this.maxPageNumber;
    }
}
