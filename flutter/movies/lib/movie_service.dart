import 'dart:convert';
import 'package:http/http.dart' as http;

final apiKey = 'b8c79639';
final endpoint = 'http://www.omdbapi.com/';

searchMovies(String text) async {
  if (text == "") return [];

  var response = await http.get(endpoint + "?apikey=" + apiKey + "&s=" + text);
  var responseJson = json.decode(response.body);
  if (responseJson['Search'] != null) {
    return responseJson['Search']
        .where((movie) => movie['Poster'] != "N/A")
        .toList();
  }

  return [];
}

findMovie(String id) async {
  if (id == "") {
    return [];
  }
  var response = await http.get(endpoint + "?apikey=" + apiKey + "&i=" + id);
  return json.decode(response.body);
}
