package controllers;

import java.util.LinkedList;

import objects.ParagraphText;
import objects.StoryName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import domain.Paragraph;
import domain.Story;


@Controller
public class MainController {
	
	@Autowired(required=true)
	StoryRepository stories;
	
	@Autowired(required=true)
	ParagraphRepository pars;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(Model m) {
		m.addAttribute("story", new StoryName());
		return "home";
	}
	
	@RequestMapping(value="/addStory", method=RequestMethod.GET)
	public String newStory(Model m) {
		m.addAttribute("story", new StoryName());
		return "addStory";
	}
	
	@RequestMapping(value="/addStory", method=RequestMethod.POST)
	public RedirectView addStory(@ModelAttribute StoryName story) {
		Story s = new Story(story.getName());
		stories.save(s);
		System.out.println(s);
		Paragraph p = new Paragraph(story.getFirst());
		p.setText(story.getFirst());
		pars.save(p);
		System.out.println(p);
		p.setStory(s);
		s.setStart(p);
		stories.save(s);
		pars.save(p);
		
		
		RedirectView rv = new RedirectView("/viewStory/"+s.getId());
		return rv;
	}
	
	@RequestMapping(value="/appendParagraph/{storyId}/{lastPar}", method=RequestMethod.GET)
	public String appendParagraph(@PathVariable String storyId, @PathVariable String lastPar, Model m) {
		m.addAttribute("storyId", storyId);
		m.addAttribute("lastPar", lastPar);
		m.addAttribute("par", new ParagraphText());
		return "addParagraph";
	}
	
	@RequestMapping(value="/appendParagraph/{storyId}/{lastPar}", method=RequestMethod.POST)
	public RedirectView appendNewParagraph(@ModelAttribute ParagraphText par, @PathVariable String storyId, @PathVariable String lastPar) {
		Paragraph p = new Paragraph(par.getText());
		p.setText(par.getText());
		Paragraph last = pars.findById(lastPar);
		Story s = stories.findById(storyId);
		if(s.getStart() == null) {
			s.setStart(p);
			stories.save(s);
		}
		p.setStory(s);
		pars.save(p);
		last.setNext(p);
		pars.save(last);
		RedirectView rv = new RedirectView("/viewStory/"+storyId);
		return rv;
	}	
	
	@RequestMapping(value="/removeParagraph/{storyId}/{parId}")
	public RedirectView removeParagraph(@PathVariable String storyId, @PathVariable String parId) {
		Story s = stories.findById(storyId);
		Paragraph p = pars.findById(parId);
		if(p.getId().equals(s.getStart().getId())) {
			s.setStart(null);
			stories.save(s);
		}
		p.setStory(null);
		p.setText(null);
		pars.save(p);
		pars.delete(parId);
		while(p.getNext() != null) {
			Paragraph temp = p.getNext();
			p = temp;
			pars.delete(p.getId());
			
		}
		RedirectView rv = new RedirectView("/viewStory/"+storyId);
		return rv;
	}
	
	@RequestMapping(value="/viewAllStories")
	public String viewAllStories(Model m) {
		m.addAttribute("stories", stories.findAll());
		return "stories";
	}
	
	@RequestMapping(value="/viewStory/{storyId}")
	public String viewAllStories(@PathVariable String storyId, Model m) {
		Story s = stories.findById(storyId);
		LinkedList<Paragraph> pars = new LinkedList<Paragraph>();
		Paragraph p = s.getStart();
		while(p != null) {
			pars.add(p);
			p = p.getNext();
		}
		m.addAttribute("story", s);
		m.addAttribute("pars", pars);
		m.addAttribute("last", pars.getLast());
		return "story";
	}
	
}
