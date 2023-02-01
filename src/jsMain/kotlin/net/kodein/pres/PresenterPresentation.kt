package net.kodein.pres

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css
import org.w3c.dom.DOMRect


@Suppress("UNUSED_PARAMETER")
@Composable
internal fun PresenterPresentation(
    presentationContainer: Container,
    slideContainer: Container,
    slides: List<Slide>,
    defaultAnimation: Animation.Set,
    presentationSize: DOMRect?,
    currentState: SlideState,
    lastMoveWasForward: Boolean
) {
    Div({
        css {
            backgroundColor(Color.silver)
            top(0.percent)
            left(0.percent)
            width(100.percent)
            height(100.percent)
        }
    }) {
        Div({
            css {
                position(Position.Absolute)
                top(1.percent)
                left(1.percent)
                width(60.percent)
                height(60.percent)
            }
        }) {
            FullScreenPresentation(
                presentationContainer = presentationContainer,
                slideContainer = slideContainer,
                slides = slides,
                defaultAnimation = defaultAnimation,
                presentationSize = presentationSize?.let { DOMRect(width = it.width * 0.60, height = it.height * 0.60) },
                currentState = currentState,
                lastMoveWasForward = lastMoveWasForward
            )
        }

        Div({
            css {
                position(Position.Absolute)
                top(1.percent)
                right(1.percent)
                width(37.percent)
                height(37.percent)
            }
        }) {
            FullScreenPresentation(
                presentationContainer = presentationContainer,
                slideContainer = slideContainer,
                slides = slides,
                defaultAnimation = defaultAnimation,
                presentationSize = presentationSize?.let { DOMRect(width = it.width * 0.37, height = it.height * 0.37) },
                currentState = currentState.next(slides),
                lastMoveWasForward = lastMoveWasForward
            )
        }

        Div({
            css {
                position(Position.Absolute)
                bottom(1.percent)
                right(1.percent)
                width(37.percent)
                height(58.percent)
                fontSize(1.5.em)
                overflow("auto")
            }
        }) {
            slides.getOrNull(currentState.index)?.notes?.invoke(currentState.state)
        }

        Div({
            css {
                position(Position.Absolute)
                bottom(1.percent)
                left(1.percent)
                width(60.percent)
                height(37.percent)
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
            }
        }) {
            PresenterTools(slides, currentState)
        }
    }
}