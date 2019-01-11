package io.wax911.support.markdown.extension

import io.wax911.support.markdown.creator.ExtensionCreator
import io.wax911.support.markdown.processor.WebMDelimiterProcessor
import org.commonmark.Extension
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import org.commonmark.renderer.text.TextContentRenderer

class WebMExtension private constructor(): Parser.ParserExtension, HtmlRenderer.HtmlRendererExtension,
    TextContentRenderer.TextContentRendererExtension {

    companion object: ExtensionCreator {
        /**
         * Creates an extension as part of a factory impl
         *
         * @return extension
         */
        override fun create(): Extension = WebMExtension()
    }

    override fun extend(parserBuilder: Parser.Builder?) {
        parserBuilder?.customDelimiterProcessor(WebMDelimiterProcessor())
    }

    override fun extend(rendererBuilder: HtmlRenderer.Builder?) {
        rendererBuilder?.nodeRendererFactory {
            TODO("implement html renderer for this type") //To change body of created functions use File | Settings | File Templates.
        }
    }

    override fun extend(rendererBuilder: TextContentRenderer.Builder?) {
        rendererBuilder?.nodeRendererFactory {
            TODO("implement text renderer for this type")
        }
    }
}