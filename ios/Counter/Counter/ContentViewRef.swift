//
//  ContentView.swift
//  Counter
//
//  Created by Giovanni Quattrocchi on 11/12/20.
//

import SwiftUI

struct ContentViewRef: View {
    
    @StateObject var counter = Model()
    
    var body: some View {
        VStack(content: {
            Text("\(Int(counter.value))").bold().font(.title)
            HStack(content:{
                Button(action: {
                    counter.subOne()
                }){
                    Text("-1")
                        .bold()
                        .font(.headline)
                        .accentColor(.white)
                        .padding(10)
                        .background(Color.blue)
                }.disabled(counter.value <= 0)
                Button(action: {
                    counter.addOne()
                }){
                    Text("+1")
                        .bold()
                        .font(.headline)
                        .accentColor(.white)
                        .padding(10)
                        .background(Color.blue)
                }.disabled(counter.value >= 10)
            })
            Slider(value: $counter.value,
                   in: 0...10,
                   step: 1){
                Text("")
            }.padding(10)
            ResetViewRef(counter: counter)
        })
    }
}

struct ContentViewRef_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
