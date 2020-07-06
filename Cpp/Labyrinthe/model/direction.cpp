

#include "direction.h"



string Direction::opposite(string const & direction) const{
    if(direction.compare(Up_)== 0){
        return Down_;
    }else if(direction.compare(Down_)== 0){
        return Up_;
    }else if(direction.compare(Right_)== 0){
        return Left_;
    }else if(direction.compare(Left_)== 0){
        return Right_;
    }else{
        throw std::invalid_argument("Bad direction request : opposite");
    }
}

string Direction::clock(string const & direction) const{
    if(direction.compare(Up_)== 0){
        return Right_;
    }else if(direction.compare(Down_)== 0){
        return Left_;
    }else if(direction.compare(Right_)== 0){
        return Down_;
    }else if(direction.compare(Left_)== 0){
        return Up_;
    }else{
        throw std::invalid_argument("Bad direction request : clock");
    }
}




