#include "generatepwd.h"
#include "random.hpp"
#include <sstream>

using namespace std;

static string alphabet = "abcdefghijklmnopqrstuvwxyz";
static string ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
static string number = "0123456789";

string generate_password(int const & lenght){


    string password ("");
    nvs::randomize();

    int  noStr, noChar;

    for(auto i = 0; i < lenght ; i ++){
        noStr = nvs::random_value(0,2);
        noChar = nvs::random_value(0,25);
        if(i == 0){
            noStr = noStr%2;
        }
        switch (noStr) {

        case 0:
            password.push_back(alphabet[static_cast<size_t>(noChar)]);
            break;
        case 1:
            password.push_back(ALPHABET[static_cast<size_t>(noChar)]);
            break;
        case 2:
            password.push_back(number[static_cast<size_t>(noChar%10)]);
            break;
        default:
            break;
        }


    }return password;



//    auto count = 0;

//    stringstream password("");
//    while(count < lenght){

//        noStr = nvs::random_value(0,2);
//        noChar = nvs::random_value(0,25);
//        if(count==0){
//            noStr = noStr%2;
//        }

//        switch (noStr) {

//        case 0:
//            password << alphabet[static_cast<size_t>(noChar)];
//            count++;
//            break;
//        case 1:
//            password << ALPHABET[static_cast<size_t>(noChar)];
//            count++;
//            break;
//        case 2:
//            noChar = noChar%10;
//            password << number[static_cast<size_t>(noChar)];
//            count++;
//            break;
//        default:
//            break;
//        }
//    }


}


