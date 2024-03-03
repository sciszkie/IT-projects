#include <stdio.h>

int main() {
    float fahr, celsius;
    float lower,upper, step;
    lower=0;
    upper=100;
    step=2;
    celsius=lower;
    while(celsius<=upper){
        fahr=(9*celsius/5)-32;
        printf("%3.0f %6.1f\n",celsius,fahr);
        celsius=celsius+step;
    }
}