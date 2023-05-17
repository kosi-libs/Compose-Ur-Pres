import org.kodein.compose.html.pres.Slide
import org.kodein.compose.html.pres.Transitions.Fade
import org.kodein.compose.html.pres.hiddenIf
import org.kodein.compose.html.pres.sourcecode.*
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text


val sourceCode = Slide(
    name = "source-code",
    stateCount = 5,
) { state ->

    H1 {
        Text("You can display source code")
    }

    SourceCode(
        lang = "kotlin",
        code = """
            @Composable
            fun <T> Content(state: Int = 0, value: T) {
                Text(«str:"«cw:Compose Web»«cup:ComposeUrPres» is cool!"»)
            «com:    // Code animations are amazing!
            »}
        """.trimIndent(),
    ) {
        "cw" { fontGrow(state < 2) }
        "cup" { fontGrow(state >= 2) }
        "str" { zoomed(state == 3) }
        "com" { lineHeight(state >= 4) }
    }

    P({
        hiddenIf(state < 1, Fade())
    }) {
        Text("Highlighting is done by ")
        A("https://highlightjs.org/", {
            target(ATarget.Blank)
        }) {
            Text("highlight.js")
        }
    }
}
