#include <iostream>
#include <vector>
using namespace std;

void display(vector<string>& arr){
    cout << "[";
    for(int i = 0; i < arr.size(); i++){
        if(i == arr.size() - 1) cout << arr[i];
        else cout << arr[i] << ", ";
    }
    cout << "]";
}

vector<string> getMazePathsHV(int sr, int sc, int dr, int dc){
    if(sr == dr && sc == dc){
        vector<string> base;
        base.push_back("");
        return base;
    }
    vector<string> myAns;
    if(sc + 1 <= dc){
        vector<string> Horizontal = getMazePathsHV(sr, sc + 1, dr, dc);
        for(string s: Horizontal){
            myAns.push_back("h" + s);
        }
    }
    if(sr + 1 <= dr){
        vector<string> Vertical = getMazePathsHV(sr + 1, sc, dr, dc);
        for(string s: Vertical){
            myAns.push_back("v" + s);
        }
    }
    return myAns;
}

// Way up method with 2 moves
// confirm the count var from video recording
int getMazePathsHV(int sr, int sc, int dr, int dc, string ans){
    if(sr == dr && sc == dc){
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    if(sc + 1 <= dc){
        count += getMazePathsHV(sr, sc + 1, dr, dc, ans + 'h');
    }
    if(sr + 1 <= dr){
        count += getMazePathsHV(sr + 1, sc, dr, dc, ans + 'v');
    }
    return count;
}

// same as above, without count.
void printMazePaths(int sr, int sc, int dr, int dc, string psf){
    if(sr == dr && sc == dc){
        cout << psf << endl;
        return;
    }
    if(sc < dc) printMazePaths(sr, sc + 1, dr, dc, psf + "h");
    if(sr < dr) printMazePaths(sr + 1, sc, dr, dc, psf + "v");
    
}


vector<string> getMazePathsHVD(int sr, int sc, int dr, int dc){
    if(sr == dr && sc == dc){
        vector<string> base;
        base.push_back("");
        return base;
    }
    vector<string> myAns;
    if(sc + 1 <= dc){
        vector<string> Horizontal = getMazePathsHVD(sr, sc + 1, dr, dc);
        for(string s: Horizontal){
            myAns.push_back("h" + s);
        }
    }
    if(sr + 1 <= dr){
        vector<string> Vertical = getMazePathsHVD(sr + 1, sc, dr, dc);
        for(string s: Vertical){
            myAns.push_back("v" + s);
        }
    }
    if(sr + 1 <= dr && sc + 1 <= dc){
        vector<string> Diagonal = getMazePathsHVD(sr + 1, sc + 1, dr, dc);
        for(string s: Diagonal){
            myAns.push_back("d" + s);
        }
    }
    return myAns;
}

// Get maze paths from (sr,sc) to (dr,dc) with any number of jumps between the two
// moves - horizontal h, vertical v and diagonal d
// result should add the number of jumps too
// eg
// i/p: 2 2
// o/p: [h1v1, v1h1, d1]
vector<string> getMazePathsWithJumps(int sr, int sc, int dr, int dc){
    if(sr == dr && sc == dc){
        vector<string> base;
        base.push_back("");
        return base;
    }
    vector<string> myAns;
    int tempR = sr, tempC = sc;
    
    while(tempC < dc){
        tempC++;
        vector<string> Horizontal = getMazePathsWithJumps(sr, tempC, dr, dc);
        for(string s: Horizontal){
            int jump = tempC - sc;
            myAns.push_back("h" + to_string(jump) + s);
            //cout<<"h" + to_string(jump) + s;
        }
    }
    
    while(tempR < dr){
        tempR++;
        vector<string> Vertical = getMazePathsWithJumps(tempR, sc, dr, dc);
        for(string s: Vertical){
            int jump = tempR - sr;
            myAns.push_back("v" + to_string(jump) + s);
            //cout << "v" << jump << s;
        }
    }
    
    tempR = sr;
    tempC = sc;
    while(tempR < dr && tempC < dc){
        tempR++;
        tempC++;
        vector<string> Diagonal = getMazePathsWithJumps(tempR, tempC, dr, dc);
        for(string s: Diagonal){
            int jump = tempR - sr;
            myAns.push_back("d" + to_string(jump) + s);
            //cout << "d" << jump << s;
        }
    }
    return myAns;
}

void printMazePaths(int sr, int sc, int dr, int dc, string psf) {
    if(sr == dr && sc == dc){
        cout << psf << endl;
        return;
    }
    for(int jump = 1; sc + jump <= dc; jump++) 
        printMazePaths(sr, sc + jump, dr, dc, psf + "h" + to_string(jump));
    for(int jump = 1; sr + jump <= dr; jump++)  
        printMazePaths(sr + jump, sc, dr, dc, psf + "v" + to_string(jump));
    for(int jump = 1; sr + jump <= dr && sc + jump <= dc; jump++) 
        printMazePaths(sr + jump, sc + jump, dr, dc, psf + "d" + to_string(jump));
}

vector<string> getStairPaths(int n){
    if(n == 0){
        vector<string> base;
        base.push_back("");
        return base;
    }
    vector<string> myAns;
    for(int i = 1; i <=3; i++){
        if(n - i < 0) break;
        vector<string> smallAns = getStairPaths(n - i);
        for(string s: smallAns) myAns.push_back(to_string(i) + s);
    }
    return myAns;
}

void printStairPaths(int n, string psf){
    if(n == 0){
        cout << psf << endl;
        return;
    }
    for(int i = 1; i <=3; i++){
        if(n - i < 0) break;
        printStairPaths(n - i, psf + to_string(i));
    }
}


int main(){
    int n, m;
    cin >> n;
    cin >> m;
    vector<string> res = getMazePathsHV(0, 0, n - 1, m - 1);
    display(res);
}