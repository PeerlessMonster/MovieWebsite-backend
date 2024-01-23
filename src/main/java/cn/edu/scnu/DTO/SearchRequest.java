package cn.edu.scnu.DTO;

import java.util.List;

public class SearchRequest {
    private List<String> category;
    private List<String> region;
    private String search;
    private String keyword;
    private String sort;
    private boolean descend;

    public List<String> getCategory() {
        return category;
    }

    public List<String> getRegion() {
        return region;
    }

    public String getSearch() {
        return search;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getSort() {
        return sort;
    }

    public boolean isDescend() {
        return descend;
    }
}
