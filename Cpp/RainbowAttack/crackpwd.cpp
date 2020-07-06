#include "crackpwd.h"
#include <sstream>
#include <istream>
#include "generatetable.h"
#include "sha256.h"

using namespace std;

void crackPwd::extract(){


    std::ifstream in(filenameOfTable,std::ios::in);
    std::string raw,key,value;


    in.seekg(0);
    if(in){

        while(std::getline(in,raw)){
            key = raw.substr(0,sizeMdp-1);
            value = raw.substr(sizeMdp+1,sizeMdp*2 +1);
            dico[key] = value;
            std::cout << "Key = " << key << " value = " << value << std::endl;
        }

//            std::cout << raw << std::endl;
    }in.close();

}

string crackPwd::findRow(string hash, int taille) {
    string result, queue;
    bool trouver = false;
    int count = 0;
    do{
        result = generateTable::reduce(hash, taille - count);
        for(int i = 1; i <= count; i++){
            result = sha256(result);
            result = generateTable::reduce(result,taille - count + i);
        }

        for (std::unordered_map<string,string>::iterator it = dico.begin(); it != dico.end(); ++it)
        {
            if(it->second == result){
                trouver = true;
                queue = it->second;
            }else{
                count++;
            }
        }
    }while(!trouver || count == taille);
    return queue;
}

string crackPwd::findPswd(string hash, string queue){
    string tempH,tempR;
    for (std::unordered_map<string,string>::iterator it = dico.begin(); it != dico.end(); ++it)
    {
        if(queue == it->second){
            tempR = it->first;
        }
    }
    do{
        tempH = sha256(tempR);
        if(tempH == hash){
            return tempR;
        }else{
            tempR = generateTable::reduce(tempH);
        }
    }while(tempR != queue);
    return "non trouver";
}
