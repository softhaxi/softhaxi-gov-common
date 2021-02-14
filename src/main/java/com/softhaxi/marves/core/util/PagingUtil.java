package com.softhaxi.marves.core.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.IntStream;

public class PagingUtil {

    public static int[] generatePages(int totalPages, int currentPage) {
        int[] pages;
        if (totalPages > 6) {
            // int totalPages = pagedResult.getTotalPages();
            int pageNumber = currentPage+1;
            int[] head = (pageNumber > 4) ? new int[]{1, -1} : new int[]{1,2,3};
            int[] bodyBefore = (pageNumber > 4 && pageNumber < totalPages - 1) ? new int[]{pageNumber-2, pageNumber-1} : new int[]{};
            int[] bodyCenter = (pageNumber > 3 && pageNumber < totalPages - 2) ? new int[]{pageNumber} : new int[]{};
            int[] bodyAfter = (pageNumber > 2 && pageNumber < totalPages - 3) ? new int[]{pageNumber+1, pageNumber+2} : new int[]{};
            int[] tail = (pageNumber < totalPages - 3) ? new int[]{-1, totalPages} : new int[] {totalPages-2, totalPages-1, totalPages};
            pages = merge(head, bodyBefore, bodyCenter, bodyAfter, tail);
        } else {
            pages = IntStream.rangeClosed(1, totalPages).toArray();
        }
        return pages;
    }

    private static int[] merge(int[] ...intarrays) {
        return Arrays.stream(intarrays).flatMapToInt(Arrays::stream)
                .toArray();
    }
}
