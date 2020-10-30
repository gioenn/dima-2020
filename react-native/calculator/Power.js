import React, {useState} from 'react';
import {Text, StyleSheet, View, TextInput, Button} from 'react-native';
import {power} from './Calc';

export default function Power(props) {
    let [baseText, setBaseText] = useState('');
    let [expText, setExpText] = useState('');
    return (
    <View style={[styles.container, {flexDirection: 'column'}]}>
      <View style={styles.container}>
          <TextInput
            style={styles.input}
            placeholder="0"
            defaultValue={baseText}
            onChangeText={setBaseText}
          />
          <Text>^</Text>
          <TextInput
            style={styles.input}
            placeholder="0"
            defaultValue={expText}
            onChangeText={setExpText}
          />
      </View>
      <Button 
        title="compute" 
        onPress={() => {
            let n = power(parseInt(baseText), parseInt(expText));
            props.updateResult(n);
            setBaseText('');
            setExpText('');
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
