//functioneazaRunnedGamme
package com.kevin.domain;

import javax.persistence.*;

@Entity
@Table(name = "runnedgames")
public class RunnedGame {
    @Id
    @Column(name="id")
    @GeneratedValue(generator = "runnedGame_generator")
    @SequenceGenerator(
            name = "runnedGame_generator",
            sequenceName = "runnedGame_sequence",
            initialValue = 1
    )
    private long ID;

    @Column(name="level")
    private int level;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="game_Definition_Id")
    private GameDefinition gameDefinition;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="user_Id")
    private User user;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public RunnedGame() {
        this.level=20;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private int numberOfCorrectAnswers =0;

    private int totalNumberOfResults=1;

    public double medium(){
        if(totalNumberOfResults==0){
            return -1;
        }
        return numberOfCorrectAnswers / totalNumberOfResults;
        //return 3;
    }
    public String stringMedium(){
        if(totalNumberOfResults==0){
            return "No medium to show.";
        }
        return numberOfCorrectAnswers +"/"+ totalNumberOfResults;
    }
}

