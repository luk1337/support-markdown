package co.anitrend.support.markdown.center

import androidx.annotation.VisibleForTesting
import co.anitrend.support.markdown.common.IMarkdownPlugin
import io.noties.markwon.AbstractMarkwonPlugin
import java.lang.reflect.Modifier

/**
 * To center-align text, surround it with either __~~~...~~~__ or __`<center>...</center>`__
 *
 * @since 0.1.0
 */
class CenterPlugin private constructor(): IMarkdownPlugin, AbstractMarkwonPlugin() {

    /**
     * Regular expression that should be used for the implementing classing
     */
    override val regex = Regex(
        pattern = PATTERN_CENTER,
        option = RegexOption.IGNORE_CASE
    )

    override fun processMarkdown(markdown: String): String {
        var replacement = markdown
        val matches = regex.findAll(markdown)
        matches.forEach { matchResult ->
            val fullMatch = matchResult.groupValues[GROUP_ORIGINAL_MATCH]
            val contentMatch = matchResult.groupValues[GROUP_CONTENT]

            replacement = replacement.replace(
                fullMatch,
                "<align center>$contentMatch</align>"
            )
        }
        return replacement
    }

    companion object {

        @VisibleForTesting(otherwise = Modifier.PRIVATE)
        const val PATTERN_CENTER = "~~~([\\s\\S]*?)~~~"

        private const val GROUP_ORIGINAL_MATCH = 0
        private const val GROUP_CONTENT = 1

        fun create() =
            CenterPlugin()
    }
}