package filmweb.repository;

import filmweb.domain.Actor;
import filmweb.domain.Comment;
import filmweb.domain.Movie;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

	private static List<Movie> db = new CopyOnWriteArrayList<>();
	private static int currentId = 0;

	public List<Movie> getAll() {
		return db;
	}

	public void add(Movie movie) {
		movie.setId(++ currentId);
		db.add(movie);
	}

	public synchronized Movie findOne(int id) {
		for (Movie movie : db) {
			if (movie.getId() == id) {
				return movie;
			}
		}
		return null;
	}

	public synchronized void delete(int id) {
		for (Movie movie : db) {
			if (movie.getId() == id) {
				db.remove(movie);
				break;
			}
		}
	}

	public synchronized Comment getMovieComment(int id, int commentId) {
		Movie movie = findOne(id);
		if (movie != null) {
			for (Comment comment : movie.getComments()) {
				if (comment.getId() == commentId) {
					return comment;
				}
			}
		}
		return null;
	}

	public void addComment(Comment comment, Movie movie) {
		comment.setId(movie.getComments().size() + 1);
		comment.setDate(LocalDateTime.now());
		movie.getComments().add(comment);
	}

	public synchronized void deleteComment(int commentId, int movieId) {
		Movie movie = findOne(movieId);
		if (movie != null) {
			for (Comment comment : movie.getComments()) {
				if (comment.getId() == commentId) {
					movie.getComments().remove(comment);
					break;
				}
			}
		}
	}

	public void addActor(Movie movie, Actor actor) {
		movie.getActors().add(actor);
	}
}
