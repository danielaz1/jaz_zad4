package filmweb.service;

import filmweb.domain.Actor;
import filmweb.domain.Movie;
import filmweb.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActorService {

	@Autowired
	ActorRepository actorRepository;

	@Autowired
	MovieService movieService;

	public List<Actor> getAll() {
		return actorRepository.getAll();
	}

	public void add(Actor actor) {
		actor.setMovies(new ArrayList<>());
		actorRepository.add(actor);
	}

	public Actor findOne(int id) {
		return actorRepository.findOne(id);
	}

	public void addMovie(Actor actor, int movieId) {
		Movie movie = movieService.findOne(movieId);
		actor.getMovies().add(movie);
		movieService.addActor(movie, actor);
	}
}
