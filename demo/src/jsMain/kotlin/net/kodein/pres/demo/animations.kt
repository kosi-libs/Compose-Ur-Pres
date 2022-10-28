package net.kodein.pres.demo

import net.kodein.pres.Animations
import net.kodein.pres.OverlayAttrs
import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.emojis.Emoji
import net.kodein.pres.hiddenIf
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.kodein.cic.css
import kotlin.time.Duration.Companion.seconds


val animations = Slide(
    name = "container",
    stateCount = 2,
    inAnimation = Animations.Flip(2.seconds),
    config = {
        OverlayAttrs {
            css {
                backgroundColor(Color("#480F40"))
            }
        }
    }
) { state ->
    H1 {
        Text("There can also be complex slide transition animations!")
    }
    P({
        hiddenIf(state < 1, fade)
    }) {
        Text("Have you noticed the background change? ${Emoji.thinking}")
    }
}
