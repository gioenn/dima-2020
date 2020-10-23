import React, {Component} from 'react';
import { StyleSheet, Text, View, SafeAreaView } from 'react-native';

function range(b, e) {
  let res = []
  for (; b <= e; b++){
    res.push(b);
  }
  return res;
}

 


class HelloWorld extends Component {
  render() {
    return (
      <Text style={styles.text}> Hello World! </Text>
    );
  }
}

HelloWorldF = () => {
  return <Text style={styles.text}> Hello World! </Text>
}

RepeatedHelloWorldF = (props) => {
  let color = props.color ? {color: props.color} : styles.red;
  return <Text style={[styles.text, color]}> Hello World {props.count} {props.count  == 1 ? "time" : "times"}!</Text>
}

class RepeatedHelloWorld extends React.Component {

  render () {
    let color = this.props.color ? {color: this.props.color} : styles.red;
    return <Text style={[styles.text, color]}> Hello World {this.props.count} {this.props.count  == 1 ? "time" : "times"}!</Text>
  }

}

HelloWorldGeneratorF = (props) => {
  let data = [];
  for (let i=1; i <= props.count; i++){
    data.push(<RepeatedHelloWorldF color={ `rgb(${16*i % 255}, ${32*i % 255}, ${64*i % 255})` } key={i.toString()} count={i}/>)
  }
  return (
    <View style={ styles.generator }>
      { data }
    </View>
  );
}

class HelloWorldGenerator extends React.Component {
  render() {
    let data = [];
    for (let i=1; i <= props.count; i++){
      data.push(<RepeatedHelloWorldF color={ `rgb(${16*i % 255}, ${32*i % 255}, ${64*i % 255})` } key={i.toString()} count={i}/>)
    }
    return (
      <View style={ styles.generator }>
        {data}
      </View>
    );
  }
}

export default class App extends React.Component {
  render() {
    return (
      <SafeAreaView style={styles.container}>
        <HelloWorldF />
        <HelloWorldGeneratorF count={10} />
        <RepeatedHelloWorldF count={11} />
      </SafeAreaView>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'white',
    justifyContent: 'center', // primary axis
    alignItems: 'center' // secondary axis
  },
  text: {
    fontWeight: "500",
    fontSize: 20
  },
  red: {
    color: "red"
  },
  generator: {
    alignItems: 'center',
  }
});
