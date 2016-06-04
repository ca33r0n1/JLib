package com.j0ach1mmall3.jlib.integration.gist;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 8/01/16
 */
public final class GistFiles {
    private GistFile file;

    /**
     * Constructs a new GistFiles
     * @param file The GistFile in this GistFiles
     */
    public GistFiles(GistFile file) {
        this.file = file;
    }

    /**
     * Returns the GistFile in this GistFiles
     * @return The GistFile
     */
    public GistFile getFile() {
        return this.file;
    }

    /**
     * Sets the GistFile in this GistFiles
     * @param file The GistFile
     */
    public void setFile(GistFile file) {
        this.file = file;
    }
}