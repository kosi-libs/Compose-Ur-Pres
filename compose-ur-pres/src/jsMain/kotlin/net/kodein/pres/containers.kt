package net.kodein.pres

import androidx.compose.runtime.Composable
import net.kodein.pres.util.transition
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Div
import org.kodein.cic.css
import org.w3c.dom.HTMLDivElement


public interface ContainerAttrs {
    public val containerAttrs: AttrBuilderContext<HTMLDivElement>?
}

public fun ContainerAttrs(attrs: AttrBuilderContext<HTMLDivElement>? = null): ContainerAttrs = object : ContainerAttrs {
    override val containerAttrs: AttrBuilderContext<HTMLDivElement>? get() = attrs
}

@Composable
public fun PresentationState.presentationContainer(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: @Composable () -> Unit
) {
    Div({
        css {
            width(100.percent)
            height(100.percent)
            position(Position.Absolute)
            top(0.px)
            left(0.px)
            overflow("hidden")
        }
        attrs?.invoke(this)
        if (slideConfig is ContainerAttrs) slideConfig.containerAttrs?.invoke(this)
    }) {
        content()
    }
}

public interface OverlayAttrs : ContainerAttrs {
    public val overlayAttrs: AttrBuilderContext<HTMLDivElement>?
}

public fun OverlayAttrs(
    containerAttrs: AttrBuilderContext<HTMLDivElement>? = null,
    overlayAttrs: AttrBuilderContext<HTMLDivElement>?
): OverlayAttrs = object : OverlayAttrs {
    override val containerAttrs: AttrBuilderContext<HTMLDivElement>? get() = containerAttrs
    override val overlayAttrs: AttrBuilderContext<HTMLDivElement>? get() = overlayAttrs
}

@Composable
public fun PresentationState.overlayedPresentationContainer(
    containerAttrs: AttrBuilderContext<HTMLDivElement>? = null,
    overlayAttrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: @Composable () -> Unit
) {
    presentationContainer(containerAttrs) {
        presentationContainer({
            overlayAttrs?.invoke(this)
            (slideConfig as? OverlayAttrs)?.overlayAttrs?.invoke(this)
        }) {
            content()
        }
    }
}

@Composable
public fun PresentationState.progress(
    color: CSSColorValue,
    height: CSSNumeric = 0.3.cssRem
) {
    Div({
        css {
            height(height)
            position(Position.Absolute)
            left(0.px)
            bottom(0.px)
            backgroundColor(color)
            transition { "width"(0.3.s) }
        }
        style {
            width((globalState.toDouble() / (globalStateCount - 1).toDouble() * 100.0).percent)
        }
    }) {}
}

@Suppress("unused")
@Composable
public fun PresentationState.slideContainer(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: @Composable () -> Unit
) {
    Div({
        css {
            width(100.percent - 4.em)
            height(100.percent - 4.em)
            padding(2.em)
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Column)
            justifyContent(JustifyContent.Center)
            alignItems(AlignItems.Center)
        }
        attrs?.invoke(this)
    }) {
        content()
    }
}
