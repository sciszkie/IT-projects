#include <stdio.h>
float celToFahr(float cel);
int main() {
    float fahr;
    float lower,upper, step;
    lower=0;
    upper=300;
    step=20;
    fahr=lower;
    while(fahr<=upper){
        printf("%3.0f %6.1f\n",fahr,celToFahr(fahr));
        fahr=fahr+step;
    }
}
float celToFahr(float fahr){
    float celsius;
    celsius=5*(fahr-32)/9;
    return celsius;
}