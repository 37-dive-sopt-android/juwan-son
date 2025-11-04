package com.sopt.dive.presentation.main.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.core.util.noRippleClickable
import com.sopt.dive.presentation.main.MainTab
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun MainBottomBar(
    tabs: ImmutableList<MainTab>,
    currentTab: MainTab?,
    onTabSelected: (MainTab) -> Unit,
    modifier: Modifier = Modifier,
) {

    Surface(
        color = Color.DarkGray,
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 5.dp)
                .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            tabs.forEach { tab ->
                MainNavigationBarItem(
                    selected = tab == currentTab,
                    tab = tab,
                    onClick = { onTabSelected(tab) },
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 12.dp),
                )
            }
        }
    }
}

@Composable
private fun MainNavigationBarItem(
    selected: Boolean,
    tab: MainTab,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val iconRes = if (selected) tab.selectedIcon else tab.unselectedIcon
    val textColor = if (selected) Color.Gray else Color.White

    Column(
        modifier = modifier
            .noRippleClickable(onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(iconRes),
            contentDescription = stringResource(tab.contentDescription),
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp),
        )
        Text(
            text = stringResource(tab.contentDescription),
            color = textColor,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview
@Composable
private fun MainBottomBarPreview() {
    var currentTab by remember { mutableStateOf(null) }
    MainBottomBar(
        tabs = MainTab.entries.toImmutableList(),
        currentTab = currentTab,
        onTabSelected = { }
    )
}