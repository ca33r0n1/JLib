package com.j0ach1mmall3.jlib.storage.serialization;

import java.util.Collection;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/11/2015
 */
public interface SerializedCollection extends JLibSerializable {
    /**
     * Returns the Collection
     * @return The Collection
     */
    Collection<String> getCollection();
}
