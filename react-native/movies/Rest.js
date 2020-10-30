const apikey = 'b8c79639';
const endpoint = 'http://www.omdbapi.com/'

export default class MovieRest {

    search(text, callback){
        if (text == ""){
            callback([]);
            return;
        }

        fetch(endpoint+"?apikey="+apikey+"&s="+text)
            .then((response) => response.json())
            .then((data) => {
                if (data.Search) {
                    let movies = data.Search.filter((movie) => movie.Poster != 'N/A');
                    callback(movies);
                }
                else callback([]);
            }).catch((error) => {
                console.log(error);
                callback([]);
            })

    }

}