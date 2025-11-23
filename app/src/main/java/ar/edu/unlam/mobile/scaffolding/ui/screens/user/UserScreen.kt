package ar.edu.unlam.mobile.scaffolding.ui.screens.user

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import ar.edu.unlam.mobile.scaffolding.ui.components.FloatingParticlesBackgroundAnimated
import ar.edu.unlam.mobile.scaffolding.ui.screens.feed.CirXD
import ar.edu.unlam.mobile.scaffolding.ui.theme.ColorTwo
import ar.edu.unlam.mobile.scaffolding.ui.theme.PetFinderFont
import ar.edu.unlam.mobile.scaffolding.ui.theme.SoftGray
import com.google.firebase.crashlytics.FirebaseCrashlytics

@Composable
fun UserScreen(
    onDetallesClick: () -> Unit,
    onMascotasClick: () -> Unit,
    onReportesClick: () -> Unit,
    onLogoutClick: () -> Unit,
) {
    val topbarHeightDp = 68.dp
    val topbarExclusionPx = with(LocalDensity.current) { topbarHeightDp.toPx() }
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(SoftGray),
    ) {
        CirXD(
            modifier =
                Modifier
                    .padding(top = 16.dp)
                    .offset(y = (-450).dp),
        )
        Text(
            text = "Usuario",
            fontFamily = PetFinderFont,
            color = ColorTwo,
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        FloatingParticlesBackgroundAnimated(
            particleCount = 24,
            excludeTopPx = topbarExclusionPx,
            modifier =
                Modifier
                    .fillMaxSize()
                    .zIndex(-1f),
        )

        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(100.dp))

            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .weight(1f),
                verticalArrangement = Arrangement.Top,
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(30.dp),
                ) {
                    ItemCard("Mis mascotas", onClick = onMascotasClick)

                    // 🔥 Forzar crash al tocar "Mis reportes"
                    ItemCard("Mis reportes", onClick = {
                        FirebaseCrashlytics.getInstance().log("Botón Mis reportes tocado")
                        throw RuntimeException("Crash de prueba desde Mis reportes")
                    })
                }
            }

            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LogoutButton(onClick = onLogoutClick)
                Spacer(modifier = Modifier.height(28.dp))
            }
        }
    }
}

// ---------- Card de item ----------
@Composable
fun ItemCard(
    title: String,
    onClick: () -> Unit,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(title, fontSize = 16.sp, color = Color(0xFF3C3C3C))
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color(0xFFB0B0B0),
            )
        }
    }
}

// ---------- Botón de logout ----------
@Composable
fun LogoutButton(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier =
            Modifier
                .fillMaxWidth()
                .height(52.dp),
        shape = RoundedCornerShape(28.dp),
        border = BorderStroke(1.5.dp, ColorTwo),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = ColorTwo),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Outlined.Logout,
                contentDescription = null,
                tint = ColorTwo,
                modifier =
                    Modifier
                        .size(20.dp)
                        .graphicsLayer { scaleX = -1f },
            )
            Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Text(
                    "Log out",
                    fontSize = 16.sp,
                    color = ColorTwo,
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserScreen() {
    UserScreen({}, {}, {}, {})
}
