package filmweb.service;

import filmweb.domain.Actor;
import filmweb.domain.Comment;
import filmweb.domain.Movie;
import filmweb.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

	@Autowired
	MovieRepository movieRepository;

	public List<Movie> getAll() {
		return movieRepository.getAll();
	}

	public void add(Movie movie) {
		movie.setComments(new ArrayList<>());
		movie.setActors(new ArrayList<>());
		movieRepository.add(movie);
	}

	public Movie findOne(int id) {
		return movieRepository.findOne(id);
	}

	public void addComment(Comment comment, Movie movie) {
		movieRepository.addComment(comment, movie);
	}

	public Comment getMovieComment(int id, int commentId) {
		return movieRepository.getMovieComment(id, commentId);
	}

	public void deleteComment(int commentId, int id) {
		movieRepository.deleteComment(commentId, id);
	}

	public void delete(int id) {
		movieRepository.delete(id);
	}

	public void addRating(Movie movie, int rating) {
		double newRating = (movie.getRating() * movie.getRatingAmount() + rating) / (movie.getRatingAmount() + 1);
		movie.setRatingAmount(movie.getRatingAmount() + 1);
		movie.setRating(newRating);
	}

	public void addActor(Movie movie, Actor actor) {
		movieRepository.addActor(movie, actor);
	}
}
