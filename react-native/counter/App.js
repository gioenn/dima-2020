import Slider from "@react-native-community/slider";
import React, { useState } from "react";
import {StyleSheet, Text, View, Button, SafeAreaView} from 'react-native';

class App extends React.Component {
  state = {counter: 0};

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.mainText}>{this.state.counter}</Text>
        <Button
          onPress={() => {
            this.setState({counter: this.state.counter + 1});
          }}
          title="+1"
        />
      </View>
    );
  }
}

export default FApp = (props) => {
  let [counter, setCounter] = useState(0);
  console.log(counter);
  return (
    <SafeAreaView style={styles.container}>
      <Text style={styles.mainText}>{counter}</Text>
      <View style={styles.row}>
      <Button
        disabled={counter == 100}
        onPress={() => {
          setCounter(counter+1);
        }}
        title="+1"
      />
       <Button
        disabled={counter == 0}
        onPress={() => {
          setCounter(counter-1);
        }}
        title="-1"
      />
      </View>
      <Slider 
        style={styles.slider}
        minimumValue={0}
        maximumValue={100}
        value={counter}
        onValueChange={(value) => {
          setCounter(Math.floor(value));
        }}
      />


    </SafeAreaView>
  );

}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'stretch',
    justifyContent: 'center',
    margin: 10
  },
  row: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'center',
  },
  mainText: {
    fontSize: 50,
    alignSelf: 'center',
  },
 
});