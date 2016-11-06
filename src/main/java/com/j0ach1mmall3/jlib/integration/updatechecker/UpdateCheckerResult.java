package com.j0ach1mmall3.jlib.integration.updatechecker;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 9/12/15
 */
public final class UpdateCheckerResult {
    private final ResultType type;
    private final String newVersion;

    /**
     * Constructs a new UpdateCheckerResult
     *
     * @param type       The Result Type
     * @param newVersion The new version, if available
     */
    UpdateCheckerResult(ResultType type, String newVersion) {
        this.type = type;
        this.newVersion = newVersion;
    }

    /**
     * Returns the Result Type
     *
     * @return The Result Type
     */
    public ResultType getType() {
        return this.type;
    }

    /**
     * Returns the new version, if available
     *
     * @return The new version
     */
    public String getNewVersion() {
        return this.newVersion;
    }

    public enum ResultType {
        NEW_UPDATE,
        UP_TO_DATE,
        ERROR
    }
}
