package io.wax911.support.markdown.processor

import org.commonmark.node.CustomNode
import org.commonmark.node.Delimited
import org.commonmark.node.Text
import org.commonmark.parser.delimiter.DelimiterProcessor
import org.commonmark.parser.delimiter.DelimiterRun

class WebMDelimiterProcessor: DelimiterProcessor {
    /**
     * @return the character that marks the the ending of a delimited node, must not clash with any built-in special
     * characters. Note that for a symmetric delimiter such as "*", this is the same as the opening.
     */
    override fun getClosingCharacter() = WebM.CLOSING_DELIMITER

    /**
     * Determine how many (if any) of the delimiter characters should be used.
     *
     *
     * This allows implementations to decide how many characters to use based on the properties of the delimiter runs.
     * An implementation can also return 0 when it doesn't want to allow this particular combination of delimiter runs.
     *
     * @param opener the opening delimiter run
     * @param closer the closing delimiter run
     * @return how many delimiters should be used; must not be greater than length of either opener or closer
     */
    override fun getDelimiterUse(opener: DelimiterRun?, closer: DelimiterRun?): Int {
        if (opener != null && closer != null)
            if (opener.length() >= WebM.MINIMUM_LENGTH && closer.length() >= WebM.MINIMUM_LENGTH)
                // return the absolute length of the delimiter
                WebM.MINIMUM_LENGTH
        return 0
    }

    /**
     * Minimum number of delimiter characters that are needed to activate this. Must be at least 1.
     */
    override fun getMinLength() = WebM.MINIMUM_LENGTH

    /**
     * @return the character that marks the beginning of a delimited node, must not clash with any built-in special
     * characters
     */
    override fun getOpeningCharacter() = WebM.OPENING_DELIMITER

    /**
     * Process the matched delimiters, e.g. by wrapping the nodes between opener and closer in a new node, or appending
     * a new node after the opener.
     *
     *
     * Note that removal of the delimiter from the delimiter nodes and unlinking them is done by the caller.
     *
     * @param opener the text node that contained the opening delimiter
     * @param closer the text node that contained the closing delimiter
     * @param delimiterUse the number of delimiters that were used
     */
    override fun process(opener: Text?, closer: Text?, delimiterUse: Int) {
        val webM = WebM()
        var tempNode = opener?.next
        tempNode?.apply {
            if (this != closer) {
                val nextNode = next
                webM.appendChild(this)
                tempNode = nextNode
            }
        }
        opener?.insertAfter(webM)
    }

    private class WebM: CustomNode(), Delimited {

        companion object {
            const val OPENING_DELIMITER = 'w'
            const val CLOSING_DELIMITER = ')'
            const val MINIMUM_LENGTH = 6
        }

        /**
         * @return the opening (beginning) delimiter, e.g. `*`
         */
        override fun getOpeningDelimiter() = "webm("

        /**
         * @return the closing (ending) delimiter, e.g. `*`
         */
        override fun getClosingDelimiter() = ")"

    }
}