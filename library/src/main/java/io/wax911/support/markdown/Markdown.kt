package io.wax911.support.markdown

import android.content.Context
import android.text.method.LinkMovementMethod
import android.text.method.MovementMethod
import android.widget.TextView
import io.wax911.support.markdown.extension.WebMExtension
import org.commonmark.Extension
import org.commonmark.ext.gfm.strikethrough.StrikethroughExtension
import org.commonmark.ext.gfm.tables.TablesExtension
import org.commonmark.parser.Parser
import ru.noties.markwon.SpannableConfiguration
import ru.noties.markwon.renderer.SpannableRenderer
import ru.noties.markwon.spans.OrderedListItemSpan
import ru.noties.markwon.tasklist.TaskListExtension
import java.util.*

class Markdown {

    /**
     * Helper method to obtain a Parser with registered strike-through &amp; table extensions
     * &amp; task lists (added in 1.0.1)
     *
     * @return a Parser instance that is supported by this library
     * @since 1.0.0
     */
    fun createParser(): Parser {
        return Parser.Builder()
            .extensions(
                Arrays.asList<Extension>(
                    StrikethroughExtension.create(),
                    TablesExtension.create(),
                    TaskListExtension.create(),
                    WebMExtension.create()
                )
            ).build()
    }


    /**
     * @see .setMarkdown
     * @since 1.0.0
     */
    fun setMarkdown(view: TextView, markdown: String) {
        setMarkdown(view, SpannableConfiguration.create(view.context), markdown)
    }

    /**
     * Parses submitted raw markdown, converts it to CharSequence (with Spannables)
     * and applies it to view
     *
     * @param view          [TextView] to set markdown into
     * @param configuration a [SpannableConfiguration] instance
     * @param markdown      raw markdown String (for example: `` `**Hello**` ``)
     * @see .markdown
     * @see .setText
     * @see SpannableConfiguration
     *
     * @since 1.0.0
     */
    fun setMarkdown(
        view: TextView,
        configuration: SpannableConfiguration,
        markdown: String
    ) {

        setText(view, markdown(configuration, markdown))
    }

    /**
     * Helper method to apply parsed markdown.
     *
     *
     * Since 1.0.6 redirects it\'s call to [.setText]
     * with LinkMovementMethod as an argument to preserve current API.
     *
     * @param view [TextView] to set markdown into
     * @param text parsed markdown
     * @see .setText
     * @since 1.0.0
     */
    fun setText(view: TextView, text: CharSequence) {
        setText(view, text, LinkMovementMethod.getInstance())
    }

    /**
     * Helper method to apply parsed markdown with additional argument of a MovementMethod. Used
     * to workaround problems that occur when using system LinkMovementMethod (for example:
     * https://issuetracker.google.com/issues/37068143). As a better alternative to it consider
     * using: https://github.com/saket/Better-Link-Movement-Method
     *
     * @param view           TextView to set markdown into
     * @param text           parsed markdown
     * @param movementMethod an implementation if MovementMethod or null
     * @see .scheduleDrawables
     * @see .scheduleTableRows
     * @since 1.0.6
     */
    fun setText(view: TextView, text: CharSequence, movementMethod: MovementMethod?) {
        // @since 2.0.1 we must measure ordered-list-item-spans before applying text to a TextView.
        // if markdown has a lot of ordered list items (or text size is relatively big, or block-margin
        // is relatively small) then this list won't be rendered properly: it will take correct
        // layout (width and margin) but will be clipped if margin is not _consistent_ between calls.
        OrderedListItemSpan.measure(view, text)

        // update movement method (for links to be clickable)
        view.movementMethod = movementMethod
        view.text = text
    }

    /**
     * Returns parsed markdown with default [SpannableConfiguration] obtained from [Context]
     *
     * @param context  [Context]
     * @param markdown raw markdown
     * @return parsed markdown
     * @since 1.0.0
     */
    fun markdown(context: Context, markdown: String): CharSequence {
        val configuration = SpannableConfiguration.create(context)
        return markdown(configuration, markdown)
    }

    /**
     * Returns parsed markdown with provided [SpannableConfiguration]
     *
     * @param configuration a [SpannableConfiguration]
     * @param markdown      raw markdown
     * @return parsed markdown
     * @see SpannableConfiguration
     *
     * @since 1.0.0
     */
    fun markdown(configuration: SpannableConfiguration, markdown: String): CharSequence {
        val parser = createParser()
        val node = parser.parse(markdown)
        val renderer = SpannableRenderer()
        return renderer.render(configuration, node)
    }
}