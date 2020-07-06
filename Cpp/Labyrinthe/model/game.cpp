#include "game.h"

void Game::init(int max){


    //    vector <string> cards,color,deck,done;
    //    players_.push_back(std::unique_ptr<Player>());
    //    players_.push_back(std::unique_ptr<Player>());


    //    unique_ptr <Player>  pl (new Player(names_[0],color,Pawn("Red")));
    //    players_.at(0).reset(pl.get());
    //    unique_ptr <Player>  pl2 (new Player(names_[1],color,Pawn("Blue")));
    //    players_.at(1).reset(pl2.get());


    vector<string> cards,colors,deck;
    cards = enumCard_.values();
    random_shuffle(cards.begin(),cards.end());
    colors = enumColor_.values();
    size_t size = cards.size() / names_.size();
    size = size > max ? max : size;
    string name,color;
    Color c;
    Position p;
    Pawn pawn;
    unique_ptr<Player> playerPtr;
    for(size_t cptPlayers = 0 ; cptPlayers < names_.size() ; cptPlayers ++){
        name = names_[cptPlayers];
        color = colors[cptPlayers];
        deck.clear();
        for(size_t cptCards = 0; cptCards < size ; cptCards ++){
            deck.push_back(cards.back());
            cards.pop_back();
        }
        p = c.gamePosition(color);
        pawn = Pawn(color,p);
        playerPtr.reset(new Player(name,deck,pawn));
        players_.push_back(std::move(playerPtr));
    }
    gameStatus_ = GameStatus::running;
    notifyObservers();

}

void Game::addPlayer(std::string name){
    names_.push_back(name);
}



void Game::setCurrentPlayer(){

    if(gameStatus_ == GameStatus::running){
        if(turnStatus_ == TurnStatus::PawnMoved || turnStatus_ == TurnStatus::CardDiscovered){
            indiceCurrentPlayer ++;
            indiceCurrentPlayer %= players_.size();
            isOnObjectif();
            turnStatus_ = TurnStatus::NothingMoved;
            notifyObservers();
        }
    }
}


bool Game::rotate(unsigned rotationLvl){

    if(gameStatus_== GameStatus::running){
        board_.rotate(rotationLvl);
        notifyObservers();
        return true;
    }return false;



}


bool Game::skip(){

    if(turnStatus_ == TurnStatus::PieceMoved){
        turnStatus_ = TurnStatus::PawnMoved;
        setCurrentPlayer();
        return true;
    }else{
        return false;
    }
}

bool Game::movePawn(Position const & pos){

    if(gameStatus_ == GameStatus::running){
        if(turnStatus_ == TurnStatus::PieceMoved)
        {
            if(!board_.findPath(getCurrentPlayer().pawn().position(),pos).empty()){
                players_.at(size_t(indiceCurrentPlayer))->Position(pos);
                turnStatus_ = TurnStatus::PawnMoved;
                isOnObjectif();
                if(gameStatus_ == GameStatus::finished){
                    notifyObservers();
                }else {
                    setCurrentPlayer();
                }
                return true;
            }
        }
    }
    return false;


}

bool Game::insertPlate(Position const & position){


    if(gameStatus_ != GameStatus::running){
        return false;
    }
    if(turnStatus_ == TurnStatus::NothingMoved && board_.insert(position)){
        PawnsAfterInsert(position);

        isOnObjectif();
        turnStatus_ = TurnStatus::PieceMoved;
        notifyObservers();
        return true;
    }
    return false;
}


bool Game::isOnObjectif(){

    if(gameStatus_!= GameStatus::running)
        return false;
    Player currentPlayer = *players_.at(size_t(indiceCurrentPlayer)).get();
    string plateCard = board_.getPlate(currentPlayer.pawn().position()).getCard();
    string playerCard = currentPlayer.getCurrentCard();
    if(playerCard == plateCard){

        players_.at(size_t(indiceCurrentPlayer))->setCurrentCard();
        turnStatus_=TurnStatus::CardDiscovered;

        //setCurrentPlayer();
        notifyObservers();
        return true;
    }
    if(players_.at(size_t(indiceCurrentPlayer))->hasFinished()){
        gameStatus_ = GameStatus::finished;
    }
    return false;
}

void setPawnInLimit(Player & player){
    int x, y;
    x = player.pawn().position().x();
    y = player.pawn().position().y();
    if(x < 0){
        x = M_LABY_SIZE + (x % M_LABY_SIZE);
    }else{
        x %= M_LABY_SIZE;
    }
    if(y < 0){
        y = M_LABY_SIZE + (y % M_LABY_SIZE);
    }else{
        y %= M_LABY_SIZE;
    }
    player.Position(Position(x,y));
}

void Game::PawnsAfterInsert(const Position & pos)
{
    for(auto &player : players_){
        if((pos.x() == 0 || pos.x() == 6) && player->pawn().position().y() == pos.y()){
            player->Position(player->pawn().position() + board_.getPositionToMove(pos));
            setPawnInLimit(*player);

        }else if((pos.y() == 0 || pos.y() == 6) && player->pawn().position().x() == pos.x()){
            player->Position(player->pawn().position() + board_.getPositionToMove(pos));
            setPawnInLimit(*player);
        }
    }
}

void Game::reset()
{
    gameStatus_ = GameStatus::notOpen;
    turnStatus_ = TurnStatus::NothingMoved;
    indiceCurrentPlayer = 0 ;
    board_.reset();
    names_.clear();
    players_.clear();
}



void Game::end(){

    gameStatus_ = GameStatus::finished;
    turnStatus_ = TurnStatus::NothingMoved;
    notifyObservers();
}

Player const * Game::getWinner() const{
    return (players_.at(indiceCurrentPlayer)->hasFinished())? players_.at(indiceCurrentPlayer).get() : nullptr;
}

Player getContent( unique_ptr<Player> const & player){
    return *player.get();
}
vector<Player> Game::getPlayers() const{
    vector<Player> copyPlayers;
    for(auto &i : players_){
        copyPlayers.push_back(*i.get());
    }

    //  std::transform(players_.begin(), players_.end(),copyPlayers, getContent);
    return copyPlayers;
}

