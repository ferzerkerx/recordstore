package com.ferzerkerx.controller;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;
import com.ferzerkerx.dao.ArtistDao;
import com.ferzerkerx.model.Artist;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArtistController {

    @Autowired
    private ArtistDao artistDao;

    @RequestMapping({"/","/home"})
    public ModelAndView showHomePage(Map<String, Object> model) {
        Collection<Artist> allArtists = artistDao.findAllArtists();
        return new ModelAndView("home-tile" , "artists", allArtists);
    }

    @RequestMapping(value = {"/artists.json"}, method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
    @ResponseBody
    public Collection<Artist> showAllArtists() {
        return artistDao.findAllArtists();
    }

}
