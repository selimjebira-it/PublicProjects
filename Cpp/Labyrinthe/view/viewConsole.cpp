#include "view/viewConsole.h"
#define DOUBLE_LINE  (<<endl<<endl)

ViewConsole::ViewConsole(Controler & controler) : controler_{controler}{}

void ViewConsole::welcome(){
    cout<< "=======================================================" << endl
        << "               Welcome To The LABYRINTH                " << endl
        << "=======================================================" << endl;
}
void ViewConsole::start(){


    welcome();

    addPlayer();
    controler_.start();
}

void ViewConsole::showPlayers(){

    for(auto & i : subject_->getPlayers()){
        cout << i << endl;
    }

}

void ViewConsole::showBoard(){

    if(subject_ != nullptr){

        wstringstream strBoard ;
        strBoard << endl <<endl ;
        strBoard <<"\t" << "  ";
        for(unsigned i = 0; i < M_LABY_SIZE; i ++){
            strBoard << " " << i << " " ;
        }strBoard << endl;

        for(unsigned y = 0; y < M_LABY_SIZE; y++){
            strBoard <<"\t" << y << " " ;
            for(unsigned x = 0; x < M_LABY_SIZE; x++){
                Plate const & p = subject_-> getPlate(Position(int(x),int(y)));
                strBoard << p.shape();
            }
            strBoard << endl <<endl ;
        }strBoard << endl << endl;
//        util::printUTf16(strBoard.str());

    }



}

void ViewConsole::showCurrentPlayer(){

    cout << subject_->getCurrentPlayer() << endl;
}

void ViewConsole::showCards(){ //const

    map <Position,string> cards;
    subject_->getCardsOnBoard(cards);
    for(auto & i : cards){
        cout << " EN : " << i.first << " Objectif : " << i.second<<endl;
    }

}

void ViewConsole::showPawns(){

    if(subject_ != nullptr){
        if(subject_->getGameState() == Game::GameStatus::running){
            for(auto & player : subject_->getPlayers()){
                cout << " Pawn of :" << player.pawn() << " is On : " << player.pawn().position() << endl;

            }
        }
    }

}

void ViewConsole::showCurrentCard(){

    if(subject_ != nullptr){
        if(subject_->getGameState() == Game::GameStatus::running){

            string card = subject_->getCurrentPlayer().getCurrentCard();
            cout << "Your Card : " << card << endl;
            cout << "someone has heard that you could find it around  "
                 << subject_->findCardOnBoard(card) << endl;
        }else{
            cout << "The game is not running ." << endl;
        }
    }

}

void ViewConsole::showWinner(){

    cout << "!!!  VICTORY  !!!" << endl;
    cout << subject_->getCurrentPlayer() << " WON THE GAME ! ";
}

void ViewConsole::showCurrentPlate(){

//    util::printUTf16(subject_->getCurrentPlate().shape());
}

void ViewConsole::showTurn(){

    cout << "It 's : " << subject_->getCurrentPlayer().name() << "'s turn" <<endl;
}

void ViewConsole::askPlayer(){

    cout<< "What do you want to do? (type \"help\" to see differents options)" << endl;
    string input;

    cin.clear();
    std::getline(cin, input);
    vector<string> answers;
    answers.clear();
    answers = util::cutStringInWord(input);
    input.clear();

    if(answers[0].compare("help") == 0){//const

        help();
        askPlayer();


    }else if(answers.at(0).compare("board") == 0){ //const

        showBoard();
        askPlayer();

    }else if(answers.at(0).compare("card") == 0){ //const

        showCurrentCard();
        askPlayer();

    }else if(answers.at(0).compare("plate") == 0){//const

        showCurrentPlate();
        askPlayer();


    }else if(answers.at(0).compare("rotate") == 0){//notify

        controler_.rotate();



    }else if(answers.at(0).compare("insert") == 0){//notify Or const


        try {
            int x = std::stoi( answers[1] );
            int y = std::stoi( answers[2] );
            if(!controler_.insert(x,y)){

                cerr << "Piece can't be insert in " << x << ", "<< y;
                askPlayer();
            }
        } catch (exception) {
            cerr << "The insert command must be followed by two integers." << endl;
            askPlayer();
        }


    }else if(answers.at(0).compare("movePawn") == 0){//notify or Const
        if(answers.size() != 3){
            cerr << "The move command must be followed by two integers." << endl;
            askPlayer();
        }


        try {
            int x = std::stoi( answers[1] );
            int y = std::stoi( answers[2] );

            if(!controler_.move(x,y)){
                cerr << "Pawn can't be move to  " << x << ", "<< y;
                askPlayer();
            }
        }catch (exception) {
            cerr << "The move command must be followed by two integers." << endl;
            askPlayer();
        }
    }else if(answers.at(0).compare("skip") == 0){//notify

        if(!controler_.skip()){
            cerr << "you Can't pass your turn you have to move a piece first !" << endl;
            askPlayer();
        }


    }else if(answers.at(0).compare("info") == 0){//const

        showTurn();
        showCards();
        askPlayer();

    }else if(answers.at(0).compare("players") == 0){ //const

        showPlayers();
        askPlayer();

    }else if(answers.at(0).compare("treasures") == 0){ //const

        showCards();
        askPlayer();

    }else if(answers.at(0).compare("pawns") == 0){ //const
        showPawns();
        askPlayer();

    }else if(answers.at(0).compare("exit") == 0){ //end

        controler_.end();

    }else{

        cout << "Aucune commande Reconnue" << endl;
        askPlayer();
        return;
    }
}

void ViewConsole::help(){

    cout<< "=======================================================" << endl
        << "                         Help                          " << endl
        << "=======================================================" << endl
        << "board------> show the labyrinth" << endl
        << "card-------> show your secret card" << endl
        << "plate------> show the current plate" << endl
        << "rotate-----> rotate the free plate" <<endl
        << "insert \"x\" \"y\"--> instert piece at this coordinates" << endl
        << "movePawn \"x\" \"y\"--> move pawn at this coordinates" << endl
        << "skip------> skip your move" << endl
        << "info------> show the curent player info" << endl
        << "pawns------> show pawn of all players" << endl
        << "treasures---> show position of all trasures" << endl
        << "exit ---------> to Quit the game" << endl
        <<"=======================================================" << endl
       << "                                                       " << endl
       << "=======================================================" << endl;
}

void ViewConsole::update(const nvs::Subject *subject){

    if(typeid(*subject) == typeid (Game)){
        subject_ = dynamic_cast<const Game*>(subject);

    }
    //State Running
    if(subject_->getGameState() == Game::GameStatus::running){
        switch (subject_->getTurnstate()) {
        //state No change
        case Game::TurnStatus::NothingMoved:


            showBoard();

            showCurrentPlate();

            askPlayer();

            break;
            //state Piece Has Moved
        case Game::TurnStatus::PieceMoved:

            showBoard();

            askPlayer();
            break;
            //state Pawn has Moved
        case Game::TurnStatus::PawnMoved:

            showPawns();
            showTurn();
            askPlayer();
            break;

            //state card has been discovered
        case Game::TurnStatus::CardDiscovered:

            showPlayers();
            askPlayer();
            break;


        }

    }else if (subject_->getGameState() == Game::GameStatus::finished) {
        //if(subject_->getTurnstate() == Game::TurnStatus::CardDiscovered){
        showWinner();
        //        }else{
        showEndOFGame();
        //    }

    }


}


void ViewConsole::showEndOFGame(){

    cout << "-------------------------------------------" << endl
         << "--------------Partie Terminée--------------" << endl
         << "-------------------------------------------" << endl;
}


void ViewConsole::addPlayer(){  

    bool continue_ = false;
    string name;
    string answerContinue;
    cout << "you need at least two players (and max 4) to play labyrinth" << endl;
    while(controler_.nbNames() < 2 || continue_){
        continue_= false;
        cout << "please enter a name (between 3 and 8 characters) for player " << controler_.nbNames() + 1<< " : ";
        cin >> name;
        if(!controler_.addPlayer(name)){
            cerr << "Could not add this player Perhaps it already exists,"
                 << endl <<" or does not respect naming conventions." << endl;
        }else{
            cout << "player added" << endl;
            if(controler_.nbNames() >= 2 && controler_.nbNames() < 4){
                cout << "Do you want to add another player?(yes/no) : ";
                cin >> answerContinue;
                if(answerContinue.compare("yes")==0){
                    continue_ = true;
                }
            }
        }
    }
}










