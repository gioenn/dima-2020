//
//  ResetView.swift
//  Counter
//
//  Created by Giovanni Quattrocchi on 11/12/20.
//

import SwiftUI

struct ResetView: View {
    
    @Binding var counter: Float
    
    var body: some View {
        Button(action: { counter = 0 }, label: {
            Text("RESET").bold().font(.callout).accentColor(.white).padding(10).background(Color.red)
        }).disabled(counter == 0)
    }
}



struct ResetView_Previews: PreviewProvider {
    @State static var counter: Float = 10
    static var previews: some View {
        ResetView(counter: $counter)
    }
}
 
