package at.hf.stopwatch.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import at.hf.stopwatch.cdi.Service;
import at.hf.stopwatch.model.Author;

@Service
public class AuthorService implements Serializable {
	@Inject
	MongoClient mongoClient;
	List<Author> listAuthors;

	public List<Author> query() {
		List<Author> list = new ArrayList<Author>();
		DB db = mongoClient.getDB("Blog-test");
		DBCollection dbCollection = db.getCollection("Author");

		JacksonDBCollection<Author, String> coll = JacksonDBCollection.wrap(dbCollection, Author.class, String.class);
		DBCursor<Author> cursor = coll.find();
		while (cursor.hasNext()) {
			list.add(cursor.next());
		}
		return list;
	}
}