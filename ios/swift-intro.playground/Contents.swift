import UIKit

let one = 1
let two: Int = 2

var n = 1.0

n += 2.0

var hi = "Hi!!!"

for c in hi {
    print(c)
}

hi.append("???")

var greet: String? = "Hello"


if var greet = greet {
    greet.append("!!!")
}
else {
    print("Greet is nil")
}

greet

func doGreet(_ who: String) {
    print("Hi \(who)")
}

var person: String? = "Giò"

if let person = person {
    doGreet(person)
}

var a: [Int] = [1, 2, 3]
var b = a

b.append(4)

b
a

func square(_ x: Int) -> Int {
    return x*x
}

func add1AndApply(_ x: Int, f: (Int) -> Int) -> Int {
    return f(x+1)
}

add1AndApply(5, f: square)

add1AndApply(5, f: { (x: Int) -> Int in
    let res = x*x*x
    return res
})

add1AndApply(5, f: { x in x*x*x })

add1AndApply(5, f: { $0*$0*$0 })

let r = 1...10 // 1..<10 == 1...9
for x in r {
    print(x)
}

r.map(square)

protocol Accellerate {
    var speed: Int {get set}
    mutating func accellerate()
}

class Car: Accellerate {
    var speed = 0
    func toggleActivation(){
        if speed == 0 {
            speed = 10
        }
        else {
            speed = 0
        }
    }
    
    func accellerate() {
        speed += 10
    }
}

let car1 = Car()
car1.toggleActivation()
car1.speed

let car2 = car1

car2.toggleActivation()

car1.accellerate()

car1.speed
 
struct CarStruct: Accellerate {
    
    var speed = 0
    
    mutating func toggleActivation(){
        if speed == 0 {
            speed = 10
        }
        else {
            speed = 0
        }
    }
    
    mutating func accellerate() {
        speed += 10
    }
    
    func log() {
        print(speed)
    }
}

var car3 = CarStruct()
car3.toggleActivation()

let car4 = car3

// car4.accellerate()

car3.speed
car4.speed
