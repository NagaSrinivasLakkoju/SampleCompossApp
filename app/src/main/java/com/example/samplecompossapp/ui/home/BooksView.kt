package com.example.samplecompossapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.apollographql.apollo3.api.ApolloResponse
import com.example.rocketreserver.GetCarrierDetailsQuery
import com.example.rocketreserver.GetMemberDetailsQuery
import com.example.samplecompossapp.R
import com.example.samplecompossapp.model.Movie
import com.example.samplecompossapp.ui.listcards.MovieCard
import com.example.samplecompossapp.ui.theme.Typography
import com.example.samplecompossapp.ui.viewmodel.CountryViewModel

@Composable
fun BooksView() {
    val countryViewModel = hiltViewModel<CountryViewModel>()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.TopCenter)
    ) {

        LaunchedEffect(Unit){
            countryViewModel.getCarriers()
            countryViewModel.getMember()
        }



        when (val state = countryViewModel.uiState.collectAsState().value) {
            is CountryViewModel.CountryUiState.Empty -> Text(
                text = stringResource(R.string.no_data_available),
                modifier = Modifier.padding(16.dp)
            )
            is CountryViewModel.CountryUiState.Loading ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            is CountryViewModel.CountryUiState.Error -> BooksErrorDialog(state.message)
            is CountryViewModel.CountryUiState.Loaded -> CarrierResponse(state.data)
        }

        when (val memberState = countryViewModel.memberuiState.collectAsState().value) {
            is CountryViewModel.MemberUiState.Empty -> Text(
                text = stringResource(R.string.no_data_available),
                modifier = Modifier.padding(16.dp)
            )
            is CountryViewModel.MemberUiState.Loaded -> MemberResponse(memberState.data)
        }
    }
}

@Composable
fun CarrierResponse(data: ApolloResponse<GetCarrierDetailsQuery.Data>) {

    Column(
        Modifier.padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxWidth())
        {
            Text(text = "Carrier Details", style = Typography.h3)
        }
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically)
        {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data.data!!.carrier!!.logoUrl)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                modifier = Modifier.width(80.dp).height(80.dp),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(20.dp))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data.data!!.applicationSettings.nationsLogoUrl)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                modifier = Modifier.width(120.dp).height(100.dp),
                contentDescription = null
            )
        }

        Text(text = "ID :  ${data.data!!.carrier!!.id}", style = TextStyle(fontSize = 18.sp))
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Plan :  ${data.data!!.carrier!!.name}", style = TextStyle(fontSize = 18.sp))
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Phone Number :  ${data.data!!.carrier!!.phoneNumber}", style = TextStyle(fontSize = 18.sp))

    }
}

@Composable
fun MemberResponse(data: ApolloResponse<GetMemberDetailsQuery.Data>) {

    Column(
        Modifier.padding(20.dp)
    ) {

        Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxWidth())
        {
            Text(text = "Member Details", style = Typography.h3)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Member ID :  ${data.data!!.member!!.nhMemberId}", style = TextStyle(fontSize = 18.sp))
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Member Name :  ${data.data!!.member!!.firstName}", style = TextStyle(fontSize = 18.sp))
    }
}

/*

@Composable
fun CountryList(countryList: ApolloResponse<GetAllCountryQuery.Data>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ){
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Countries using Graphql",
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.teal_700),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.height(10.dp))



     */
/*   LazyColumn {
            items(countryList.data?.countries?.size!!) {  item ->
                CountryCard(country = countryList.data?.countries?.get(item)!!)
            }
        }*//*



        //commented

       */
/* LazyColumn {
            itemsIndexed(items = countryList) { index, item ->
                CountryCard(movie = item)
            }
        }*//*

    }


}
*/


@Preview(showBackground = true)
@Composable
fun BooksViewPreview() {
    val movie = Movie(
        "Coco",
        "https://howtodoandroid.com/images/coco.jpg",
        "Coco is a 2017 American 3D computer-animated musical fantasy adventure film produced by Pixar",
        "Latest"
    )

    MovieCard(movie = movie)
}

@Composable
fun BooksErrorDialog(message: String) {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = stringResource(R.string.problem_occurred))
            },
            text = {
                Text(message)
            },
            confirmButton = {
                openDialog.value = false
            }
        )
    }
}
