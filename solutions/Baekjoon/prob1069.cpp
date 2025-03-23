#include <iostream>
#include <cmath>
#include <iomanip>
using namespace std;

double getSec(double dist, int d, int t) {
    double sec1 = dist;
    double sec2 = 0;
    double sec3 = 0;

    int idx = 0;
    while ((idx * d) < dist) {
        idx++;
    }

    if (idx <= 1) {
        sec2 = t + abs(dist - d);
        sec3 = 2 * t;
    } else {
        sec2 = (idx - 1) * t + abs(dist - (idx - 1) * d);
        sec3 = idx > 1 ? idx * t : 2 * t;
    }
    return min(sec1, min(sec2, sec3));
}

int main() {
    int x, y, d, t;
    cin >> x >> y >> d >> t;

    double dist = sqrt(x * x + y * y);
    double sec = getSec(dist, d, t);
    cout << fixed << setprecision(13);
    cout << sec << endl;
}
