// n = window.prompt("Enter a name:");
// window.alert("hello " + n)

// if (true) {
//     console.log("hello")
// }

// let x = 5

// arrays 

/*
let arr = [1, 2, 3, 4, 5]
arr = arr + [6, 7, 8]

arr.push(9)
let x = arr.pop()

arr.unshift(0)
let y = arr.shift()
*/

// objects - JSON

let obj = {
    name: "John",
    age: 25,
    address: {
        city: "Bangalore",
        state: "Karnataka"
    },
    hobbies: ["reading", "coding", "music"],
    sayHello: function () {
        console.log("Hello")
    }
}

let obj2 = {
    name: "Mary",
    age: 25,
    address: {
        city: "Bangalore",
        state: "Karnataka"
    },
    hobbies: ["reading", "coding", "music"]
}

arr = [obj, obj2]

// arr[0].sayHello()

// function

// function sayHello() {
//     console.log("Hello")
// }

let operate = function (evt) {
    evt.preventDefault()
    // console.log(evt)
    let A = document.querySelector("#num1").value
    let B = document.querySelector("#num2").value
    var op = document.querySelector("#op").selectedIndex
    let C = 0
    if (op == 0) {
        C = parseInt(A) + parseInt(B)
    } else if (op == 1) {
        // ....
    } else {
        // error
        document.querySelector("#error-message").computedStyleMap.display = "block"
    }
    
    document.querySelector("#result").innerHTML = C

}

// operate()  // call function

// window.setInterval(operate, 500)  // call function every 1/2 second

// let a = document.querySelector("h1")
// a.innerHTML = "Hello"

// perform math operation
function clicked(evt){
    console.log(evt.clientX, evt.clientY)
}

var num1 = document.querySelector("#num1").value
var num2 = document.querySelector("#num2").value

document.querySelector("form").addEventListener("submit", operate)

document.querySelector("#num1").addEventListener("click", clicked)