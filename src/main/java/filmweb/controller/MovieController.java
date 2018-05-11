package filmweb.controller;

import filmweb.domain.Comment;
import filmweb.domain.Movie;
import filmweb.service.MovieService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	MovieService movieService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity getAll() {
		List<Movie> movies = movieService.getAll();
		if (movies.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody Movie movie) {
		movieService.add(movie);
		return new ResponseEntity<>(movie, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getMovie(@PathVariable("id") int id) {
		Movie movie = movieService.findOne(id);
		if (movie == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(movie, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/actors", method = RequestMethod.GET)
	public ResponseEntity<?> getMovieActors(@PathVariable("id") int id) {
		Movie movie = movieService.findOne(id);
		if (movie == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(movie.getActors(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/comments", method = RequestMethod.GET)
	public ResponseEntity<?> getMovieComments(@PathVariable("id") int id) {
		Movie movie = movieService.findOne(id);
		if (movie == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(movie.getComments(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/comments", method = RequestMethod.POST)
	public ResponseEntity<?> addComment(@PathVariable("id") int id, @RequestBody Comment comment) {
		Movie movie = movieService.findOne(id);
		if (movie == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		movieService.addComment(comment, movie);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/comments/{commentId}", method = RequestMethod.GET)
	public ResponseEntity<?> getMovieComment(@PathVariable("id") int id, @PathVariable("commentId") int commentId) {
		Comment comment = movieService.getMovieComment(id, commentId);
		if (comment == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(comment, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/comments/{commentId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteMovieComment(@PathVariable("id") int id, @PathVariable("commentId") int commentId) {
		Comment comment = movieService.getMovieComment(id, commentId);
		if (comment == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		movieService.deleteComment(commentId, id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		Movie movie = movieService.findOne(id);
		if (movie == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		movieService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Movie movie) {
		Movie m = movieService.findOne(id);
		if (m == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		m.setDescription(movie.getDescription());
		m.setTitle(movie.getTitle());

		return new ResponseEntity<>(m, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/rating", method = RequestMethod.POST)
	public ResponseEntity<?> addRating(@PathVariable("id") int id, @RequestBody String json) {
		Movie movie = movieService.findOne(id);
		if (movie == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		JSONObject jsonObject = new JSONObject(json);
		movieService.addRating(movie, jsonObject.getInt("rating"));

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
