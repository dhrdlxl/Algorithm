#include<iostream>
#include<vector>
#include<queue>
using namespace std;

const int LIMIT=5;
int N, answer, sum;

int max(int a,int b)
{
    return a>b? a:b;
}
bool isSame(int (*a)[20],int (*b)[20])
{
    for(int i=0;i<N;i++)
    {
        for(int j=0;j<N;j++)
        {
            if(a[i][j]!=b[i][j])
                return false;
        }
    }
    return true;
}
void go(int count,int (*board)[20],int dir)
{
    if(count==LIMIT) return;
    
    int compareBoard[20][20];
    int maxNum=0;
    bool isChanged=false;//합쳐진 숫자가 있는지 검사
    for(int i=0;i<N;i++)
    {
        for(int j=0;j<N;j++)
        {
            compareBoard[i][j]=board[i][j];
        }
    }
    queue<int> q;
    if(dir==0)//left
    {
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                if(board[i][j]!=0)
                {
                    q.push(board[i][j]);
                }
            }
            int index=0;
            int before=0;
            while(!q.empty())
            {
                if(before==q.front())
                {
                    board[i][--index]*=2;
                    before=0;
                    isChanged=true;
                }
                else
                {
                    board[i][index]=q.front();
                    before=q.front();
                }
                maxNum=max(maxNum,board[i][index]);
                index++;
                q.pop();
            }
            for(;index<N;index++)
            {
                board[i][index]=0;
            }
        }
    }
    else if(dir==1)//right
    {
        for(int i=0;i<N;i++)
        {
            for(int j=N-1;j>=0;j--)
            {
                if(board[i][j]!=0)
                {
                    q.push(board[i][j]);
                }
            }
            int index=N-1;
            int before=0;
            while(!q.empty())
            {
                if(before==q.front())
                {
                    board[i][++index]*=2;
                    before=0;
                    isChanged=true;
                }
                else
                {
                    board[i][index]=q.front();
                    before=q.front();
                }
                maxNum=max(maxNum,board[i][index]);
                index--;
                q.pop();
            }
            for(;index>=0;index--)
            {
                board[i][index]=0;
            }
        }
    }
    else if(dir==2)//up
    {
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                if(board[j][i]!=0)
                {
                    q.push(board[j][i]);
                }
            }
            int index=0;
            int before=0;
            while(!q.empty())
            {
                if(before==q.front())
                {
                    board[--index][i]*=2;
                    before=0;
                    isChanged=true;
                }
                else
                {
                    board[index][i]=q.front();
                    before=q.front();
                }
                maxNum=max(maxNum,board[index][i]);
                index++;
                q.pop();
            }
            for(;index<N;index++)
            {
                board[index][i]=0;
            }
        }
    }
    else if(dir==3)//down
    {
        for(int i=0;i<N;i++)
        {
            for(int j=N-1;j>=0;j--)
            {
                if(board[j][i]!=0)
                {
                    q.push(board[j][i]);
                }
            }
            int index=N-1;
            int before=0;
            while(!q.empty())
            {
                if(before==q.front())
                {
                    board[++index][i]*=2;
                    before=0;
                    isChanged=true;
                }
                else
                {
                    board[index][i]=q.front();
                    before=q.front();
                }
                maxNum=max(maxNum,board[index][i]);
                index--;
                q.pop();
            }
            for(;index>=0;index--)
            {
                board[index][i]=0;
            }
        }
    }
    
    if(answer<maxNum) answer=maxNum;
    
    //현재 최대 값이 아무리 증가해도 answer보다 같거나 작을 때.
    if(maxNum << (LIMIT-count-1) <= answer)
    {
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                board[i][j]=compareBoard[i][j];
            }
        }
        return ;
    }
       
    //이동 전과 후의 board 상태 변화가 없을 때
    if(isSame(compareBoard,board))  
    {
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                board[i][j]=compareBoard[i][j];
            }
        }
        return ;
    }
    //현재 최대 값을 2배를 했을 때 모든 수의 합보다 크면 더 큰수를 만들 수 없다.
    if(maxNum*2>sum) 
    {
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                board[i][j]=compareBoard[i][j];
            }
        }
        return ;
    }
    
    for(int i=0;i<4;i++)
    {
        //현재 회차에서 수의 변화가 없으면 같은 방향은 안본다.
        if(!isChanged && dir==i) continue;
        go(count+1,board,i);
    }

    for(int i=0;i<N;i++)
    {
        for(int j=0;j<N;j++)
        {
            board[i][j]=compareBoard[i][j];
        }
    }
    return ;
}

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    
    cin>>N;
    
    int board[20][20];
    for(int i=0;i<N;i++)
    {
        for(int j=0;j<N;j++)
        {
            cin>>board[i][j];
            sum+=board[i][j];
        }
    }

    for(int i=0;i<4;i++)
    {
        go(0,board,i);
    }
    cout<<answer;
    return 0;
}