import React, { useState } from 'react';
import {Text, SafeAreaView , StyleSheet, View} from 'react-native';
import {Factorial} from './Factorial';
import Power from './Power'

export default App = () => {
  let [result, setResult] = useState(0);
  let [onResultChangeList, setOnResultChangeList] = useState([]);

  let addOnResultChange = (f) => {
    setOnResultChangeList((prev) => [...prev, f]);
  }

  let changeResult = (n) => {
    setResult(n);
    onResultChangeList.forEach(f => f());
  }

  return (
  <SafeAreaView style={styles.container}>    
    <Text style={styles.result}>{result}</Text>
    <View style={styles.container}>
      <Factorial updateResult={changeResult} onResultChange={addOnResultChange}/>
      <Power updateResult={changeResult} onResultChange={addOnResultChange}/>
    </View>
  </SafeAreaView>);
}


const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
  },
  result: {
    color: 'red',
    fontSize: 50
  }
})