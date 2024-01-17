#include<math.h>
#include<stdio.h>
#include<iostream>
using namespace std;

int main()
{
float a[51],b[51],c[51];
int N;

float dt,T,k1,k2;
cout<<"Enter the large number N="<<endl;
cin>>N;
cout<<"Enter small period dt= "<<endl;
cin>>dt;
T=(float)N*dt;
cout<<" Simulation for a period T= "<<T;
cout<<"\nEnter k1="<<endl;
cin>>k1;
cout<<"Enter k2= "<<endl;
cin>>k2;
cout<<"Enter the value of A(0) and B(0)"<<endl;;
cin>>a[0]>>b[0];
int i;

c[0]=0.0;
for(i=1;i<=N;i++) {
  a[i] =a[i-1] + (k2*c[i-1]-k1*a[i-1]*b[i-1])*dt;
  b[i] =b[i-1] + (k2*c[i-1]-k1*a[i-1]*b[i-1])*dt;
  c[i] =c[i-1] + (2*k1*a[i-1]*b[i-1]-2*k2*c[i-1])*dt;

}
float r=0;
printf("Time         A(i)         B(i)       C(i) \n\n");

for(i=0;i<=N;i++) {

    printf("%.2f         %.2f         %.2f       %.2f \n\n",r,a[i],b[i],c[i]);
    r=r+dt;
}

return 0;

}





