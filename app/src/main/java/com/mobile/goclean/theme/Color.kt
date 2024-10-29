package com.mobile.goclean.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Slate50 = Color(0xFFF8FAFC)
val Slate100 = Color(0xFFF1F5F9)
val Slate200 = Color(0xFFE2E8F0)
val Slate300 = Color(0xFFCBD5E1)
val Slate400 = Color(0xFF94A3B8)
val Slate500 = Color(0xFF64748B)
val Slate600 = Color(0xFF475569)
val Slate700 = Color(0xFF334155)
val Slate800 = Color(0xFF1E293B)
val Slate900 = Color(0xFF0F172A)

val Gray50 = Color(0xFFF9FAFB)
val Gray100 = Color(0xFFF3F4F6)
val Gray200 = Color(0xFFE5E7EB)
val Gray300 = Color(0xFFD1D5DB)
val Gray400 = Color(0xFF9CA3AF)
val Gray500 = Color(0xFF6B7280)
val Gray600 = Color(0xFF4B5563)
val Gray700 = Color(0xFF374151)
val Gray800 = Color(0xFF1F2937)
val Gray900 = Color(0xFF111827)

val Red50 = Color(0xFFFEF2F2)
val Red100 = Color(0xFFFEE2E2)
val Red200 = Color(0xFFFECACA)
val Red300 = Color(0xFFFCA5A5)
val Red400 = Color(0xFFF87171)
val Red500 = Color(0xFFEF4444)
val Red600 = Color(0xFFDC2626)
val Red700 = Color(0xFFB91C1C)
val Red800 = Color(0xFF991B1B)
val Red900 = Color(0xFF7F1D1D)

val Blue50 = Color(0xFFEFF6FF)
val Blue100 = Color(0xFFDBEAFE)
val Blue200 = Color(0xFFBFDBFE)
val Blue300 = Color(0xFF93C5FD)
val Blue400 = Color(0xFF60A5FA)
val Blue500 = Color(0xFF3B82F6)
val Blue600 = Color(0xFF2563EB)
val Blue700 = Color(0xFF1D4ED8)
val Blue800 = Color(0xFF1E40AF)
val Blue900 = Color(0xFF1E3A8A)

val Green50 = Color(0xFFECFDF5)
val Green100 = Color(0xFFD1FAE5)
val Green200 = Color(0xFFA7F3D0)
val Green300 = Color(0xFF6EE7B7)
val Green400 = Color(0xFF34D399)
val Green500 = Color(0xFF10B981)
val Green600 = Color(0xFF059669)
val Green700 = Color(0xFF047857)
val Green800 = Color(0xFF065F46)
val Green900 = Color(0xFF064E3B)

val Yellow50 = Color(0xFFFEFCE8)
val Yellow100 = Color(0xFFFEF9C3)
val Yellow200 = Color(0xFFFEF08A)
val Yellow300 = Color(0xFFFDE047)
val Yellow400 = Color(0xFFFACC15)
val Yellow500 = Color(0xFFEAB308)
val Yellow600 = Color(0xFFCA8A04)
val Yellow700 = Color(0xFFA16207)
val Yellow800 = Color(0xFF854D0E)
val Yellow900 = Color(0xFF713F12)

val Amber50 = Color(0xFFFFFBEB)
val Amber100 = Color(0xFFFEF3C7)
val Amber200 = Color(0xFFFDE68A)
val Amber300 = Color(0xFFFCD34D)
val Amber400 = Color(0xFFFBBF24)
val Amber500 = Color(0xFFF59E0B)
val Amber600 = Color(0xFFD97706)
val Amber700 = Color(0xFFB45309)
val Amber800 = Color(0xFF92400E)
val Amber900 = Color(0xFF78350F)

val Orange50 = Color(0xFFFFF7ED)
val Orange100 = Color(0xFFFFEDD5)
val Orange200 = Color(0xFFFED7AA)
val Orange300 = Color(0xFFFDBA74)
val Orange400 = Color(0xFFFB923C)
val Orange500 = Color(0xFFF97316)
val Orange600 = Color(0xFFEA580C)
val Orange700 = Color(0xFFC2410C)
val Orange800 = Color(0xFF9A3412)
val Orange900 = Color(0xFF7C2D12)

val Cyan50 = Color(0xFFE0F2FE)
val Cyan100 = Color(0xFFBAE6FD)
val Cyan200 = Color(0xFF7DD3FC)
val Cyan300 = Color(0xFF38BDF8)
val Cyan400 = Color(0xFF0EA5E9)
val Cyan500 = Color(0xFF06B6D4)
val Cyan600 = Color(0xFF0891B2)
val Cyan700 = Color(0xFF0E7490)
val Cyan800 = Color(0xFF155E75)
val Cyan900 = Color(0xFF164E63)

val Teal50 = Color(0xFFF0FDFA)
val Teal100 = Color(0xFFCCFBF1)
val Teal200 = Color(0xFF99F6E4)
val Teal300 = Color(0xFF5EEAD4)
val Teal400 = Color(0xFF2DD4BF)
val Teal500 = Color(0xFF14B8A6)
val Teal600 = Color(0xFF0D9488)
val Teal700 = Color(0xFF0F766E)
val Teal800 = Color(0xFF115E59)
val Teal900 = Color(0xFF134E4A)

val Emerald50 = Color(0xFFECFDF5)
val Emerald100 = Color(0xFFD1FAE5)
val Emerald200 = Color(0xFFA7F3D0)
val Emerald300 = Color(0xFF6EE7B7)
val Emerald400 = Color(0xFF34D399)
val Emerald500 = Color(0xFF10B981)
val Emerald600 = Color(0xFF059669)
val Emerald700 = Color(0xFF047857)
val Emerald800 = Color(0xFF065F46)
val Emerald900 = Color(0xFF064E3B)

// Custom Template
val PrimaryColor = Color(0xFF00C853)
val BackgroundColor = Color(0xFF00C853)
val BackgroundGradient =
    Brush.linearGradient(
        colors =
            listOf(
                Color(0xFF00C853),
                Green600,
            ),
        start = Offset(0f, 0f),
        end = Offset(0f, Float.POSITIVE_INFINITY),
    )

val SubBackgroundColor = Color(0xFF757575)
val OnPrimaryColor = Color.White
val TitleTextColor = Color(0xFF212121)
val SubTextColor = Color(0xFF757575)
val ErrorColor = Color(0xFF1F1F1F)
val SuccessColor = Color(0xFF25D162)
val WarningColor = Color(0xFFF59E0B)
val infoColor = Color(0xFF007AFF)
