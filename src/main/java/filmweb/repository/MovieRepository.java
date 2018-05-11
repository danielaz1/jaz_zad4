package filmweb.repository;

import filmweb.domain.Actor;
import filmweb.domain.Comment;
import filmweb.domain.Movie;

import java.util.List;

public interface MovieRepository {

	List<Movie> getAll();

	void add(Movie movie);

	Movie findOne(int id);

	void delete(int id);

	Comment getMovieComment(int id, int commentId);

	void addComment(Comment comment, Movie movie);

	void deleteComment(int commentId, int movieId);

	void addActor(Movie movie, Actor actor);

}
