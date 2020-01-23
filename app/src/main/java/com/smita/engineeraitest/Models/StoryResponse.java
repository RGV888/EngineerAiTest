package com.smita.engineeraitest.Models;

import java.util.List;

public class StoryResponse {

    private List<Hits> hits;
    private int nbPages;
    private String nbHits;
    private String page;
    private String hitsPerPage;

    public List<Hits> getHits() {
        return hits;
    }

    public void setHits(List<Hits> hits) {
        this.hits = hits;
    }

    public int getNbPages() {
        return nbPages;
    }

    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }

    public String getNbHits() {
        return nbHits;
    }

    public void setNbHits(String nbHits) {
        this.nbHits = nbHits;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getHitsPerPage() {
        return hitsPerPage;
    }

    public void setHitsPerPage(String hitsPerPage) {
        this.hitsPerPage = hitsPerPage;
    }
}
