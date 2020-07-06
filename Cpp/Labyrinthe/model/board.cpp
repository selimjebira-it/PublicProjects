#include "board.h"

Board::Board()
{
    init();


}
void Board::init(){

    currentPlate_.reset(new IPlate(Position(-10,-10)));
    Position corners [] {Position(0,0),Position(M_LABY_SIZE-1,0),Position(M_LABY_SIZE-1,M_LABY_SIZE-1),Position(0,M_LABY_SIZE-1)};
    initBoard(corners);

}

void Board::rotate(unsigned i){
    currentPlate_->rotate(i);
}


void Board::initBoard(Position * corners){

    string direction = enumDirection_.Right_;

    vector<string> objectifs = enumCard_.values();

    Position start;

    for(auto i = 0; i < 4 ; i ++){
        start = corners[i];
        for(auto j = 0; j < 4 ; j ++){
            if(j == 0){
                setPtr(start,new LPlate(Position(start.x(),start.y()),"",i,true));
            }else{
                setPtr(start,new TPlate(Position(start.x(),start.y()),objectifs.back(),i,true));
                objectifs.pop_back();
            }if(j==2){
                direction = enumDirection_.clock(direction);
            }
            start = start + enumDirection_.vecteur(direction, 2);
        }
    }


    vector<Plate*> platesLeft;
    platesLeft = leftOvers(objectifs,platesLeft);

    for (int y = 0; y < M_LABY_SIZE; y ++) {
        for (int x = 0; x < M_LABY_SIZE; x ++) {
            if(board_[static_cast<size_t>(x)][static_cast<size_t>(y)] == nullptr){
                Position pos (x,y);
                platesLeft.back()->setPosition(pos);
                setPtr(pos,platesLeft.back());
                platesLeft.pop_back();

            }
        }
    }

}

vector<Plate *> & Board::leftOvers(vector<string> & objectifs, vector<Plate *> & result){


    for(unsigned t = 0; t < 6 ; t ++){
        result.push_back(new TPlate(Position(0,0),objectifs.back()));
        objectifs.pop_back();
    }
    for(unsigned i = 0; i < 11 ; i ++){
        result.push_back(new IPlate(Position(0,0),"",i%2));
    }
    for(unsigned l = 0; l < 16 ; l++){
        result.push_back(new LPlate(Position(0,0),objectifs.empty()? "" : objectifs.back(),l%4));
        if(!objectifs.empty()){
            objectifs.pop_back();
        }
    }

    random_shuffle(result.begin(),result.end()); //Utiliser sans succès
    return  result;

}


void Board::setPtr(Position const & pos, Plate * platePtr){

    board_[size_t(pos.x())][size_t(pos.y())].reset(platePtr);
}


void Board::cardsOnBoard(map<Position,std::string>& cards) const{

    for(auto const &i : this->board_){
        for(auto const &j : i){
            if(j->getCard().compare("") != 0){

                cards.insert(pair<Position,std::string>(Position(j->getPosition()),j->getCard()));

            }
        }
    }

}

bool comparCard(std::pair<Position, std::string>card1, string card2){
    return card1.second.compare(card2)==0;
}
Position Board::findCardOnBoard(string card) const{

    map<Position,std::string> cards;
    cardsOnBoard(cards);
    for(auto & elm : cards){
        if(elm.second.compare(card)==0){
            return elm.first;
        }
    }
//    auto it = std::find(cards.begin(),cards.end(), card );
//    if(it != cards.end()){
//        return it->first;
//    }

//    auto fct = [card](std::pair<Position, std::string> const & str){return card.compare(str.second) == 0;};

//    auto temp = (std::find_if(cards.begin(),cards.end(),fct));
    return /*temp == cards.end() ? Position(-10,-10) : temp->first*/Position(-10,-10);
}


vector<Position> Board::findPath(Position const & start, Position const & end)const{

    vector<Position> path;
    path.push_back(start);
    if(start == end){
        return path;
    }

    if(!isInBoardLimit(end) || !recherchePath(end, path)){
        path.pop_back();
    }
    return path;
}

void Board::reset()
{
    init();
}

bool Board::recherchePath(Position const & end, vector<Position>& path)const{

    for (auto dir : enumDirection_.values()) {
        Position temp(path.back() + enumDirection_.vecteur(dir));
        if(isAcceptable(temp, path)){
            path.push_back(temp);
            if(temp == end){
                return true;
            }else if(recherchePath(end, path)){
                return true;
            }
            path.pop_back();
        }
    }
    return false;
}

Position Board::getPositionToMove(const Position & pos) const
{
    if(pos.x() == 0)
        return  enumDirection_.vecteur(enumDirection_.Right_);
    else if(pos.x() == 6)
        return enumDirection_.vecteur(enumDirection_.Left_);
    else if(pos.y() == 0)
        return enumDirection_.vecteur(enumDirection_.Down_);
    else if(pos.y() == 6)
        return enumDirection_.vecteur(enumDirection_.Up_);
}

bool Board::isAcceptable(Position const & pos, vector<Position>& path)const{

    if(isInBoardLimit(pos)){
        if(!util::contains(path, pos)){
            return getPlate(path.back()).connected(getPlate(pos));
        }
    }
    return false;
}



bool Board::insert(Position  pos){
    if(pos.x() == 0 || pos.x() == 6 || pos.y() == 0 || pos.y() == 6){
        //on sait qu'on est sur une case déplaçable
        if(getPlate(pos).isMovable()){

            Position dirTaken;
            if(pos.x() == 0)
                dirTaken = enumDirection_.vecteur(enumDirection_.Right_);
            else if(pos.x() == 6)
                dirTaken = enumDirection_.vecteur(enumDirection_.Left_);
            else if(pos.y() == 0)
                dirTaken = enumDirection_.vecteur(enumDirection_.Down_);
            else if(pos.y() == 6)
                dirTaken = enumDirection_.vecteur(enumDirection_.Up_);


            for (int i = 0 ; i < M_LABY_SIZE; i ++) {
                this->currentPlate_->setPosition(pos);
                this->board_[size_t(pos.x())][size_t(pos.y())].swap(currentPlate_);
                pos = pos + dirTaken;
            }
            return true;
        }
    }
    return false;
}


std::string Board::toString() const{

    for(auto y = 0; y < 7 ; y++ ){
        for(auto x = 0; x < 7 ; x ++){

            std::cout << getPlate(Position(x,y)).getRotationLvl();

        }std::cout <<endl;

    }

}


















