#include<iostream>
#include<stdio.h>
#include<stdlib.h>
#include<time.h>
using namespace std;
int main(){
    int N;
    cout<<"Enter the total number of observation N"<<endl;
    cin>>N;
    int n;
    cout<<"Enter the number of interval n"<<endl;
    cin>>n;

    float Ei = (float)N/n;
    cout<<" Ei = "<<Ei<<endl;

    float random[N];
  //srand (time(NULL));
  int i,j;
  for(i=0;i<N;i++){
    random[i]=rand() % 100 + 1;

  }
  cout<<" Random Number "<<endl;
  for(j=0;j<N;j++){
        random[j]=(float)random[j]/100;
        if(j%10==0)
        {
            cout<<endl;
        }

    cout<<random[j]<<"         ";
  }
  cout<<endl<<endl;
   int count1=0,Oi[n],S[n],Ssqr[n];
  float Ssum[n];
  float range=100/n;
  range=range/100;
  double upper_range=range,lower_range=0.00;
printf("   Upper    Lower           Oi       Oi-Ei      (Oi-Ei)^2        (Oi-Ei)^2/Ei\n\n");
  for(i=0;i<n;i++){
    for(j=0;j<N;j++){
    if(random[j]>=lower_range && random[j]<=upper_range  ){

        count1=count1+1;
    }

  }
 Oi[i]=count1;
    count1=0;
     S[i]=Oi[i]-Ei;
    Ssqr[i]=S[i]*S[i];
    Ssum[i]=(float)(Ssqr[i]/Ei);

printf("   %.2f      %.2f           %d           %d           %d           %.2f\n\n",lower_range,upper_range,Oi[i],S[i],Ssqr[i],Ssum[i]);
    lower_range=upper_range+0.00001;
    upper_range=upper_range+range;

       // cout<<"      "<<Oi[i]<< "       "<<S[i] <<"       "<<Ssqr[i]<<"        "<<Ssum[i]<<endl;
//    cout<<" \nOi=  "<<Oi[i]<< " \nOi-Ei = "<<S[i] <<" \n(Oi-Ei)^2 = "<<Ssqr[i]<<"  \n(Oi-Ei)^2/Ei = "<<Ssum[i]<<endl;


  }
   int k;
    float sum=0;
    for(k=0;k<n;k++){
        sum=sum+Ssum[k];
    }
     cout<<" \nTotal sum = "<<sum<<endl;

     float criticalValue=0;
     cout<<"Enter the value chiSquare"<<endl;
     cin>>criticalValue;

     if(criticalValue >= sum ){
        cout<<" Independence of numbers accepted"<<endl;

     }
     else{

      cout<<" Independence of numbers rejected"<<endl;

     }



}





