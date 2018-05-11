package filmweb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Movie {

	private int id;
	private String title;
	private String description;
	private double rating;
	private int ratingAmount;
	private List<Comment> comments;

	@JsonIgnore
	private List<Actor> actors;

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRatingAmount() {
		return ratingAmount;
	}

	public void setRatingAmount(int ratingAmount) {
		this.ratingAmount = ratingAmount;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
}
