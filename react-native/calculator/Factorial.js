import React, {useState, useRef} from 'react';
import {Text, StyleSheet, View, TextInput, Button} from 'react-native';
import {factorial} from './Calc';

export function Factorial(props) {
    let [text, setText] = useState('');
    let textRef = useRef(null);
    return (
    <View style={[styles.container, {flexDirection: 'column'}]}>
      <View style={styles.container}>
          <TextInput
            ref={textRef}
            style={styles.input}
            placeholder="0"
            onChangeText={setText}
          />
          <Text>!</Text>
      </View>
      <Button 
        title="compute" 
        onPress={() => {
            let n = factorial(parseInt(text));
            props.updateResult(n);
            textRef.current.clear();
        }}
      />
      </View>
      );
  }

const styles = StyleSheet.create({
    container: {
      flexDirection: 'row',
      alignItems: 'center',
      justifyContent: 'center',
    },
    input: {
      fontSize: 25,
      marginRight: 0,
      textAlign: 'right'
    }
  })
