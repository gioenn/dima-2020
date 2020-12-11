//
//  ResetView.swift
//  Counter
//
//  Created by Giovanni Quattrocchi on 11/12/20.
//

import SwiftUI

struct ResetViewRef: View {
    
    @ObservedObject var counter: Model
    
    var body: some View {
        Button(action: { counter.value = 0 }, label: {
            Text("RESET").bold().font(.callout).accentColor(.white).padding(10).background(Color.red)
        }).disabled(counter.value == 0)
    }
}



struct ResetViewRef_Previews: PreviewProvider {
    @StateObject static var counter = Model()
    static var previews: some View {
        ResetViewRef(counter: counter)
    }
}
 
