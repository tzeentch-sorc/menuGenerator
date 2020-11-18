package edu.netcracker.menugenerator.util;

import org.springframework.data.domain.Slice;
import org.springframework.http.HttpHeaders;

public class PaginationUtil {
    public static HttpHeaders generateSliceHttpHeaders(Slice<?> slice) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Has-Next-Page", "" + slice.hasNext());
        return headers;
    }
}
