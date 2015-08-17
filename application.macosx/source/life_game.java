import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class life_game extends PApplet {

int[][] cells = new int[30][30];
int[] objX = new int[30];
int[] objY = new int[30];

public void setup(){
  size(300,300);
  background(255);
  fill(0);
  frameRate(5);
  
  for(int j=0; j<height/10; j++){
    for(int i=0; i<width/10; i++){
      objX[i]=i*10; //insert points value
      }
     objY[j]=j*10; //insert points value
  }
  
    for(int j=0; j<30; j++){
     for(int i=0; i<30; i++){
       if(random(1)>0.5f){
         cells[i][j]=1;
       }else{
         cells[i][j]=0;
       }
     }
   }
  
  for(int j=0; j<30; j++){
    for(int i=0; i<30; i++){
      if(cells[i][j]==1){
        rect(objX[i],objY[j],10,10);
      }
    }
  }
  
} // end setup

public void prepare(){
  background(255);
  for(int j=0; j<30; j++){
    for(int i=0; i<30; i++){
      line(objX[i],0,objX[i],height);
      }
     line(0,objY[j],width,objY[j]);
  }
}

public void draw(){
prepare();
int[][] neighbor = new int[30][30];
int count=0;
   for(int j=1; j<29; j++){
     for(int i=1; i<29; i++){
       if(cells[i][j]==1){ // myself is alive!
        for(int a=-1; a<2; a++){
          for(int b=-1; b<2; b++){
            if(cells[i+a][j+b]==1){
              count++;
            }
          }
        }
       switch (count-1){
         case 0:
         case 1:
            neighbor[i][j]=0;
            break;
         case 2:
         case 3:
            neighbor[i][j]=1;
            break;
         default:
            neighbor[i][j]=0;
            break;
       }
       
       }else{ // myself was dead!
        for(int a=-1; a<2; a++){
          for(int b=-1; b<2; b++){
            if(cells[i+a][j+b]==1){
              count++;
            }
          }
        }
       switch (count){
         case 3:
            neighbor[i][j]=1;
            break;
         default:
            neighbor[i][j]=0;
            break;
       }        
       }
       count=0;
     }
   }
   
   for(int j=0; j<30; j++){
     for(int i=0; i<30; i++){
       if(neighbor[i][j]==1){
         rect(objX[i],objY[j],10,10);
         cells[i][j]=1;
       }else{
         cells[i][j]=0;
       }
     }
   }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "life_game" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
