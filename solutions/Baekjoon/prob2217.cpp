#include <iostream>
#include <algorithm>
using namespace std;

int rope[100000];
int N;

int main()
{
    cin >> N;
    for (int i = 0; i < N; i++)
    {
        cin >> rope[i];
    }

    sort(rope, rope + N);

    int max = 0;
    for (int i = 0; i < N; i++)
    {
        int w = rope[i] * (N - i);
        
        if (max < w)
        {
            max = w;
        }
    }

    cout << max;
}