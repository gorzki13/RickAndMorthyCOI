package com.jg.rickandmorthycoi.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val Primary   = Color(0xFF1E88E5)
val OnPrimary = Color.White

val Secondary   = Color(0xFFD81B60)
val OnSecondary = Color.White

val Background   = Color(0xFFF5F5F5)
val OnBackground = Color(0xFF212121)

val Surface   = Color.White
val OnSurface = Color(0xFF212121)

val LightColors = lightColorScheme(
    primary        = Primary,
    onPrimary      = OnPrimary,
    secondary      = Secondary,
    onSecondary    = OnSecondary,
    background     = Background,
    onBackground   = OnBackground,
    surface        = Surface,
    onSurface      = OnSurface
)

val DarkColors = darkColorScheme(
    primary        = Primary,
    onPrimary      = OnPrimary,
    secondary      = Secondary,
    onSecondary    = OnSecondary,
    background     = Color(0xFF121212),
    onBackground   = Color(0xFFE0E0E0),
    surface        = Color(0xFF1E1E1E),
    onSurface      = Color(0xFFE0E0E0)
)
