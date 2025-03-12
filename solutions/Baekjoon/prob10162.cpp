#include <iostream>
#include <algorithm>

using namespace std;

static const int button[] = {300, 60, 10};

int main()
{
    int T;
    cin >> T;

    int ans[] = {0, 0, 0};
    for (int i = 0; i < 3; i++)
    {
        ans[i] += (T / button[i]);
        T %= button[i];
    }

    if (T == 0)
    {
        for (int i = 0; i < 3; i++)
        {
            cout << ans[i] << " ";
        }
    }
    else
    {
        cout << -1;
    }
}