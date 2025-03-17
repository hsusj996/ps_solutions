#include <iostream>

using namespace std;

typedef long long ll;

int CCW(pair<ll, ll> p1, pair<ll, ll> p2, pair<ll, ll> p3) {
    ll temp = (p1.first * p2.second) + (p2.first * p3.second) + (p3.first * p1.second);
    temp -= (p1.second * p2.first) + (p2.second * p3.first) + (p3.second * p1.first);

    if (temp > 0) return 1;
    else if (temp < 0) return -1;
    else return 0;
}

bool isOverlapped(pair<ll, ll> a, pair<ll, ll> b, pair<ll, ll> c, pair<ll, ll> d) {
    int ccw1 = CCW(a, b, c) * CCW(a, b, d);
    int ccw2 = CCW(c, d, a) * CCW(c, d, b);

    return (ccw1 < 0) && (ccw2 < 0);
}

int main() {
    pair<ll, ll> a, b, c, d;

    cin >> a.first >> a.second;
    cin >> b.first >> b.second;
    cin >> c.first >> c.second;
    cin >> d.first >> d.second;

    if (isOverlapped(a, b, c, d)) {
        cout << "1";
    } else {
        cout << "0";
    }
}
