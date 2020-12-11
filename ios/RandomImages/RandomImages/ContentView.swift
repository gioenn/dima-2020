//
//  ContentView.swift
//  RandomImages
//
//  Created by Giovanni Quattrocchi on 03/12/20.
//

import SwiftUI
import URLImage

struct ContentView: View {
    
    @State private var lastId = Int.random(in: 0...1000)
    @State private var history: [ImageData] = []
    
    var body: some View {
        let url = URL(string: "https://picsum.photos/id/\(lastId)/200")!
        NavigationView {
            VStack(content: {
                URLImage(url: url,
                         failure: {_,_ in
                            Text("Missing...")
                         },
                         content: { image in
                            image
                                .resizable()
                                .aspectRatio(contentMode: .fit)}
                )
                Button(action: {
                    history.append(ImageData(url: url))
                    lastId = Int.random(in: 0...1000)
                    
                } ){
                    Text("NEXT").bold()
                        .font(.callout).accentColor(Color.white)
                        .padding(10)
                        .background(Color.blue)
                }
                NavigationLink(destination: HistoryView(history: $history)) {
                    Text("HISTORY").bold()
                        .font(.callout).accentColor(Color.white)
                        .padding(10)
                        .background(Color.gray)
                }
            }
            ).navigationBarTitle("Random Images")
        }
    }
}

struct HistoryView : View {
    
    @Binding var history: [ImageData]
    
    var body: some View {
        List(history) { image in
            URLImage(url: image.url,
                     failure: {_,_ in
                        Text("Missing...")
                     },
                     content: { image in
                        image
                            .resizable()
                            .aspectRatio(contentMode: .fit)}
            )
        }.navigationBarTitle("History")
        .navigationBarItems(trailing:
                Button(action: {
                    history.removeAll()
                }) {
                    Image(systemName: "trash").imageScale(.large)
                }
        )
    }
}



struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

