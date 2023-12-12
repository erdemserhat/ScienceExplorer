package com.erdemserhat.scienceexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erdemserhat.scienceexplorer.SampleData.informationCardsList
import com.erdemserhat.scienceexplorer.SampleData.scientistList
import com.erdemserhat.scienceexplorer.ui.theme.ScienceExplorerTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScienceExplorerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val windowSizeClass = calculateWindowSizeClass(this)
                    ScienceExplorerApp(windowSize = windowSizeClass)

                }
            }
        }
    }
}

//Scientist Search Bar
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
        //setting an icon for tf
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        //overriding default colors for tf
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}


//Scientist Profile Element
@Composable
fun ScientistProfileElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = null,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(id = text),
            modifier = modifier
                //or you can use Spacer()
                .paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium

        )

    }
}

//InformationCard
@Composable
fun InformationCard(
    modifier: Modifier = Modifier,
    @DrawableRes drawable: Int,
    @StringRes text: Int
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier,

        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp)

        ) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )

            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

        }

    }
}

//Scientist Profile Row
@Composable
fun ScientistProfilesRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .padding(horizontal = 16.dp)
    ) {
        items(scientistList) { item ->
            ScientistProfileElement(drawable = item.drawable, text = item.text)
        }

    }
}

//InformationGrid
@Composable
fun InformationCardGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(informationCardsList) { item ->
            InformationCard(
                drawable = item.drawable,
                text = item.text,
                modifier = Modifier.height(80.dp)
            )


        }
    }
}


//Home
@Composable
fun HomeSection(
    modifier: Modifier = Modifier,
    //parameters for slot-based layouts
    @StringRes title: Int,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 32.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

//Home Screen
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())

    ) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.scientists_title, content = { ScientistProfilesRow() })
        HomeSection(title = R.string.information_cards_title, content = { InformationCardGrid() })
        Spacer(Modifier.height(16.dp))


    }
}


//Bottom Navigation Bar
@Composable
private fun ScienceExplorerBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Spa,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_home)
                )
            },
            selected = true,
            onClick = {}

        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },

            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_profile)
                )
            },
            selected = false,
            onClick = {}


        )

    }
}


//portrait
@Composable
fun ScienceExplorerPortrait() {
    ScienceExplorerTheme {
        Scaffold(
            bottomBar = { ScienceExplorerBottomNavigation()}
        ) {padding->
            HomeScreen(Modifier.padding(padding))

        }
    }
}


@Composable
private fun ScienceExplorerNavigationRail(modifier: Modifier = Modifier) {
    NavigationRail(
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Spa,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_home))
                },
                selected = true,
                onClick = {}
            )

            NavigationRailItem(
                icon={
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_profile))
                },
                selected = false,
                onClick = {}

            )


        }

    }
}


//Landscape Mode
@Composable
fun ScienceExplorerAppLandscape() {
    ScienceExplorerTheme {
        Row {
            ScienceExplorerNavigationRail()
            HomeScreen()
        }
    }
}


@Composable
fun ScienceExplorerApp(windowSize: WindowSizeClass) {
    when(windowSize.widthSizeClass){
        WindowWidthSizeClass.Compact -> {
            ScienceExplorerPortrait()
        }
        WindowWidthSizeClass.Expanded -> {
            ScienceExplorerAppLandscape()
        }
    }
}

@Composable
private fun SootheNavigationRail(modifier: Modifier = Modifier) {
    NavigationRail(
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Spa,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_home))
                },
                selected = true,
                onClick = {}
            )

            NavigationRailItem(
                icon={
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_profile))
                },
                selected = false,
                onClick = {}

            )


        }

    }
}

//Previews

//Searchbar
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun SearchBarPreview() {
    ScienceExplorerTheme { SearchBar(Modifier.padding(8.dp)) }
}

//Scientist Profile Preview
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun ScientistProfileElementPreview() {
    ScienceExplorerTheme {
        ScientistProfileElement(
            modifier = Modifier.padding(8.dp),
            drawable = R.drawable.edison,
            text = R.string.thomas_edison
        )
    }
}

//Information Card Preview
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun InformationCardPreview() {
    ScienceExplorerTheme {
        InformationCard(
            modifier = Modifier.padding(8.dp),
            drawable = R.drawable.infinite_digits_pi,
            text = R.string.infinite_digits_pi
        )
    }
}

//Information Card Gird Preview
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun InformationCardGridPreview() {
    ScienceExplorerTheme { InformationCardGrid() }
}

//Scientists Profiles Row Preview
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodyRowPreview() {
    ScienceExplorerTheme { ScientistProfilesRow() }
}

//Home Section
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    ScienceExplorerTheme {
        HomeSection(title = R.string.scientists_title) {
            ScientistProfilesRow()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun ScreenContentPreview() {
    ScienceExplorerTheme { HomeScreen() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun BottomNavigationPreview() {
    ScienceExplorerTheme { ScienceExplorerBottomNavigation(Modifier.padding(top = 24.dp)) }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun NavigationRailPreview() {
    ScienceExplorerTheme { SootheNavigationRail() }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun ScienceExplorerPortraitPreview() {
    ScienceExplorerPortrait()
}

@Preview(widthDp = 640, heightDp = 360)
@Composable
fun ScienceExplorerLandscapePreview() {
    ScienceExplorerAppLandscape()
}



