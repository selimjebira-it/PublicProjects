#ifndef PLAYER_H
#define PLAYER_H

#include "pawn.h"
#include "card.h"
#include <string>
#include <vector>

using namespace std;


/**
 * @brief The Player class, joueur du jeu
 */
class Player
{

    /**
     * @brief name_ nom du joueur
     */
    string name_;
    /**
     * @brief deck_ cartes à jouer du joueurs
     */
    vector<string> deck_;
    /**
     * @brief done_ carte déjà jouées du joueur
     */
    vector<string> done_;
    /**
     * @brief pawn_ pion du joueur
     */
    Pawn pawn_;


public:

    /**
     * @brief Player constructeur de player
     * @param name nom du joueur
     * @param deck deck du joueur
     * @param pawn pio du joueur
     */
    Player(string const & name, const vector<string> &deck, Pawn const & pawn);

    inline Pawn const &  pawn()const{

        return pawn_;
    }

    /**
     * @brief Position set la position du pion du joueur
     * @param pos nouvelle position du pion du joueur
     */
    inline void Position(Position const & pos){

        pawn_.position(pos);
    }

    /**
     * @brief name retourne le nom du joueur
     * @return le nom du joueur
     */
    inline string name()const{

        return name_;
    }

    /**
     * @brief getCurrentCard retourne le trésor que le joueur doit trouver
     * @return le trésor que le joueur doit trouver, si plus de trésor, retourne la chaine "Aucune Carte"
     */
    inline string getCurrentCard()const{

        if(!deck_.empty()){
            return deck_.back();
        }else{
            return "Aucune Carte";
        }

    }

    /**
     * @brief operator == l'égalité est établie sur l'égalité des noms des joueurs et l'égalité de leur pion
     * @param other le deuxième joueur de l'égalité
     * @return vrai si égaux, faux sinon
     */
    inline bool operator==(Player const & other){
        bool names = other.name().compare(name_) == 0;
        bool pawns = other.pawn() == pawn_;
        return (pawns && names);
    }

    /**
     * @brief setCurrentCard met à jour le trésor que le joueur doit trouver
     */
    void setCurrentCard();

    /**
     * @brief hasFinished indique si le joueur à fini de joueur de jouer dans le jeu
     * (si il a trouvé tous les trésors de son deck et qu'il est de retour sur sa case de départ)
     * @return si le joueur à fini de jouer dans le jeu
     */
    bool hasFinished() const;



};
std::ostream & operator<<(ostream&, Player const &);

#endif // PLAYER_H
