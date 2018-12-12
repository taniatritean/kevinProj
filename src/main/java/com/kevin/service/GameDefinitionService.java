package com.kevin.service;


import com.kevin.dto.GameDefinitionDTO;
import com.kevin.domain.GameDefinition;
import com.kevin.persistance.GameDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class GameDefinitionService {

    @Autowired
    private GameDefinitionRepository gameDefinitionRepository;


    public void saveGameDefinition(GameDefinitionDTO gameDefinition){
        if(gameDefinition.getName()==null){
            throw new IllegalArgumentException("Name can not be null.");
        }
        //if(gameDefinition.getID()==0){
            //throw new IllegalArgumentException("ID can not be 0.");
        //}

        GameDefinition gameDefObject = convert(gameDefinition);
        try {
            gameDefinitionRepository.save(gameDefObject);
        }catch (Exception e){
            System.out.println("Error in saving user "+e);
        }
    }

    @Transactional
    public List<GameDefinitionDTO> getGameDefinitions() {
        Iterator<GameDefinition> iterator =
                gameDefinitionRepository.findAll().iterator();


        List<GameDefinitionDTO> list = new ArrayList<>();

        while (iterator.hasNext()) {
            GameDefinition gameDefinition = iterator.next();

            GameDefinitionDTO gameDefinitionDTO = convertToDto(gameDefinition);
            list.add(gameDefinitionDTO);
        }

        return list;

    }

    private GameDefinitionDTO convertToDto(GameDefinition gameDefinition) {
        GameDefinitionDTO gameDefinitionDTO = new GameDefinitionDTO();
        gameDefinitionDTO.setName(gameDefinition.getName());
        gameDefinitionDTO.setID(gameDefinition.getID());
        gameDefinitionDTO.setDescription(gameDefinition.getDescription());

        return gameDefinitionDTO;
    }

    private GameDefinition convert(GameDefinitionDTO gameDefinitionDTO) {
        GameDefinition gameDefinition = new GameDefinition();
        gameDefinition.setName(gameDefinitionDTO.getName());
        gameDefinition.setID(gameDefinitionDTO.getID());
        gameDefinition.setDescription(gameDefinitionDTO.getDescription());

        return gameDefinition;
    }

    public GameDefinitionDTO getGameDefinitionById(long id) {

        GameDefinition gameDefinition = gameDefinitionRepository.findOne(id);
        if(gameDefinition== null) {
            throw new IllegalArgumentException("The id is not valid.");
        }
        return convertToDto(gameDefinition);
    }

    public GameDefinitionDTO updateGameDefinition(long id,GameDefinitionDTO dto) {

        GameDefinition gameDefinition = gameDefinitionRepository.findOne(id);
        if(dto.getDescription()!=null) {
            gameDefinition.setDescription(dto.getDescription());
        }
        gameDefinition.setName(dto.getName());

        GameDefinition savedObject = gameDefinitionRepository.save(gameDefinition);

        return convertToDto(savedObject);
    }
}
