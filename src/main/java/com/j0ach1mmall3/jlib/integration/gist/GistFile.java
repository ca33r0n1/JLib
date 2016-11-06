package com.j0ach1mmall3.jlib.integration.gist;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 8/01/16
 */
public final class GistFile {
    private String content;

    /**
     * Constructs a new GistFile
     *
     * @param content The Content in this GistFile
     */
    public GistFile(String content) {
        this.content = content;
    }

    /**
     * Returns the Content in this GistFile
     *
     * @return The Content
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Sets the Content in this GistFile
     *
     * @param content The Content
     */
    public void setContent(String content) {
        this.content = content;
    }
}
