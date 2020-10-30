import React, { useState } from 'react';
import {Text, SafeAreaView , StyleSheet, View} from 'react-native';
import {Factorial} from './Factorial';
import Power from './Power'

export default App = () => {
  let [result, setResult] = useState(0);
  return (
  <SafeAreaView style={styles.container}>    
    <Text style={styles.result}>{result}</Text>
    <View style={styles.container}>
      <Factorial updateResult={setResult}/>
      <Power updateResult={setResult}/>
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