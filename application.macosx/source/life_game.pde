int[][] cells = new int[30][30];
int[] objX = new int[30];
int[] objY = new int[30];

void setup(){
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
       if(random(1)>0.5){
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

void prepare(){
  background(255);
  for(int j=0; j<30; j++){
    for(int i=0; i<30; i++){
      line(objX[i],0,objX[i],height);
      }
     line(0,objY[j],width,objY[j]);
  }
}

void draw(){
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
