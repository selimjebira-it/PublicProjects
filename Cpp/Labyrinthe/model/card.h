#ifndef CARD_H
#define CARD_H
#include <iostream>
#include <string>
#include <vector>

using namespace std;

/**
  Classe reprenant la cartes "trésors du jeu labyrinthe"
*/
class Card
{

public:
    const string aucun = "";
    const string fantome = "fantome";
    const string gnome = "gnome";
    const string dragon = "dragon";
    const string fee = "fee";
    const string chauveSouris  = "chauveSouris";
    const string genie = "genie";
    const string hibou = "hibou";
    const string scarabee = "scarabee";
    const string rat = "rat";
    const string araignee = "araignee";
    const string papillon = "papillon";
    const string lezard = "lezard";
    const string grimoire = "grimoire";
    const string bourseDor = "bourseDor";
    const string carte = "carte";
    const string couronne = "couronne";
    const string clefs = "clefs";
    const string ossements = "ossement";
    const string bague = "bague";
    const string coffreAuTresor = "coffreAuTresor";
    const string emeraude = "emeraude";
    const string epee = "epee";
    const string chandelier = "chandelier";
    const string heaume = "heaume";
private:

    /**< Liste reprenant l'ensemble des cartes */
     std::vector <string> values_;



public:
/** Constructeur de carte*/
     inline Card(){
             values_.push_back(fantome);
             values_.push_back(gnome);
             values_.push_back(dragon);
             values_.push_back(fee);
             values_.push_back(chauveSouris);
             values_.push_back(genie);
             values_.push_back(hibou);
             values_.push_back(scarabee);
             values_.push_back(rat);
             values_.push_back(araignee);
             values_.push_back(papillon);
             values_.push_back(lezard);
             values_.push_back(grimoire);
             values_.push_back(bourseDor);
             values_.push_back(carte);
             values_.push_back(couronne);
             values_.push_back(clefs);
             values_.push_back(ossements);
             values_.push_back(bague);
             values_.push_back(coffreAuTresor);
             values_.push_back(emeraude);
             values_.push_back(epee);
             values_.push_back(chandelier);
             values_.push_back(heaume);
     }

 /** retourne la liste de l'ensemble des cartes
  * \return la liste de l'ensemble des cartes*/
    inline std::vector<string>  values()const{
        return values_;
    }

};



#endif // CARD_H
