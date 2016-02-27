package com.j0ach1mmall3.jlib.integration.gist;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 8/01/16
 */
public final class Gist {
    private String description;
    private boolean publik;
    private GistFiles files;

    /**
     * Constructs a new Gist
     * @param description The description of this Gist
     * @param publik Whether this Gist is be public
     * @param files The GistFiles in this Gist
     */
    public Gist(String description, boolean publik, GistFiles files) {
        this.description = description;
        this.publik = publik;
        this.files = files;
    }

    /**
     * Returns the description of this Gist
     * @return The description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether this Gist is public
     * @return Whether this Gist is public
     */
    public boolean isPublik() {
        return this.publik;
    }

    /**
     * Returns the GistFiles in this Gist
     * @return the GistFiles
     */
    public GistFiles getFiles() {
        return this.files;
    }

    /**
     * Sets the description of this Gist
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets whether this Gist is public
     * @param publik Whether this Gist is public
     */
    public void setPublik(boolean publik) {
        this.publik = publik;
    }

    /**
     * Returns the GistFiles in this Gist
     * @param files the GistFiles
     */
    public void setFiles(GistFiles files) {
        this.files = files;
    }
}
