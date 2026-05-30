package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.KidsGreen

data class ColorItem(val name: String, val color: Color)

val ColorsList = listOf(
    ColorItem("Red", Color(0xFFFF5252)),
    ColorItem("Blue", Color(0xFF448AFF)),
    ColorItem("Green", Color(0xFF69F0AE)),
    ColorItem("Yellow", Color(0xFFFFD740)),
    ColorItem("Orange", Color(0xFFFFAB40)),
    ColorItem("Purple", Color(0xFFE040FB)),
    ColorItem("Pink", Color(0xFFFF4081)),
    ColorItem("Black", Color(0xFF212121)),
    ColorItem("White", Color(0xFFFFFFFF)),
    ColorItem("Brown", Color(0xFF795548))
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorsScreen(onBack: () -> Unit) {
    var selectedColor by remember { mutableStateOf<ColorItem?>(null) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Learn Colors", fontWeight = FontWeight.Bold, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                       Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = KidsGreen)
            )
        }
    ) { padding ->
        if (selectedColor == null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(com.example.ui.theme.ThemeBackground),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(ColorsList) { item ->
                    ColorCard(item) {
                        selectedColor = item
                    }
                }
            }
        } else {
             BigPreviewScreen(
                 text = selectedColor!!.name,
                 subText = "",
                 backgroundColor = selectedColor!!.color,
                 onClose = { selectedColor = null }
             )
        }
    }
}

@Composable
fun ColorCard(item: ColorItem, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(item.color)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        val textColor = if (item.name == "White" || item.name == "Yellow") Color.Black else Color.White
        Text(
            text = item.name,
            fontSize = 36.sp,
            fontWeight = FontWeight.ExtraBold,
            color = textColor
        )
    }
}
