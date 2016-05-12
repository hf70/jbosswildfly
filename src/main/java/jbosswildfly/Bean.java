package jbosswildfly;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Model
public class Bean {
	private String word;
	private String anagram;

	public void generateAnagram() {
		Random rand = new Random();
		List<Character> characters = new ArrayList<Character>();
		for (char c : word.toCharArray()) {
			characters.add(c);
		}
		StringBuilder output = new StringBuilder(word.length());
		while (characters.size() != 0) {
			int randPicker = (int) (Math.random() * characters.size());
			output.append(characters.remove(randPicker));
		}
		this.anagram = output.toString();
	}

	public String getAnagram() {
		return anagram;
	}

	public void setAnagram(String anagram) {
		this.anagram = anagram;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public void logout() throws ServletException, IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		//context.getExternalContext().invalidateSession();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		
		request.logout();
		request.login("walter", "huber");
		
		context.getExternalContext().redirect("/jbosswildfly/protected/anagram.xhtml");
	}

}