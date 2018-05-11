package filmweb.repository;

import filmweb.domain.Actor;

import java.util.List;

public interface ActorRepository {

	void add(Actor actor);

	Actor findOne(int id);

	List<Actor> getAll();

}
