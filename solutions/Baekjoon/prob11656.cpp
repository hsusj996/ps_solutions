#include <iostream>
using namespace std;

int main(){
    string s;
    cin >> s;

    vector<string> v;
    for(int i=0;i<s.length();i++){
        v.push_back(s.substr(i));
    }

    sort(v.begin(), v.end());

    for(string s : v){
        cout << s << endl;
    }
}