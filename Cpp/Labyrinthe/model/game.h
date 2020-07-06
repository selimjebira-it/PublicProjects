#ifndef GAME_H
#define GAME_H
#include <vector>
#include "model/board.h"
#include "model/player.h"
#include "observer/observer.h"
#include "observer/subject.h"


/**
 * @brief The Game class repésente la logique du jeu et fait interagir les différents éléments
 * du jeu ensemble.
 */
class Game:public nvs::Subject
{
public:

    /**
     * @brief The GameStatus enum renseigne sur l'état du jeu en général (en cours, fini, ...)
     */
    enum class GameStatus{
        notOpen,running,finished

    };

    /**
     * @brief The TurnStatus enum renseigne sur l'état d'un tour de jeu en particulier
     * (pièce déplacée, piont bougé, rien de fait, carte découverte)
     */
    enum class TurnStatus{
        PieceMoved,PawnMoved,NothingMoved,CardDiscovered
    };



private:

    Board board_;

    Card const enumCard_;

    Color const enumColor_;

    vector<unique_ptr<Player>> players_;

    GameStatus gameStatus_;

    TurnStatus turnStatus_;

    int indiceCurrentPlayer;

    vector<std::string> names_;

    void setCurrentPlayer();

    bool isOnObjectif();

    void PawnsAfterInsert(Position const &);





public:

    /**
     * @brief Game constructeur de game
     */
    inline Game():gameStatus_{GameStatus::notOpen}, turnStatus_{TurnStatus::NothingMoved},indiceCurrentPlayer{0}{}

    /**
     * @brief init permet d'initialiser tout les composants du jeu,
     * on peut choisir le nombre max de cartes par joueurs, ceci n'est qu'un maximum, les
     * joueurs peuvent donc avoir moins de cartes s'il il n'y en a pas assez pour
     * que tous aient le max, avant d'init, il faut au minimum avoir ajouté 2 joueurs.
     * @param max nombres max de carte par joueur
     */
    void init(int max = 12);

    /**
     * @brief addPlayer permet d'ajouter un joueur au jeu,
     * le jeu ne doit pas encore avoir commencé et on ne peut pas ajouter plus de
     * 4 joueurs.
     * @param name nom du joueur
     */
    void addPlayer(std::string name);

    /**
     * @brief movePawn permet de déplacer un pion su le plateau de jeu, il faut que le
     * joueur détenteur du pion aie préalablement insérer une plate dans le plateau,
     * le pion ne peut se déplacer que sur un sans coupure (les plates doivent être connectées
     * entre elles, il ne doit pas y avoir de mur qui barre la route). Si le piont à bien pu se
     * déplacer, vérifie si le joueur à trouvé un trésore qu'il recherchait ou s'il a fini la
     * partie enretournant sur sa base. Disponible une fois par tour.
     * @return
     * vrai si le pion à pu être bougé.
     */
    bool movePawn(Position const &);

    /**
     * @brief insertPlate permet d'insérer une pièce dans le plateau de jeu, il est possible
     * d'inserer une pièce (plate) sur les cases extérieurs du board et avec une des coordonées
     * impaire.
     * Disponible une fois par tour.
     * @return vrai si la pièce est insérée, faux autrement
     */
    bool insertPlate(Position const  &);

    /**
     * @brief skip permet de passer son tour,
     * n'est possible que si le joueur à inséré une plate, disponible une fois par tour.
     * @return vrai si le tour à été passé, faux autrement
     */
    bool skip();

    /**
     * @brief rotate permet de rotate la plate "libre" d'un quart de tour dans le sens horloger
     * @return vrai si la plate à bien été rotate.
     */
    bool rotate(unsigned = 1);


    /**
     * @brief end ferme le jeu
     */
    void end();

    void reset();

    //CONST

    /**
     * @brief getGameState retourne le satut global du jeu
     * @return retourne le satut global du jeu
     */
    inline GameStatus getGameState()const{return gameStatus_;}

    /**
     * @brief getTurnstate retourne le satut du tour de jeu
     * @return retourne le satut du tour de jeu
     */
    inline TurnStatus getTurnstate()const{return turnStatus_;}

    /**
     * @brief getCurrentPlayer retourne le joueur courant.
     * @return retourne le joueur courant.
     */
    inline Player const & getCurrentPlayer() const {return *players_.at(size_t(indiceCurrentPlayer)).get();}

    /**
     * @brief getCurrentPlate retourne la plate courrante ou "plate libre"
     * @return retourne la plate courrante
     */
    inline Plate const & getCurrentPlate() const { return board_.currentPlate();}

    /**
     * @brief findCardOnBoard permet de trouver la position d'un trésor sur le board.
     * @param nameOftheCard nom de la carte trésor
     * @return la position du trésore sur le board
     */
    inline Position findCardOnBoard(std::string const & nameOftheCard) const { return board_.findCardOnBoard(nameOftheCard);}

    /**
     * @brief getPlayers retourne la liste de tous les joueurs
     * @return retourne la liste de tous les joueurs
     */
    vector<Player> getPlayers() const;

    /**
     * @brief getWinner retourne le joueur gagnant
     * @return retourne le joueur gagnant, nullptr si pas de gagnant
     */
    Player const * getWinner() const;

    /**
     * @brief getPlate retourne la plate présente sur le plateau de jeu à la position donnée.
     * @param pos position sur le plateau de jeu.
     * @return la plate concernée
     */
    inline Plate const & getPlate(Position const & pos) const {return board_.getPlate(pos);}

    /**
     * @brief getBoard retourne le plateau de jeu.
     * @return retourne le plateau de jeu.
     */
    inline const Board & getBoard() const {return board_;}

    /**
     * @brief getCardsOnBoard retourne une map de toutes les trésors et leur position
     * sur le plateau de jeu.
     * @param mapToFill une map de toutes les trésors et leur position
     */
    void getCardsOnBoard (map <Position,string> & mapToFill) const {board_.cardsOnBoard(mapToFill);}


};

#endif // GAME_H
