package com.j0ach1mmall3.jlib.storage.file.json;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 15/01/16
 */
public abstract class JsonConfiguration {
    protected String doNotChange;

    /**
     * Returns the version of the Config
     * @return The version of the Config
     */
    public final String getDoNotChange() {
        return this.doNotChange;
    }

    /**
     * Sets the version of the Config
     * @param doNotChange The version of the Config
     */
    public final void setDoNotChange(String doNotChange) {
        this.doNotChange = doNotChange;
    }
}
