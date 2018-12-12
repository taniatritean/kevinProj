package com.kevin.web;

import com.kevin.domain.GameDefinition;
import com.kevin.dto.GameDefinitionDTO;
import com.kevin.service.GameDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ttritean
 * @since 12/11/2018
 */

@RestController
public class GameDefinitionController {


    @Autowired
    private GameDefinitionService gameDefinitionService;


    @RequestMapping(path = "/gamedefinition/{id}", method = RequestMethod.GET)
    public GameDefinitionDTO getGameDefinition(@PathVariable("id") long id) {
        return gameDefinitionService.getGameDefinitionById(id);
    }

    @RequestMapping(path="/gamedefinition", method = RequestMethod.POST)
    public void saveGameDefinition(@RequestBody GameDefinitionDTO gameDefintion) {

        gameDefinitionService.saveGameDefinition(gameDefintion);
    }


    @RequestMapping(path="/gamedefinition/{id}", method = RequestMethod.PUT)
    public GameDefinitionDTO updateGameDefinition(@PathVariable long id, @RequestBody GameDefinitionDTO dto) {
        return gameDefinitionService.updateGameDefinition(id, dto);
    }
}
