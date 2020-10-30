/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React, { useState } from 'react';
import {
  SafeAreaView,
  StyleSheet,
  TextInput,
} from 'react-native';
import MovieRest from './Rest';
import MovieList from './MovieList';

rest = new MovieRest();

export default Movies = () => {
  [movies, setMovies] = useState([]);
  return (
    <SafeAreaView  style={styles.container}>
      <TextInput
        style={styles.input}
        placeholder="Search"
        onChangeText={(text) => {
          rest.search(text, (movies) => setMovies(movies));
        }}
      />
      <MovieList movies={movies} />
    </SafeAreaView>
  ) 

}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  input: {
    height: 50,
    margin: 5,
    fontSize: 20
  }
})