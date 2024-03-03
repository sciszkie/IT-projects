#include <stdio.h>

/*1.5.4 Count Words with modification from 1.6.4.1-13 horizontal histogram of digits occurance */



int main() {

  int c,i;
  int ncharacters[96];
  for (i=0;i<96;i++){
      ncharacters[i]=0;
  }
  while((c=getchar())!='1'){
      if(c>=' '&& c<=127){
          ++ncharacters[c-32];
      }
  }
  for (int i=0;i<96;i++){
      putchar(32+i);
      putchar('\t');
      for (int j=1;j<=ncharacters[i];j++){
          putchar('*');
      }
      putchar('\n');
  }
}