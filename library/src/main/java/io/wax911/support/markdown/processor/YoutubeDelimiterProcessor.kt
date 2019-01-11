package io.wax911.support.markdown.processor

import org.commonmark.node.Text
import org.commonmark.parser.delimiter.DelimiterProcessor
import org.commonmark.parser.delimiter.DelimiterRun

class YoutubeDelimiterProcessor: DelimiterProcessor {
    /**
     * @return the character that marks the the ending of a delimited node, must not clash with any built-in special
     * characters. Note that for a symmetric delimiter such as "*", this is the same as the opening.
     */
    override fun getClosingCharacter(): Char {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Minimum number of delimiter characters that are needed to activate this. Must be at least 1.
     */
    override fun getMinLength(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * @return the character that marks the beginning of a delimited node, must not clash with any built-in special
     * characters
     */
    override fun getOpeningCharacter(): Char {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}