#ifndef BOARD_H
#define BOARD_H
#define M_LABY_SIZE 7
#include <array>
#include <memory>
#include <sstream>
#include <map>
#include <iterator>
#include <algorithm>
#include "tplate.h"
#include "lplate.h"
#include "iplate.h"
#include "util.h"

class Board
{



    /**
     * @brief enumCard_ utiliser pour recuper les noms des cartes
     */
    Card const  enumCard_;



    /**
     * @brief enumDirection_ utliser pour recuperer les noms des directions
     */
    Direction const enumDirection_;
    /**
     * @brief leftOvers
     * @return
     */
    std::vector<Plate*> & leftOvers(std::vector<string> &,std::vector<Plate*> &);
    /**
     * @brief board_ tableau (fait de std::array)  2D , il contient les différents
     *  types de plates (liées par des pointeurs uniques) présentes sur le plateau.
     */

    std::array<std::array<std::unique_ptr<Plate>,M_LABY_SIZE>,M_LABY_SIZE> board_;

    /**
     * @brief currentPlate_ plate libre, utilisée pour les insetion dans le plateau
     */
    std::unique_ptr<Plate> currentPlate_;

    /**
     * @brief setPtr permet de définir un emplacement du board_
     */
    void setPtr(Position const & , Plate*);


    bool isAcceptable(Position const & pos, std::vector<Position>& path)const;

    bool recherchePath(Position const & end, std::vector<Position>& path)const;

    inline bool isInBoardLimit(Position const & pos)const{return (pos.x() < M_LABY_SIZE && pos.x() >= 0 && pos.y() < M_LABY_SIZE && pos.y() >= 0);}


    void initBoard(Position []);





public:

    /**
     * @brief Board notre classe tableau
     */
    Board();
    Board(Board const &) = delete;
    Board(Board const &&) = delete;
    ~Board() = default;

    /**
     * @brief findPath method pour savoir si un pion est déplaçable
     * @param start position de départ du pion
     * @param end position finale du pion
     * @return retourne une liste de position si un chemin est trouvé
     */
    std::vector<Position> findPath(Position const & start, Position const & end)const;

    void reset();


    /**
     * @brief getPlate getter de plate
     * @param pos position de la plate
     * @return plate
     */
    inline Plate const & getPlate(Position const & pos)const{return *board_[size_t(pos.x())][size_t(pos.y())].get();}

    /**
     * @brief insert methode pour inserer la current plate a un certain endroit du tableau
     * @return resultat de la tentative d'insertion
     */
    bool insert(Position);

    /**
     * @brief cardsOnBoard methode pour récupèrer l'ensemble des cartes sur le board
     */
    void cardsOnBoard(std::map<Position,std::string> &) const;

    /**
     * @brief findCardOnBoard methode pour trouver une carte specifique sur le board
     * @param card le nom de la carte a trouver
     * @return la position de la carte trouvée -1 -1 si non  trouvée
     */
    Position findCardOnBoard(string card) const;

    /**
     * @brief currentPlate getter de la current plate
     * @return current plate
     */
    inline Plate const & currentPlate() const{return *currentPlate_.get();}


    std::string toString()const;
    std::wstring toWString()const;

    inline const std::array<std::array<std::unique_ptr<Plate>,M_LABY_SIZE>,M_LABY_SIZE>& getBoard(){
        return board_;
    }


    /**
     * @brief rotate methode pour faire tourner la current plate
     */
    void rotate(unsigned=1);

    Position getPositionToMove(Position const &)const;

private:
    void init();
};

#endif // BOARD_H
