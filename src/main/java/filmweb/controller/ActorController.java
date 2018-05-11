package filmweb.controller;

import filmweb.domain.Actor;
import filmweb.service.ActorService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {

	@Autowired
	ActorService actorService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity getAll() {
		List<Actor> actors = actorService.getAll();
		if (actors.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(actors, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody Actor actor) {
		actorService.add(actor);
		return new ResponseEntity<>(actor, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getActor(@PathVariable("id") int id) {
		Actor actor = actorService.findOne(id);
		if (actor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(actor, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/movies", method = RequestMethod.GET)
	public ResponseEntity<?> getActorMovies(@PathVariable("id") int id) {
		Actor actor = actorService.findOne(id);
		if (actor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(actor.getMovies(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/movies", method = RequestMethod.POST)
	public ResponseEntity<?> addActorMovie(@PathVariable("id") int id, @RequestBody String json) {
		Actor actor = actorService.findOne(id);
		if (actor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		JSONObject jsonObject = new JSONObject(json);
		actorService.addMovie(actor, jsonObject.getInt("movieId"));
		return new ResponseEntity<>(actor.getMovies(), HttpStatus.OK);
	}

}
