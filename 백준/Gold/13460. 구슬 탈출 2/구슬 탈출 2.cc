#include<iostream>

using namespace std;

int board[10][10];
int Y[4]={0,0,-1,1};
int X[4]={-1,1,0,0};
int min(int a,int b)
{
    return a<b? a:b;
}
int go(int rY,int rX,int bY,int bX,int count,int dir)
{
    int ans=100;
    if(count==10)
    {
        return 100;
    }

    for(int i=0;i<4;i++)
    {
        //이전과 같은 방향으로 기울 일 때,
        //반대 방향으로 기울이때는 고려하지 않아도 된다.
        if(i+dir==1 || i+dir==5 || i==dir)
            continue;


        int m_rY=rY;
        int m_rX=rX;
        int m_bY=bY;
        int m_bX=bX;

        int countR=0;
        int countB=0;
        bool check=false;
        while(countB<9)
        {
            m_bY+=Y[i];
            m_bX+=X[i];
            if(board[m_bY][m_bX]==0)//Blue가 구멍으로 들어간 경우
            {
                check=true;
                break;
            }
            else if(board[m_bY][m_bX]==-1)
            {
                m_bY-=Y[i];
                m_bX-=X[i];
                break;
            }
            countB++;
        }
        if(check) continue;

        while(countR<9)
        {
            m_rY+=Y[i];
            m_rX+=X[i];
            if(board[m_rY][m_rX]==0)
            {
                return count+1;
            }
            else if(board[m_rY][m_rX]==-1)
            {
                m_rY-=Y[i];
                m_rX-=X[i];
                break;
            }
            countR++;
        }

        if(m_rY==m_bY && m_rX==m_bX)
        {
            if(countB>countR)
            {
                m_bY-=Y[i];
                m_bX-=X[i];
            }
            else
            {
                m_rY-=Y[i];
                m_rX-=X[i];
            }
        }
        if(countR!=0 || countB!=0)
            ans=min(ans,go(m_rY,m_rX,m_bY,m_bX,count+1,i));
    }
    return ans;
}

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int N,M;
    cin>>N>>M;

    int RedX,RedY,BlueX,BlueY;
    char ch;
    for(int i=0;i<N;i++)
    {
        for(int j=0;j<M;j++)
        {
            cin>>ch;
            if(ch=='#') board[i][j]=-1;
            else if(ch=='.') board[i][j]=1;
            else if(ch=='0') board[i][j]=0;
            else if(ch=='B')
            {
                board[i][j]=1;
                BlueY=i;
                BlueX=j;
            }
            else if(ch=='R')
            {
                board[i][j]=1;
                RedY=i;
                RedX=j;
            }
        }
    }
    
    int answer=go(RedY,RedX,BlueY,BlueX,0,-100);
    if(answer==100)
        cout<<-1;
    else
        cout<<answer;
    
    return 0;
}