import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
  //variables
  PImage stage;
  PImage gameover;
  int timer = 6000;
  int select[];
  int numberofkirby;
  int score = 0;
  PImage kirbyicon;
  //kirby1
  PImage kirby_spritesheet;
  PImage kirby_walking_sheet;
  PImage[] kirby_walking_frames;
  int int_kirby_walking_frames = 10;
  int kirby_walking_width = 23;
  int kirby_walking_height = 19;
  float kirbyX;
  float kirbyY = random(500, 675);  
  PImage kirby_dying;
  int tickcount;
  boolean kirbyDisplay = true;
  float deadkirbyX;
  float deadkirbyY;
  int deadtick;
  //kirby2
  PImage kirby_spritesheet2;
  PImage kirby_flying_sheet;
  PImage[] kirby_flying_frames;
  int int_kirby_flying_frames = 5;
  int kirby_flying_width = 32;
  int kirby_flying_height = 28;
  float kirby2X;
  float kirby2Y = random(100, 500);
  float kirby2startingY = random(100, 500);
  PImage kirby2_dying;
  PImage kirby2_falling;
  float deadkirby2X;
  float deadkirby2Y; 
  int kirby2_dying_width = 22;
  int kirby2_dying_height = 26;
  boolean kirby2Display = true;
  int tickcount2 = 0;
  //kirby3
  PImage kirby_vertflying_sheet;
  PImage[] kirby_vertflying_frames;
  int int_kirby_vertflying_frames = 5;
  int kirby_vertflying_width = 32;
  int kirby_vertflying_height = 28;
  float kirby3X = random(80,600);
  float kirby3Y = height; 
  PImage kirby3_dying;
  PImage kirby3_falling;
  float deadkirby3X;
  float deadkirby3Y; 
  float kirby3Xchange=random(-5,5);
  int kirby3_dying_width = 22;
  int kirby3_dying_height = 26;
  boolean kirby3Display = true;
  int tickcount3 = 0;
  //metaknight
  PImage metaknight_spritesheet; 
  PImage metaknight_appearing;
  PImage metaknight_turning;
  int metaknight_appearing_width = 33;
  int metaknight_appearing_height = 28;
  PImage metaknight_jump; //338,300,55,42
  PImage metaknight_glide;
  PImage dead_metaknight;
  float metaknightX = 345;
  float metaknightY = 200;
  float deadmkX;
  float deadmkY;
  boolean metaknightDisplay = true;
  float metaknightstartY = metaknightY;
  float metaknightstartX=0;
  boolean mkhit = false;
  int tickcount4 = 0;
  boolean mkappear=false; 
  int jumptick;
  int mkturningtick = 0;
  boolean mkcanhit = false;
  //boo
  PImage boo;
  PImage shyboo;
  float booX;
  float booY;
  float booXchange;
  float shybooX;
  float shybooY;
  boolean booDisplay = true;
  int tickcount5 = 0;
  //crosshair
  PImage crosshair;
  PImage hitmark;
  boolean hitmarkDisplay = false;
  int hitmarkTime; 
  int crosshairX;
  int crosshairY;
  int initialslct;
  
  
  public void settings() {
    size(1280, 720);
  }
  
  public void setup() {
    //background
    stage = loadImage("stage.png");
    background(stage);
    gameover = loadImage("gameover.png");
    select = new int[4];
    for (initialslct = 1; initialslct <= 3; initialslct++)
      select[initialslct] = -1;

    //kirby
    kirby_spritesheet = loadImage("kirby_spritesheet.png");
    kirby_walking_sheet = kirby_spritesheet.get(9, 55, kirby_walking_width*int_kirby_walking_frames, kirby_walking_height);
    kirby_dying = kirby_spritesheet.get(146, 191, 21, 19);
    kirbyicon = kirby_spritesheet.get(9,6,21,18);
    
    //kirby2
    kirby_spritesheet2 = loadImage("kirby_spritesheet2.png");
    kirby_flying_sheet = kirby_spritesheet2.get(4, 252, kirby_flying_width*int_kirby_flying_frames, kirby_flying_height);
    kirby2_dying = kirby_spritesheet2.get(4, 361, kirby2_dying_width, kirby2_dying_height);
    kirby2_falling = kirby_spritesheet2.get(223, 361, kirby2_dying_width, kirby2_dying_height);
    
    //kirby3
    kirby_vertflying_sheet = kirby_spritesheet2.get(4, 252, kirby_vertflying_width*int_kirby_vertflying_frames, kirby_vertflying_height);
    kirby3_dying = kirby_spritesheet2.get(4, 361, kirby3_dying_width, kirby3_dying_height);
    kirby3_falling = kirby_spritesheet2.get(223, 361, kirby3_dying_width, kirby3_dying_height);
    
    //metaknight 
    metaknight_spritesheet =loadImage("metaknight_spritesheet.png");
    metaknight_appearing = metaknight_spritesheet.get(108,1749,27,24);
    metaknight_turning = metaknight_spritesheet.get(308,1748,30,27);
    metaknight_jump = metaknight_spritesheet.get(338,300,55,42);
    metaknight_glide = metaknight_spritesheet.get(406,371,72,33);
    dead_metaknight = metaknight_spritesheet.get(543,1668,38,48);

    //boo
    boo = loadImage("neutral_boo.png");
    shyboo = loadImage("shy_boo.png");

    //crosshair
    crosshair = loadImage("crosshair.png");
    hitmark = loadImage("hitmark.png");
    
    //walking
    kirby_walking_frames = new PImage[int_kirby_walking_frames];
    for(int frameNum = 0; frameNum < int_kirby_walking_frames; frameNum++)
      kirby_walking_frames[frameNum] = kirby_walking_sheet.get(kirby_walking_width*frameNum, 0, kirby_walking_width, kirby_walking_sheet.height);
    //flying
    kirby_flying_frames = new PImage[int_kirby_flying_frames];
    for(int frameNum = 0; frameNum < int_kirby_flying_frames; frameNum++)
      kirby_flying_frames[frameNum] = kirby_flying_sheet.get(kirby_flying_width*frameNum, 0, kirby_flying_width, kirby_flying_sheet.height);
    //vertical flying
      kirby_vertflying_frames = new PImage[int_kirby_vertflying_frames];
    for(int frameNum = 0; frameNum < int_kirby_vertflying_frames; frameNum++)
      kirby_vertflying_frames[frameNum] = kirby_vertflying_sheet.get(kirby_vertflying_width*frameNum, 0, kirby_vertflying_width, kirby_vertflying_sheet.height);
    //initialize boolean variables
    kirbyDisplay = true;
    kirby2Display = true;
    kirby3Display = true;
    metaknightDisplay = true;
    mkhit=false;
    mkcanhit=false;
    booDisplay = true;
  }
  public void draw() {
    background(stage);
    //operates when the timer is still counting down, ie. hasn't reach 0
    if (timer > 0){
      timer-=2;
      textSize(20);
      text("time: "+timer/100, width-100, 25);
      image(kirbyicon,20,7,20,20);
      text("x "+score, 50, 25);
      
      if (timer>2000){
        image(metaknight_appearing,metaknightX,metaknightY,50,50);
        mkcanhit = false;
        mkturningtick = 0;
        jumptick = 0;
        tickcount4 = 0;
      }
      //randomize the values of the integers that are assigned to a specific movement method
      if(timer%100 == 0){
        for(int countkirby = 1; countkirby <= 3; countkirby++){
          if(select[countkirby] < 0 || select[countkirby] > 50)
            select[countkirby] = (int)(random(100)+0.5);  
        }
      }
      //determines the timing when metaknight appears
      if(timer == 2000)
        mkappear = true;
      //executes the methods if the radomized integer assigned to that method is inside the range
      if(select[1] >= 0 && select[1] <=50){
        kirbywalk();
      }
      if(select[2] >= 0 && select[2] <=50){
        kirbyfly();
      }
      if(select[3] >= 0 && select[3] <=50){
        kirbyverticalfly();
      }
      if (mkappear == true){
        mkhit =false;
        metaknight();
      }  
      //draw the crosshair and the hitmark
      crosshairX = mouseX - 25;
      crosshairY = mouseY - 25;
      image(crosshair, crosshairX, crosshairY, 50, 50);
      if (hitmarkDisplay == true && hitmarkTime < 7){
        image(hitmark, mouseX - 25, mouseY - 25, 50, 50);
        hitmarkTime++;
      }
      else{
        hitmarkDisplay = false;
        hitmarkTime = 0;
      }
      }
      //ending screen, appears when the time runs out
      else{
        image(gameover, 320, 240, 640, 200);
        textSize(40);
        text("Your Score: "+ score, 480, 480);
      }
  }
  //a movement method of kirby walking on the ground
  public void kirbywalk(){
    if (kirbyDisplay == true){
      image(kirby_walking_frames[(frameCount/3)%int_kirby_walking_frames], kirbyX, kirbyY,40,40);
      tickcount = 0;
      if (kirbyX <= width && kirbyX >=-40){  
        if(timer >= 2000)
          kirbyX += 2.5;
        else
          kirbyX += 5;
      }
      else{
        select[1] = -1;
        kirbyX = -40;
        kirbyY = random(500,650);
      }
    }
    if(kirbyDisplay == false && tickcount<60){
      image(kirby_dying, deadkirbyX, deadkirbyY, 40, 40);
      tickcount++;
    }
    if (tickcount >=60){
      select[1] = -1;
      kirbyX = -40;
      kirbyY = random(500,650);
      kirbyDisplay = true;
    }
  }
  ////a movement method of kirby flying horizontally while moving up and down in period
  public void kirbyfly(){
    if (kirby2Display == true){
      image(kirby_flying_frames[(frameCount/3)%int_kirby_flying_frames], kirby2X, kirby2Y,40,40);
      tickcount2 = 0;
      if (kirby2X <= width && kirby2X >= -40){  
        if(timer >= 2000)
          kirby2X += 2;
        else
          kirby2X += 4;
        kirby2Y = (8*sin(kirby2X/10) + kirby2startingY);
      }
      else{
        select[2] = -1;
        kirby2X = -40;
        kirby2Y = random(500,650);
        
      }
    }
    if (kirby2Display == false){  
      if(tickcount2<30){
          image(kirby2_dying, deadkirby2X, deadkirby2Y,40,40);
          tickcount2++;
        }
        else if(deadkirby2Y <= height){
          image(kirby2_falling, deadkirby2X, deadkirby2Y,40,40);
          tickcount2++;
          deadkirby2Y += 5;
        }
        else{
          select[2] = -1;
          kirby2Display = true;
          kirby2X = -40;
          kirby2Y = random(100,500);
          kirby2startingY = kirby2Y;
        }
    }
  } 
  //a movement method of kirby flying vertically/diagonally
  public void kirbyverticalfly(){
    if (kirby3Display == true){
      image(kirby_vertflying_frames[(frameCount/3)%int_kirby_vertflying_frames], kirby3X, kirby3Y,40,40);
      tickcount3 = 0;
      if (kirby3Y <= height && kirby3Y >= -40){  
        if(timer >= 2000)
          kirby3Y -=3;
        else
          kirby3Y -=6;
        kirby3X+=kirby3Xchange;
      }
      else{
        select[3] = -1;
          kirby3X = random(80,600);
          kirby3Y = height;
          kirby3Xchange=random(-3,3);
      }
    }
    if (kirby3Display == false){  
      if(tickcount3<30){
          image(kirby3_dying, deadkirby3X, deadkirby3Y,40,40);
          tickcount3++;
        }
        else if(deadkirby3Y <= height){
          image(kirby3_falling, deadkirby3X, deadkirby3Y,40,40);
          tickcount3++;
          deadkirby3Y += 5;
        }
        else{
          select[3] = -1;
          kirby3X = random(80,600);
          kirby3Y = height;
          kirby3Xchange=random(-5,5);
          kirby3Display = true;
        }
    }
  }
  //when executed, the metaknight in the background moves and glides away
  public void metaknight(){
    if(metaknightDisplay == true){
      if (mkturningtick < 45 && timer <= 2000){
        image(metaknight_turning,metaknightX,metaknightY,50,50);
        mkturningtick++;
      }
      if(mkturningtick >=45){
        if(mkcanhit == false){
          if(metaknightY > metaknightstartY-100){
            image(metaknight_jump,metaknightX,metaknightY,80,60);
            metaknightY-=4;
            metaknightstartX = 0;
          }
          else if (jumptick < 5){
            jumptick++;
          }
        }
        if(metaknightX <=width && metaknightY >= -50 && jumptick >=5){
          image(metaknight_glide,metaknightX,metaknightY,100,50);
          mkcanhit = true;
          metaknightX +=10;
          metaknightstartX += 1;
          metaknightY = -pow(2,metaknightstartX/20)+121;
        }
      }
    }
    else{
      if(tickcount4 < 60){
        image(dead_metaknight,deadmkX,deadmkY,60,80);
        tickcount4++;
      }
      else{
        mkappear=false;
      }
    }
  }
  //execute when mouse is clicked, indicates if a target is hit and operates the scoring system
  public void mouseClicked() {
    hitmarkDisplay = true;
      if (mouseX >= kirbyX && mouseX <= (kirbyX+40) && mouseY >= kirbyY && mouseY <= (kirbyY+40)){
        if(kirbyDisplay == true){
          score ++;
        }
        kirbyDisplay = false;
        deadkirbyX = kirbyX;
        deadkirbyY = kirbyY;
        
      }
      if (mouseX >= kirby2X && mouseX <= (kirby2X+40) && mouseY >= kirby2Y && mouseY <= (kirby2Y+40)){
        if(kirby2Display == true){
          score++;
        }
        kirby2Display = false;
        deadkirby2X = kirby2X;
        deadkirby2Y = kirby2Y;
      }
      if (mouseX >= kirby3X && mouseX <= (kirby3X+40) && mouseY >= kirby3Y && mouseY <= (kirby3Y+40)){
        if(kirby3Display == true){
          score++;
        }
        kirby3Display = false;  
        deadkirby3X = kirby3X;
        deadkirby3Y = kirby3Y;
      }
      if(mouseX >= metaknightX && mouseX <= (metaknightX+100) && mouseY >= metaknightY && mouseY <= (metaknightY+50) && mkcanhit == true){
        if(metaknightDisplay == true) 
          score += 10;
        metaknightDisplay = false;  
        deadmkX = metaknightX;
        deadmkY = metaknightY;
      } 
      if(mouseX >= booX && mouseX <= (booX+30) && mouseY >= booY && mouseY <= (booY+30)){
        if (booDisplay == true)
        score +=5;

      }
  } 
}