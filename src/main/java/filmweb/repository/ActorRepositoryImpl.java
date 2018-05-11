package filmweb.repository;

import filmweb.domain.Actor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class ActorRepositoryImpl implements ActorRepository {

	private static List<Actor> db = new CopyOnWriteArrayList<>();
	private static int currentId = 0;

	public List<Actor> getAll() {
		return db;
	}

	public void add(Actor actor) {
		actor.setId(++ currentId);
		db.add(actor);
	}

	public synchronized Actor findOne(int id) {
		for (Actor actor : db) {
			if (actor.getId() == id) {
				return actor;
			}
		}
		return null;
	}

}
