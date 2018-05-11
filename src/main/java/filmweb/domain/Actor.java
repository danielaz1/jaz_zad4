package filmweb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Actor {

	private int id;
	private String name;

	@JsonIgnore
	private List<Movie> movies;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
}
