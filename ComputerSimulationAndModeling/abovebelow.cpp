#include<iostream>
#include <stdlib.h>
#include<stdio.h>
#include <time.h>
#include<math.h>
using namespace std;
int main(){
    int N;
    cout<<"Enter the total number of observations N"<<endl;
    cin>>N;
    float random[N];
    srand (time(NULL));
    int i,j;
    for(i=0;i<N;i++){
        random[i]=rand() % 100 + 1;

  }
  cout<<" Random Number "<<endl;
  for(j=0;j<N;j++){
        random[j]=(float)random[j]/100;
   cout<<random[j]<<" ";
  }

  float mean=(0.99+0.01)/2;
  cout<<"mean ="<<mean<<endl;

  char sign[N];
  j=0;
  for(i=0;i<N;i++){

    if(random[i]>=mean){

            sign[j] = '+';
            j=j+1;

    }
    else
    {
         sign[j] = '-';
            j=j+1;
    }
  }
  cout<<endl;
  int k;
  for(k=0;k<j;k++){

   cout<<sign[k]<<" ";
  }
  int b=1,l,n1=0,n2=0;
  for(i=0;i<N;i++)
  {
      if(sign[i]=='+')
      {
          n1=n1+1;
      }
      else
      {
          n2=n2+1;

      }
  }
  cout<<"n1 = "<<n1<<"  "<<"n2 = "<<n2<<" "<<endl;
  char first=sign[0],second;
  for(l=1;l<N;l++){
        second=sign[l];
    if(first==second){

    }
    else
    {
    first=second;
       b=b+1;
    }
  }
   cout<<" \n b =  "<<b;
   float Ub,Ob,Zo;
   float temp=n1*n2;
   Ub=(float)((2*(temp))/N)+0.5;

   cout<<"\n Ub = "<<Ub;
   Ob=(float)((2*temp)*(2*temp-N))/((N*N)*(N-1));
    cout<<" \n Ob = "<<Ob;
     Zo=(float)(b-Ub)/sqrt(Ob);
    cout<<" \nZo = "<<Zo;

    float criticalValue;
    cout<<"Enter the Critical Value"<<endl;
    cin>>criticalValue;
    if(criticalValue<Zo ){
        cout<<"null hypothesis rejected"<<endl;

    }
    else
        {
          cout<<"null hypothesis accepted"<<endl;
        }
  }



