package io.wax911.support.markdown.creator

import org.commonmark.Extension

interface ExtensionCreator {
    /**
     * Creates an extension as part of a factory impl
     *
     * @return extension
     */
    fun create(): Extension
}