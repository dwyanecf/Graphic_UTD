package Tetris;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Random;

public class MyThread extends Thread
{
    public static MainFrame main = new MainFrame();
    public static boolean isDead = false;
    public static boolean start = false;
    public static boolean start1;
    public static int DieLine[] = new int[4];
    public static int Score = 0;

    public static int M_Scoring_factor = 1; // range from 1-10
    public static int N_Number_of_Rows = 20; // range from 20-50
    public static double Velocity =  0.1;         // range from 0.1-1.0

    public static int Level = 0;
    public static int Speed = 0;

    public static int time[] = {
        900, 800, 700, 600, 500, 400, 300, 200, 100
    };
    public static boolean fall = false;
    public static Random random = new Random();
    public static Object CurObj = new Object();
    public static Object NexObj = new Object();
    public static int EV[][] = new int[12][24];
    public static Robot ne;
    public MyThread(){
    	try {
			ne = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
    }
    public void run()
    {
        BeginGame();

        while(start1){
        	if(!start)yanshi(2);
                while(start)
                {
                    yanshi(time[Speed]);
                        if(start)
                          fall();
                                isDead();
                                if(isDead)
                                {
                                    EndGame();
                                    start = false;
                                    start1 = false;
                                }
                }
        }
    }

    public void BeginGame()
    {
        start = true;
        start1 = true;
        isDead = false;

        //generate the next object color and coordinate.
        CreateObj(NexObj);

        //let the current coordinate equal to the next object's.
        GetNextObj();
        System.out.println("game start");
        //initial EV array
        for(int i = 0; i < 12; i++)
        {
            for(int j = 0; j < 24; j++)
             EV[i][j] = 0;

        }

        for(int i = 0; i < 12; i++)
        {
            //since the first battle stage, there is no blocks underline.
            //j=24 not satisfy j<24
            for(int j = 24 - Level; j < 24; j++)
            {
                EV[i][j] = random.nextInt(7)+1;}

        }

        //paint the current pane
        main.repaint();
    }

    public static void CreateObj(Object obj)
    {
        int sx = 6;
        obj.type = random.nextInt(7)+1;
        switch(obj.type)
        {
//        ##
//        ##
        case 1:
            obj.pos[0].x = sx;
            obj.pos[0].y = 0;
            obj.pos[1].x = sx + 1;
            obj.pos[1].y = 0;
            obj.pos[2].x = sx;
            obj.pos[2].y = 1;
            obj.pos[3].x = sx + 1;
            obj.pos[3].y = 1;
            obj.center.x = sx;
            obj.center.y = 0;
            break;
//          #
//          ##
//           #
        case 2:
            obj.pos[0].x = sx;
            obj.pos[0].y = 0;
            obj.pos[1].x = sx;
            obj.pos[1].y = 1;
            obj.pos[2].x = sx + 1;
            obj.pos[2].y = 1;
            obj.pos[3].x = sx + 1;
            obj.pos[3].y = 2;
            obj.center.x = sx + 1;
            obj.center.y = 1;
            break;
//           #
//          ##
//          #
        case 3:
            obj.pos[0].x = sx + 1;
            obj.pos[0].y = 0;
            obj.pos[1].x = sx;
            obj.pos[1].y = 1;
            obj.pos[2].x = sx + 1;
            obj.pos[2].y = 1;
            obj.pos[3].x = sx;
            obj.pos[3].y = 2;
            obj.center.x = sx;
            obj.center.y = 1;
            break;
//          ##
//           #
//           #
        case 4:
            obj.pos[0].x = sx;
            obj.pos[0].y = 0;
            obj.pos[1].x = sx + 1;
            obj.pos[1].y = 0;
            obj.pos[2].x = sx + 1;
            obj.pos[2].y = 1;
            obj.pos[3].x = sx + 1;
            obj.pos[3].y = 2;
            obj.center.x = sx + 1;
            obj.center.y = 1;
            break;
//          ##
//          #
//          #
        case 5:
            obj.pos[0].x = sx;
            obj.pos[0].y = 0;
            obj.pos[1].x = sx + 1;
            obj.pos[1].y = 0;
            obj.pos[2].x = sx;
            obj.pos[2].y = 1;
            obj.pos[3].x = sx;
            obj.pos[3].y = 2;
            obj.center.x = sx;
            obj.center.y = 1;
            break;
//          #
//          ##
//          #
        case 6:
            obj.pos[0].x = sx;
            obj.pos[0].y = 0;
            obj.pos[1].x = sx;
            obj.pos[1].y = 1;
            obj.pos[2].x = sx + 1;
            obj.pos[2].y = 1;
            obj.pos[3].x = sx;
            obj.pos[3].y = 2;
            obj.center.x = sx;
            obj.center.y = 1;
            break;
//          #
//          #
//          #
//          #
        case 7:
            obj.pos[0].x = sx;
            obj.pos[0].y = 0;
            obj.pos[1].x = sx;
            obj.pos[1].y = 1;
            obj.pos[2].x = sx;
            obj.pos[2].y = 2;
            obj.pos[3].x = sx;
            obj.pos[3].y = 3;
            obj.center.x = sx;
            obj.center.y = 2;
            break;
        }
    }

    
    public static void GetNextObj()
    {
        //let the current coordinate equal to the next object's.
        CurObj.type = NexObj.type;
        CurObj.center.x = NexObj.center.x;
        CurObj.center.y = NexObj.center.y;
        for(int i = 0; i < 4; i++)
        {
            CurObj.pos[i].x = NexObj.pos[i].x;
            CurObj.pos[i].y = NexObj.pos[i].y;
        }
        CreateObj(NexObj);
        main.repaint();
    }

    //function for calculating the elimination of lines.
    public static void DelDieLine()
    {
        int isDead = 1;
        //how many lines are eliminated
        int DieCount = 0;
        for(int i = 0; i < 4; i++)
            DieLine[i] = -1;

        for(int i = 23; i >= 0; i--)
        {
            isDead = 1;
            for(int j = 0; j < 12; j++)
            {
                // if all the squares in the same line is full then set isDead==1 then eliminate this line
                if(EV[j][i] != 0)
                    continue;
                isDead = 0;
                break;
            }

            if(isDead == 1)
            {
                DieLine[DieCount] = i;
                DieCount++;
            }
        }

        switch(DieCount)
        {
            //eliminate 1 line for 1 score.
        case 1:
            Score++;
            break;
            //eliminate 2 line for 3 score.
        case 2:
            Score += 3;
            break;
            //eliminate 3 line for 4 score.
        case 3:
            Score += 4;
            break;
            //eliminate 4 line for 6 score.
        case 4:
            Score += 6;
            break;
        default:
            System.out.println("No line can be eliminated");
        }
        if(Score >= N_Number_of_Rows)
        {
            Speed++;
            if(Speed >= 9)
                Speed = 0;
//            N_Number_of_Rows += 10;
        }
        //if lines are eliminated then redraw the squares in the pane.
        for(int i = DieCount - 1; i >= 0; i--)
        {
            for(int DestLine = DieLine[i]; DestLine > 0; DestLine--)
            {
                for(int j = 0; j < 12; j++)
                    EV[j][DestLine] = EV[j][DestLine - 1];
            }
        }
    }

    public static void Move(int step)
    {
        Point temp[] = new Point[4];
        temp[0] = new Point();
        temp[1] = new Point();
        temp[2] = new Point();
        temp[3] = new Point();
        for(int i = 0; i < 4; i++)
        {
            temp[i].x = CurObj.pos[i].x + step;
            temp[i].y = CurObj.pos[i].y;
            if(temp[i].x < 0 || temp[i].x >= 12)
                return;
            if(temp[i].y < 0 || temp[i].y >= 24)
                return;
            if(EV[temp[i].x][temp[i].y] != 0)
                return;
        }

        for(int i = 0; i < 4; i++)
        {
            CurObj.pos[i].x = temp[i].x;
            CurObj.pos[i].y = temp[i].y;
        }

        CurObj.center.x += step;
        main.repaint();
    }

    public static void fall()
    {
        Point temp[] = new Point[4];
        temp[0] = new Point();
        temp[1] = new Point();
        temp[2] = new Point();
        temp[3] = new Point();
        for(int i = 0; i < 4; i++)
        {
            temp[i].x = CurObj.pos[i].x;
            temp[i].y = CurObj.pos[i].y + 1;
            if(temp[i].y >= 30)
                return;
            if(temp[i].y >= 24)
            {
                for(int n = 0; n < 4; n++)
                    //this time, i get the current object color and give it to EV[][].
                    EV[CurObj.pos[n].x][CurObj.pos[n].y] = CurObj.type;

                DelDieLine();
                SetCruObjZero();
                GetNextObj();
                main.repaint();
                return;
            }
            if(EV[temp[i].x][temp[i].y] != 0)
            {
                for(int n = 0; n < 4; n++)
                    EV[CurObj.pos[n].x][CurObj.pos[n].y] = CurObj.type;

                DelDieLine();
                SetCruObjZero();
                GetNextObj();
                main.repaint();
                return;
            }
        }

        for(int i = 0; i < 4; i++)
        {
            CurObj.pos[i].x = temp[i].x;
            CurObj.pos[i].y = temp[i].y;
        }
        
        CurObj.center.y++;
        main.repaint();
    }

    public static void isDead()
    {
        int isdead = 0;
        for(int i = 23; i >= 0; i--)
        {
            isdead = 1;
            for(int j = 0; j < 12; j++)
            {
                if(EV[j][i] == 0)
                    continue;
                isdead = 0;
                break;
            }
        }

        if(isdead == 0)
        {
            isDead = true;
            SetCruObjZero();
        }
    }



    public static void SetCruObjZero()
    {
        for(int i = 0; i < 4; i++)
            CurObj.pos[i].y = 30;
    }

    public static void Filp()
    {
        Point temp[] = new Point[4];
        temp[0] = new Point();
        temp[1] = new Point();
        temp[2] = new Point();
        temp[3] = new Point();
        if(CurObj.type == 0)
            return;
        for(int i = 0; i < 4; i++)
        {
            int cx = CurObj.pos[i].x - CurObj.center.x;
            int cy = CurObj.center.y - CurObj.pos[i].y;
            //here +cy +cx means clockwise
            temp[i].x = CurObj.center.x + cy;
            temp[i].y = CurObj.center.y + cx;
            if(temp[i].x < 0 || temp[i].x >= 12)
                return;
            if(temp[i].y < 0 || temp[i].y >= 24)
                return;
            if(EV[temp[i].x][temp[i].y] == 1)
                return;
        }

        for(int i = 0; i < 4; i++)
        {
            CurObj.pos[i].x = temp[i].x;
            CurObj.pos[i].y = temp[i].y;
        }
        main.repaint();
    }

    public static void FilpUnclock()
    {
        Point temp[] = new Point[4];
        temp[0] = new Point();
        temp[1] = new Point();
        temp[2] = new Point();
        temp[3] = new Point();
        if(CurObj.type == 0)
            return;
        for(int i = 0; i < 4; i++)
        {
            int cx = CurObj.pos[i].x - CurObj.center.x;
            int cy = CurObj.center.y - CurObj.pos[i].y;
            //here -cy -cx means counter-clockwise
            temp[i].x = CurObj.center.x - cy;
            temp[i].y = CurObj.center.y - cx;
            if(temp[i].x < 0 || temp[i].x >= 12)
                return;
            if(temp[i].y < 0 || temp[i].y >= 24)
                return;
            if(EV[temp[i].x][temp[i].y] == 1)
                return;
        }

        for(int i = 0; i < 4; i++)
        {
            CurObj.pos[i].x = temp[i].x;
            CurObj.pos[i].y = temp[i].y;
        }
        main.repaint();
    }


    public static void EndGame()
    {
        Level = 0;
        Speed = 0;
        Score = 0;
        for(int j = 0; j < 24; j++)
        {
            for(int i = 0; i < 12; i++)
                EV[i][j] = 1;

            main.repaint();
            yanshi(5);
        }

        for(int j = 23; j >= 0; j--)
        {
            for(int i = 11; i >= 0; i--)
                EV[i][j] = 0;

            main.repaint();
            yanshi(5);
        }
    }

    public static void yanshi(int shijian)
    {       
            ne.delay(shijian);
    }
}
