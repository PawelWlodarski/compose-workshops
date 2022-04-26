package com.wlodar.jug.compose.exercises.ex1basics

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun Exercise1Demo() {
    Exercise1TextExamples()
//    Exercise1RowsAndColumns()
//    Exercise1DeeperIntoModifiers()
//    Exercise1LayoutAsProgram()
//    Exercise1ModifiersOrder()
}


@Composable
fun Exercise1TextExamples() {

    Text("This is simple Text Composable, look inside...")

    /*//explain import mess when uncommenting this part
    Text(
        "Stylized Text",
        style = TextStyle(
            color = Color.Red,
            fontSize = 16.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W800,
            fontStyle = FontStyle.Italic,
            letterSpacing = 0.5.em,
            background = Color.LightGray,
            textDecoration = TextDecoration.Underline
        )
    )*/

    //uncomment both texts and use column - use Intellij shortcut

}

@Composable
fun Exercise1RowsAndColumns(){
    @Composable
    fun ButtonExample2(label:String){
        Button(onClick = { }) {
            Text(label)
        }
    }

    //Modifier.padding(5.dp)

    //Modifier.padding(5.dp).fillMaxWidth(),
    //horizontalArrangement = Arrangement.SpaceBetween

    Column {
        Row {
            ButtonExample2(label = "1")
            ButtonExample2(label = "2")
            ButtonExample2(label = "3")
        }
    }

    //* Show button's row scope
    //* show interactive mode
    //* show weight and explain problem with columnScope
    //Modifier.weight(1f)
}

@Composable
fun Exercise1DeeperIntoModifiers(){
    @Composable
    fun ButtonExample3(label:String){
        Button(onClick = { }) {
            Text(label)
        }
    }

    @Composable
    fun ExampleRow(label1:String,label2:String){ // modifier:Modifier=Modifier
        //weight - not working
        //modifier = Modifier.padding(5.dp).composed { modifier }
        Row(modifier = Modifier.padding(5.dp)) {
            ButtonExample3(label = label1)
            ButtonExample3(label = label2)
        }
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        ExampleRow(label1 = "1", label2 = "2") //Modifier.weight(2f)
        ExampleRow(label1 = "3", label2 = "4")
    }


    //Bonus
   /* @Composable
    fun ColumnScope.CustomRowInsideColumn(){
        Row(modifier = Modifier
            .padding(5.dp)
            .weight(2f)) {
            Text(text = "1")
            Text(text = "2")
        }
    }

    @Composable
    fun ColumnScopeAsParameter(cs:ColumnScope) =
        with(cs){
            Row(modifier = Modifier
                .padding(5.dp)
                .weight(2f)) {
                Text(text = "1")
                Text(text = "2")
            }
        }

    Column {
        CustomRowInsideColumn()
        val column=this
        Row{
//            CustomRowInsideColumn()
            this@Column.CustomRowInsideColumn()
            column.CustomRowInsideColumn()
        }

        ColumnScopeAsParameter(this)
    }*/


}

@Composable
fun Exercise1LayoutAsProgram(){
    @Composable
    fun GeneratoedBox(i: Int, j: Int) {
        Box(
            modifier = Modifier
                .border(BorderStroke(1.dp, Color.Red))
                .padding(1.dp)
        ) {
            Text("[$i,$j]")
        }
    }

    //show some refactoring
    Column{
        (1 .. 3).forEach {i->
            Row(Modifier.padding(1.dp)){
                (1 .. 3).forEach {j ->
                    GeneratoedBox(i, j)
                }
            }

        }
    }

}



@Composable
fun Exercise1ModifiersOrder(){

    Surface(color = Color.Gray,
        modifier = Modifier
            .clickable {  }
            .padding(30.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

    }

//    Surface(color = Color.Gray,
//        modifier = Modifier
//            .padding(30.dp)
//            .clickable {  }
//            .fillMaxWidth()
//            .fillMaxHeight()
//    ) {
//
//    }

}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Exercise1Demo()
}